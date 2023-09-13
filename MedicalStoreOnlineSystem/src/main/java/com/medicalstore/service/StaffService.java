package com.medicalstore.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.AdminDAO;
import com.medicalstore.dao.MedicalStoreDAO;
import com.medicalstore.dao.StaffDAO;
import com.medicalstore.dto.StaffDTO;
import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Staff;
import com.medicalstore.exception.AlreadyAddStaffException;
import com.medicalstore.exception.UnauthorizedAdmin;

@Service
public class StaffService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AdminDAO adminDao;
	
	@Autowired
	private MedicalStoreDAO medicalStoreDao;
	
	@Autowired
	private StaffDAO staffDao;
	
	@Autowired
	private CheckDuplicateEntry duplicate;

	public ResponseEntity<ResponseStructure<Staff>> saveStaff(StaffDTO staff, long adminId, long medicalStoreId) {

					List<Staff> listStaff = staffDao.getAllStaff();
					
					MedicalStore medicalStore =medicalStoreDao.findMedicalStoreById(medicalStoreId);
					
					if(medicalStore.getAdmin().getAdminId() == adminId) {
							
							for(int i = 0 ; (i < listStaff.size() && (! listStaff.isEmpty())) ; i++) {
								if(listStaff.get(i).getMedicalStore().getStoreId() == medicalStoreId)
									throw new AlreadyAddStaffException("This medical have already added staff... choose another one.");		
							}
												
							Staff staffEntity = this.modelMapper.map(staff,Staff.class);
							
							staffEntity.setAdmin(adminDao.findAdminById(adminId));
							staffEntity.setMedicalStore(medicalStore);
							
							duplicate.checkDuplicateData(staffEntity,staffEntity.getEmail(),staffEntity.getPhoneNumber());

							return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.CREATED.value(),
																			"Staff Created...",
																			staffDao.saveStaff(staffEntity)),
																			HttpStatus.CREATED);
					}
					throw new UnauthorizedAdmin("This admin does not have permission to add staff in this medical...");
	}

	public ResponseEntity<ResponseStructure<Staff>> updateStaff(StaffDTO staff, long staffId , long adminId) {
		
		            Staff oldStaff = staffDao.findStaffById(staffId);
		            duplicate.checkDuplicateData(oldStaff,staff.getEmail(),staff.getPhoneNumber());

		            if(oldStaff.getAdmin().getAdminId() == adminId) {
		
					Staff staffEntity = this.modelMapper.map(staff,Staff.class);
					staffEntity.setStaffId(staffId);
					staffEntity.setAdmin(oldStaff.getAdmin());
					staffEntity.setMedicalStore(oldStaff.getMedicalStore());
					
					return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(),
																	"Staff updated...",
																	staffDao.saveStaff(staffEntity)),
																	HttpStatus.OK);
		            }
		            
		            throw new UnauthorizedAdmin("This Admin does not have permission to update this staff detail....");
	}

	public ResponseEntity<ResponseStructure<Staff>> findStaffById(long staffId) {
		
				  return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(),
																	"Staff Found...",
																	staffDao.findStaffById(staffId)),
																	HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Staff>>> findStaffByName(String staffName) {
			
				 return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(),
																	"Staff Found...",
																	staffDao.findStaffByName(staffName)),
																	HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Staff>> deleteStaffById(long staffId,long adminId) {
					
				Staff staff = staffDao.findStaffById(staffId);
			
				if(staff.getAdmin().getAdminId() == adminId) {
					
					return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.GONE.value(),
																		"Staff Found...",
																		staffDao.deleteStaffById(staffId)),
																		HttpStatus.GONE);
					
				}
				throw new UnauthorizedAdmin("This admin does not have permission to delete this staff information...");
						
	}
		

}
