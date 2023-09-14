package com.medicalstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.BookingDAO;
import com.medicalstore.dao.MedicalStoreDAO;
import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.dao.StaffDAO;
import com.medicalstore.dto.MedicineDTO;
import com.medicalstore.entity.Booking;
import com.medicalstore.entity.Medicine;
import com.medicalstore.exception.UnauthorizedStaff;

@Service
public class MedicineService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MedicalStoreDAO medicalDao;

	@Autowired
	private StaffDAO staffDao;

	@Autowired
	private MedicineDAO medicineDao;

	@Autowired
	private BookingDAO bookingDao;

	@Autowired
	private CheckDuplicateEntry duplicate;

	public ResponseEntity<ResponseStructure<List<Medicine>>> saveMedicines(List<MedicineDTO> medicineDto,
			long medicalStoreId, long staffId) {

		Set<MedicineDTO> medicineSet = new TreeSet<>(medicineDto);

		List<Medicine> listMedicine = new ArrayList<>();

		if (staffDao.findStaffById(staffId).getMedicalStore().getStoreId() == medicalStoreId) {
			duplicate.checkDuplicateMedicine(medicineSet, medicalStoreId);

			for (MedicineDTO medicine : medicineSet) {
				Medicine medicineEntity = this.modelMapper.map(medicine, Medicine.class);
				medicineEntity.setMedicalStore(medicalDao.findMedicalStoreById(medicalStoreId));
				listMedicine.add(medicineDao.saveMedicine(medicineEntity));
			}
		} else {
			throw new UnauthorizedStaff("This Staff does not have permission to add medicine this medical store....");
		}

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.CREATED.value(), "Medicines Successfully Stored...", listMedicine),
				HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(MedicineDTO medicineDto, long medicineId,
			long staffId) {

		Medicine medicine = medicineDao.findMedicineById(medicineId);
		long medicalStoreId = medicine.getMedicalStore().getStoreId();
		if (medicalStoreId == staffDao.findStaffById(staffId).getMedicalStore().getStoreId()) {

			Medicine medicineEntity = this.modelMapper.map(medicineDto, Medicine.class);
			medicineEntity.setMedicineId(medicineId);
			// doubt
			// medicineEntity.setMedicineName(medicine.getMedicineName());
			duplicate.checkDuplicateMedicine(medicineEntity, medicalStoreId);
			medicineEntity.setMedicalStore(medicine.getMedicalStore());

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "Medicine Updated....",
					medicineDao.saveMedicine(medicineEntity)), HttpStatus.OK);

		}
		throw new UnauthorizedStaff("This Staff does not have permission to update medicine this medical store....");
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicineById(long medicineId) {

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(), "Medicine found...",
				medicineDao.findMedicineById(medicineId)), HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicineByName(String medicineName) {

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(), "Medicine found...",
				medicineDao.findMedicineByName(medicineName)), HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineById(long medicineId, long staffId) {

		Medicine medicine = medicineDao.findMedicineById(medicineId);

		if (medicine.getMedicalStore().getStoreId() == staffDao.findStaffById(staffId).getMedicalStore().getStoreId()) {

			// List<Booking> bookingList = bookingDao.findBookingByMedicine(medicine);

			// for(Booking booking : bookingList){
			// List<Medicine> medicineList = booking.getMedicine();
			// if(medicineList.contains(medicine))
			// medicineList.remove(medicine);
			// }

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.ACCEPTED.value(), "Medicine deleted....",
					medicineDao.deleteMedicineById(medicineId)), HttpStatus.ACCEPTED);

		}
		throw new UnauthorizedStaff("This Staff does not have permission to delelte medicine this medical store....");

	}

}
