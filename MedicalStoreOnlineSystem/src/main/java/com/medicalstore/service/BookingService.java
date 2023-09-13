package com.medicalstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.BookingDAO;
import com.medicalstore.dao.CustomerDAO;
import com.medicalstore.dao.MedicalStoreDAO;
import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.dto.BookingDTO;
import com.medicalstore.dto.CustomerDTO;
import com.medicalstore.dto.MedicineDTO;
import com.medicalstore.entity.Booking;
import com.medicalstore.entity.Customer;
import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Medicine;
import com.medicalstore.enums.BookingStatus;
import com.medicalstore.exception.BookingAlreadyCancelled;
import com.medicalstore.exception.BookingAlredyDeliverd;
import com.medicalstore.exception.BookingCantBeCancelled;
import com.medicalstore.exception.MedicineDetailNotFound;
import com.medicalstore.exception.MedicineNotAvailableException;

@Service
public class BookingService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private MedicineDAO medicineDao;
	
	@Autowired
	private MedicalStoreDAO medicalStoreDao;

	@Autowired
	private BookingDAO bookingDao;
		
	public ResponseEntity<ResponseStructure<BookingDTO>> saveBooking(BookingDTO bookingDto, long customerId, long medicalStoreId,Map<String,String> medicineIdAndQty) {
		
		/*CONTROLLER PASS BOOKING DATA , CUSTOMER ID AND MAP -CONTAINS MEDICINE NUMBER AND EACH MEDICINE QTY */

			//CHECK HASHMAP EMPTY OR NOT 
			if (medicineIdAndQty.isEmpty()) {
				throw new MedicineDetailNotFound("Please provide medicine Id and Quantity..");
			}

			//FIRST I CHECK CUSTOMER PRESENT OR NOT
			Customer customer = customerDao.findCutomerById(customerId);
			List<Booking> bookingList = customer.getBooking();

			if(bookingList.isEmpty())
				bookingList = new ArrayList<>();

			MedicalStore medicalStore = medicalStoreDao.findMedicalStoreById(medicalStoreId);

			//THEN GET ALL MEDICINE ID / MEDICINE FORM THE MAP 
			Set<String> medicineId = medicineIdAndQty.keySet();

			//CUSTOMER HAS MANY MEDICINE 
			List<MedicineDTO> medicineListDto = new ArrayList<>();
			//at the time saving booking save this medicine object and each quantity
			Long[][] objectMap  = new Long[medicineIdAndQty.size()][2];
			//GET ALL MEDICINE COUNT TO SET BOOKING
			int totalQuantity = 0;
			int arrayIndex = 0;

			//THEN CHECK MEDICINE PRESENT OR NOT 
			for(String l : medicineId){	

				Medicine medicine = medicineDao.findMedicineByIdAndMedicalStore(Long.parseLong(l),medicalStore);

				if(medicine.getStockQuantity() > 0 && Integer.parseInt(medicineIdAndQty.get(l)) <= medicine.getStockQuantity()){


						medicineListDto.add(modelMapper.map(medicine,MedicineDTO.class));

						objectMap[arrayIndex][0] = medicine.getMedicineId();
						objectMap[arrayIndex][1] = Long.parseLong(medicineIdAndQty.get(l));

						totalQuantity += Integer.parseInt(medicineIdAndQty.get(l));
						medicine.setStockQuantity(medicine.getStockQuantity() - Integer.parseInt(medicineIdAndQty.get(l)));

				}
				else throw new MedicineNotAvailableException("Medicine not available your required qty...");

				arrayIndex++;
			}

			Booking booking = this.modelMapper.map(bookingDto,Booking.class);
			
			booking.setQuantity(totalQuantity);
			booking.setMedicine(objectMap);
			booking.setCustomer(customer);
			booking.setStatus(BookingStatus.ACTIVE);
			
			customer.setBooking(bookingList);
			booking = bookingDao.saveBooking(booking);

			bookingDto = this.modelMapper.map(booking,BookingDTO.class);
			
			bookingDto.setCustomerDto(this.modelMapper.map(customer,CustomerDTO.class));
			bookingDto.setMedicineDto(objectMap);
		
			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.CREATED.value(),
																"Booking Successfully saved...", 
																bookingDto),
																HttpStatus.CREATED);
		
	}


    public ResponseEntity<ResponseStructure<BookingDTO>> cancelBooking(long bookingId) {

		Booking booking = bookingDao.findBookingById(bookingId);

		switch(booking.getStatus()){

			case ACTIVE :{

				LocalDate cancelledDate = booking.getExpectedDate().minusDays(1);

				if(cancelledDate.equals(LocalDate.now()) || LocalDate.now().isAfter(cancelledDate)){
					throw new BookingCantBeCancelled("You cant cancel booking now...");
				}
				booking.setStatus(BookingStatus.CANCELLED);
				break;
			}
			case DELIVERD :{
				 throw new BookingAlredyDeliverd("Medcine on the way you not cancelled....");
			}
			case CANCELLED :{
				throw new BookingAlreadyCancelled("Already Cancelled....");
			}
		}
		
		Long[][] objectMap = booking.getMedicine();
		
		for(int i = 0 ; i < objectMap.length ; i++){

			Medicine medi = medicineDao.findMedicineById(objectMap[i][0]); 
			long eachQuantity = objectMap[i][1];
			medi.setStockQuantity(medi.getStockQuantity() +(int)eachQuantity);

			medicineDao.saveMedicine(medi);

			
		}

		BookingDTO bookingDto = this.modelMapper.map(bookingDao.saveBooking(booking),BookingDTO.class);
		bookingDto.setCustomerDto(this.modelMapper.map(booking.getCustomer(), CustomerDTO.class));
		bookingDto.setMedicineDto(objectMap);


		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.ACCEPTED.value(),
															"Booking Cancelled", 
															bookingDto),
															HttpStatus.ACCEPTED);

    }


	public ResponseEntity<ResponseStructure<List<BookingDTO>>> getBookings(long customerId) {
		
		Customer customer  = customerDao.findCutomerById(customerId);
		List<Booking> bookingList =  bookingDao.findBookingByCustomer(customer);
		List<BookingDTO> bookingDtoList = new ArrayList<>();

		for(Booking book : bookingList){

			BookingDTO bookingDto = this.modelMapper.map(book,BookingDTO.class);
			bookingDto.setCustomerDto(this.modelMapper.map(book.getCustomer(),CustomerDTO.class));
			bookingDto.setMedicineDto(book.getMedicine());
			bookingDtoList.add(bookingDto);
		}

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(),
															"Booking found...",
															bookingDtoList),
															 HttpStatus.FOUND);

	}

}