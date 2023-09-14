package com.medicalstore.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.AddressDAO;
import com.medicalstore.dao.AdminDAO;
import com.medicalstore.dao.MedicalStoreDAO;
import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.dao.StaffDAO;
import com.medicalstore.dto.MedicalStoreDTO;
import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Staff;
import com.medicalstore.exception.AddressAlreadyAssigned;
import com.medicalstore.exception.UnauthorizedAdmin;

@Service
public class MedicalStoreService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MedicalStoreDAO medicalDao;

	@Autowired
	private AdminDAO adminDao;

	@Autowired
	private AddressDAO addressDao;

	@Autowired
	private StaffDAO staffDao;

	@Autowired
	private MedicineDAO medicineDao;

	@Autowired
	private CheckDuplicateEntry duplicate;

	public ResponseEntity<ResponseStructure<MedicalStore>> saveMedicalStore(MedicalStoreDTO medicalStore, long adminId,
			long addressId) {

		List<MedicalStore> listMedical = medicalDao.getAllMedicalStore();

		for (MedicalStore mediStore : listMedical) {

			if (mediStore.getAddress().getAddressId() == addressId) {
				throw new AddressAlreadyAssigned("Address already assigened another medical Store..");
			}
		}

		MedicalStore medical = this.modelMapper.map(medicalStore, MedicalStore.class);
		medical.setAdmin(adminDao.findAdminById(adminId));
		medical.setAddress(addressDao.findAddressById(addressId));

		duplicate.checkDuplicateData(medical, null, medical.getManagerPhoneNumber());

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.CREATED.value(), "MedicalStore created...",
				medicalDao.saveMedicalStore(medical)), HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<MedicalStore>> updateMedicalStore(MedicalStoreDTO medicalStore,
			long medicalStoreId, long adminId) {

		MedicalStore oldMedical = medicalDao.findMedicalStoreById(medicalStoreId);
		duplicate.checkDuplicateData(oldMedical, null, medicalStore.getManagerPhoneNumber());

		if (oldMedical.getAdmin().getAdminId() == adminId) {

			MedicalStore medical = this.modelMapper.map(medicalStore, MedicalStore.class);
			medical.setStoreId(medicalStoreId);
			medical.setAdmin(oldMedical.getAdmin());
			medical.setAddress(oldMedical.getAddress());

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "MedicalStore updated...",
					medicalDao.saveMedicalStore(medical)), HttpStatus.OK);
		}

		throw new UnauthorizedAdmin("Admin does not have permission to update this medical....");

	}

	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStoreById(long medicalStoreId) {

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(), "MedicalStore found...",
				medicalDao.findMedicalStoreById(medicalStoreId)), HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStoreByName(String medicalStoreName) {

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(), "MedicalStore found...",
				medicalDao.findMedicalStoreByName(medicalStoreName)), HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> deleteMedicalStoreById(long medicalStoreId, long adminId) {

		MedicalStore oldMedical = medicalDao.findMedicalStoreById(medicalStoreId);

		if (oldMedical.getAdmin().getAdminId() == adminId) {

			List<Staff> staffList = staffDao.getAllStaff();

			for (Staff staff : staffList) {

				if (staff.getMedicalStore().getStoreId() == medicalStoreId)
					staffDao.deleteStaffById(staff.getStaffId());

			}

			medicineDao.deleteMedicineByMedicalStore(oldMedical);
			oldMedical = medicalDao.deleteMedicalStoreById(medicalStoreId);

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.GONE.value(),
					"MedicalStore as well as all MedicalStore related staff and medicine deleted sucessfully...",
					oldMedical), HttpStatus.GONE);
		}

		throw new UnauthorizedAdmin("Admin does not have permission to delete this medical....");
	}

}