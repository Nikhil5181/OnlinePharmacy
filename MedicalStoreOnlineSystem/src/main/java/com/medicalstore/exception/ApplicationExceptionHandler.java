package com.medicalstore.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medicalstore.config.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to Find",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNameNotFoundException(NameNotFoundException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to Find",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(UnauthorizedAdmin.class)
	public ResponseEntity<ResponseStructure<String>> handleUnauthorizedAdminException(UnauthorizedAdmin exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to perform operation..",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(UnauthorizedStaff.class)
	public ResponseEntity<ResponseStructure<String>> handleUnauthorizedStaffException(UnauthorizedStaff exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to perform operation....",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(AddressAlreadyAssigned.class)
	public ResponseEntity<ResponseStructure<String>> handleAddressAlreadyAssigned(AddressAlreadyAssigned exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Address Alredy used",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MedicineDetailNotFound.class)
	public ResponseEntity<ResponseStructure<String>> handleMedicineDetailNotFound(MedicineDetailNotFound exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"please send data in url like medicineID:medicineQty...",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MedicineNotAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleMedicineNotAvailableException(MedicineNotAvailableException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"please enter valid Quantity...",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AlreadyAddStaffException.class)
	public ResponseEntity<ResponseStructure<String>> handleAlreadyAddStaffException(AlreadyAddStaffException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"fail to save...",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ResponseStructure<Object>> handleDuplicateEntryException(DuplicateEntryException exception){

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.ALREADY_REPORTED.value(),
															exception.getMessage(),
															exception.getErrorMap()),
															HttpStatus.ALREADY_REPORTED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

			Map<String,String> errorData = new HashMap<>();
 		
			List<ObjectError> listError =  exception.getAllErrors();
			
			for(ObjectError error : listError) {
				String field = ((FieldError)error).getField();
				String message = ((FieldError)error).getDefaultMessage();
				
				errorData.put(field, message);
			}
			
			return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
																"Invalid Data...!",
																errorData),
																HttpStatus.BAD_REQUEST);
			
	}

	// @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	// public ResponseEntity<ResponseStructure<String>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
		
	// 	return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
	// 														"Duplicate Data..",
	// 														exception.getMessage()),
	// 														HttpStatus.BAD_REQUEST);
	// }
	
	@ExceptionHandler(InvalidUsernameAndPassword.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidUsernameAndPassword(InvalidUsernameAndPassword exception){

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to login",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerDoesNotHaveBookingException.class)
	public ResponseEntity<ResponseStructure<String>> handleCustomerDoesNotHaveBookingException(CustomerDoesNotHaveBookingException exception){

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
														"Fail to find",
														exception.getMessage()),
														HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookingAlredyDeliverd.class)
	public ResponseEntity<ResponseStructure<String>> handleBookingAlredyDeliverd(BookingAlredyDeliverd exception){

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
														"Fail to cancelled booking",
														exception.getMessage()),
														HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookingAlreadyCancelled.class)
	public ResponseEntity<ResponseStructure<String>> handleBookingAlredyCancelled(BookingAlreadyCancelled exception){

		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
														"Fail to cancelled booking",
														exception.getMessage()),
														HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookingCantBeCancelled.class)
	public ResponseEntity<ResponseStructure<String>> handleBookingCantBeCancelled(BookingCantBeCancelled exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
													"Fail to cancel booking..",
													exception.getMessage()),
													HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmailNoFoundException(EmailNotFoundException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to find email..",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FailToForgotPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handleFailToForgotPasswordException(FailToForgotPasswordException exception){
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
															"Fail to reset password..",
															exception.getMessage()),
															HttpStatus.BAD_REQUEST);
	}
}