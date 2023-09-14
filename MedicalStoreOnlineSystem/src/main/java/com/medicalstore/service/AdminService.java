package com.medicalstore.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.AdminDAO;
import com.medicalstore.dto.AdminDTO;
import com.medicalstore.entity.Admin;
import com.medicalstore.exception.EmailNotFoundException;
import com.medicalstore.exception.FailToForgotPasswordException;
import com.medicalstore.exception.InvalidUsernameAndPassword;

@Service
public class AdminService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminDAO adminDao;

	@Autowired
	private CheckDuplicateEntry duplicate;

	public ResponseEntity<ResponseStructure<AdminDTO>> saveAdmin(Admin admin) {

		duplicate.checkDuplicateData(admin, admin.getAdminEmail(), admin.getPhoneNumber());

		AdminDTO adminDto = this.modelMapper.map(adminDao.saveAdmin(admin), AdminDTO.class);

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.CREATED.value(), "Admin Successfully Saved..", adminDto),
				HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<AdminDTO>> updateAdmin(Admin admin, long adminId) {

		duplicate.checkDuplicateData(adminDao.findAdminById(adminId), admin.getAdminEmail(), admin.getPhoneNumber());

		admin.setAdminId(adminId);
		AdminDTO adminDto = this.modelMapper.map(adminDao.saveAdmin(admin), AdminDTO.class);

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.OK.value(), "Admin Successfully updated..", adminDto),
				HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<AdminDTO>> findAdminById(long adminId) {

		AdminDTO adminDto = this.modelMapper.map(adminDao.findAdminById(adminId), AdminDTO.class);

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.FOUND.value(), "Admin Successfully Found....", adminDto),
				HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<AdminDTO>> login(String email, String password) {

		Admin admin = adminDao.findAdminByEmail(email);

		if (admin.getPassword().equals(password)) {

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "Welcome login successfully...",
					this.modelMapper.map(admin, AdminDTO.class)), HttpStatus.OK);
		}
		throw new InvalidUsernameAndPassword("Admin username and password invalid...");

	}

	public ResponseEntity<ResponseStructure<AdminDTO>> forgotPassword(String email, String newPassword,
			String adminPhone) {

		Admin admin = adminDao.findAdminByEmail(email);

		if (admin == null)
			throw new EmailNotFoundException("Your specified email admin not found...!");

		if (admin.getPhoneNumber().equals(adminPhone)) {
			admin.setPassword(newPassword);

			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "Passward found..",
					this.modelMapper.map(adminDao.saveAdmin(admin), AdminDTO.class)), HttpStatus.OK);

		}

		throw new FailToForgotPasswordException("PhoneNumber is not register....");
	}

}