package com.ftd.inventoryservice.domain.shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * <p>
 * The Class CommonUtils for Common codes.
 * </p>
 * 
 * @author Abhishek Mallick
 */
@Component
public class CommonUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Failure response.
	 *
	 * @param failureMessage the failure message
	 * @param response the response
	 * @return <code>ResponseEntity<Response></code>
	 */
	public static ResponseEntity<Response> failureResponse(String failureMessage, Object response) {

		Response apiResponse = Response.builder().status(Boolean.FALSE).statusCode(HttpStatus.OK.value())
				.message(failureMessage).response(response).build();
		return new ResponseEntity<>(apiResponse, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Success response.
	 *
	 * @param successMessage the success message
	 * @param response the response
	 * @return the response entity
	 */
	public static ResponseEntity<Response> successResponse(String successMessage, Object response) {

		Response apiResponse = Response.builder().status(Boolean.TRUE).statusCode(HttpStatus.OK.value())
				.message(successMessage).response(response).build();
		return new ResponseEntity<>(apiResponse, new HttpHeaders(), HttpStatus.OK);

	}

	/**
	 * Bad response.
	 *
	 * @param errorMessage the error message
	 * @return the response entity
	 */
	public static ResponseEntity<Response> badResponse(String errorMessage) {

		Response apiResponse = Response.builder().status(Boolean.FALSE).statusCode(HttpStatus.BAD_REQUEST.value())
				.message(errorMessage).build();
		return ResponseEntity.badRequest().body(apiResponse);
	}

	/**
	 * Method that iterates the validation errors and returns a comma separated
	 * error message.
	 *
	 * @param errors the errors
	 * @return the validation errors
	 */
	public StringBuilder getValidationErrors(Errors errors) {

		StringBuilder errorMessages = new StringBuilder();

		for (ObjectError objErr : errors.getAllErrors()) {
			if (!StringUtils.isEmpty(errorMessages))
				logger.debug("Error Message is : ", objErr.getDefaultMessage());
			errorMessages = errorMessages.append(objErr.getDefaultMessage()).append(CommonConstants.COMMA);
		}
		return errorMessages;
	}


	/**
	 * <p>
	 * Utility Method to return String Date of desired format from Date Object.
	 * </p>
	 *
	 * @param date the date
	 * @return the string
	 */
	public String convertDateToString(Date date) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (null == date)
			return CommonConstants.EMPTY;

		// To convert Date to String, use format method of SimpleDateFormat class.
		String strDate = dateFormat.format(date);

		logger.debug("Date converted to String: " + strDate);
		return strDate;
	}

}
