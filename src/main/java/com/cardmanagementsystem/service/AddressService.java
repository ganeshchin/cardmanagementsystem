package com.cardmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.AddressDao;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@Service
public class AddressService {
	private static final String ERROR_STRING = "error";
	private static final String SUCCESS_STRING = "success";
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;
	List<String> errors = new ArrayList<>();

	public Response saveAddress(AddressDetails address) {
		Response response = new Response();
		AddressDetails dbAddressDetails = null;
		UserDetails userDetails = null;
		try {
			userDetails = userDao.findUserById(address.getUserId());
			if (userDetails == null) {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.BAD_REQUEST);
				errors.add("given userid is not available in users table");
				response.setErrors(errors);
				return response;
			}
			dbAddressDetails = addressDao.saveAddress(address);
			if (dbAddressDetails != null) {
				response.setStatusCode("00");
				response.setStatusDescription(SUCCESS_STRING);
				response.setUserDetails(userDetails);
				response.setStatus(HttpStatus.CREATED);
				response.setAddressDetails(dbAddressDetails);
				return response;
			} else {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setStatusCode("01");
			response.setStatusDescription(ERROR_STRING);
			response.setUserDetails(null);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
