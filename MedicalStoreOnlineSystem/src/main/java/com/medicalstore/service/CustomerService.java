package com.medicalstore.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.AddressDAO;
import com.medicalstore.dao.CustomerDAO;
import com.medicalstore.dto.CustomerDTO;
import com.medicalstore.entity.Address;
import com.medicalstore.entity.Customer;
import com.medicalstore.exception.AddressAlreadyAssigned;
import com.medicalstore.exception.EmailNotFoundException;
import com.medicalstore.exception.FailToForgotPasswordException;
import com.medicalstore.exception.InvalidUsernameAndPassword;

@Service
public class CustomerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressDAO addressDao;

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CheckDuplicateEntry duplicate;

	public ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomer(Customer customer, long addressId) {

		duplicate.checkDuplicateData(customer, customer.getEmail(), customer.getPhoneNumber());

		Address address = addressDao.findAddressById(addressId);
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(address);

		if (customerDao.findCustomerByAddress(address) != null)
			throw new AddressAlreadyAssigned("Please select another address.....!");

		customer.setAddress(listAddress);
		CustomerDTO customerDto = this.modelMapper.map(customerDao.saveCustomer(customer), CustomerDTO.class);

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.CREATED.value(), "Customer saved...", customerDto),
				HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> updateCustomer(Customer customer, long customerId,
			long addressId) {

		Customer oldCustomer = customerDao.findCutomerById(customerId);
		duplicate.checkDuplicateData(oldCustomer, customer.getEmail(), customer.getPhoneNumber());

		List<Address> listAddress = oldCustomer.getAddress();
		listAddress.add(addressDao.findAddressById(addressId));
		customer.setCustomerId(customerId);
		customer.setAddress(listAddress);
		CustomerDTO customerDto = this.modelMapper.map(customerDao.saveCustomer(customer), CustomerDTO.class);

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "Customer updated...", customerDto),
				HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> findCustomerById(long customerId) {

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.FOUND.value(), "Customer found...",
						this.modelMapper.map(customerDao.findCutomerById(customerId), CustomerDTO.class)),
				HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> deleteCustomerById(long customerId) {

		Customer customer = customerDao.findCutomerById(customerId);
		customer.setAddress(null);
		CustomerDTO customerDto = this.modelMapper.map(customerDao.deleteCustomerById(customerId), CustomerDTO.class);

		return new ResponseEntity<>(
				new ResponseStructure<>(HttpStatus.ACCEPTED.value(), "Customer deleted...", customerDto),
				HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> login(String customerEmail, String customerPassword) {

		Customer customer = customerDao.findCustomerByEmail(customerEmail);

		if (!(customer != null && customer.getPassword().equals(customerPassword)))
			throw new InvalidUsernameAndPassword("Invlaid username or password....");

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(), "Welcome login successfully",
				this.modelMapper.map(customer, CustomerDTO.class)), HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<CustomerDTO>> forgotPassword(String customerEmail, String newPassword,
			String phoneNumber) {

		Customer customer = customerDao.findCustomerByEmail(customerEmail);

		if (customer == null)
			throw new EmailNotFoundException("Your specified email customer not found....");

		if (customer.getPhoneNumber().equals(phoneNumber)) {

			customer.setPassword(newPassword);
			return new ResponseEntity<>(
					new ResponseStructure<>(HttpStatus.OK.value(), "Password successfully reset..",
							this.modelMapper.map(customerDao.saveCustomer(customer), CustomerDTO.class)),
					HttpStatus.OK);

		}

		throw new FailToForgotPasswordException("Customer mobile no invalid or not resgister....");

	}
}