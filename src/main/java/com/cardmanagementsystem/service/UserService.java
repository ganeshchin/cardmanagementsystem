package com.cardmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.AddressDao;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@Service
public class UserService {
	private static final String ERROR_STRING="error";
	private static final String SUCCESS_STRING="success";
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;

	public Response createUser(UserDetails userDetails) {
		Response response = new Response();
		List<String> errors = new ArrayList<>();
		try {

			if (!userDetails.getKycStatus().equals("DONE") && !userDetails.getKycStatus().equals("NOT_DONE")) {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.BAD_REQUEST);
				errors.add("please provide proper valid status");
				response.setErrors(errors);
				return response;
			}

			String pan = userDetails.getPan();
			String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(pan);
			if (!m.matches()) {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.BAD_REQUEST);
				errors.add("please provide  proper pan details");
				response.setErrors(errors);
				return response;
			}

			String pwd = userDetails.getPassword();
			userDetails.setPassword(Base64.encodeBase64(pwd.getBytes()).toString()); //NOSONAR
			if (!userDetails.getTitle().equals("Mr") && !userDetails.getTitle().equals("Mrs")
					&& !userDetails.getTitle().equals("Miss")) {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.BAD_REQUEST);
				errors.add("please provide  proper title details");
				response.setErrors(errors);
				return response;
			}

			userDetails = userDao.saveUser(userDetails);

			if (userDetails != null) {
				response.setStatusCode("00");
				response.setStatusDescription(SUCCESS_STRING);
				response.setUserDetails(userDetails);
				response.setStatus(HttpStatus.CREATED);
				return response;

			} else {
				response.setStatusCode("01");
				response.setStatusDescription(ERROR_STRING);
				response.setUserDetails(null);
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				return response;

			}
		} catch (Exception ex) {
			response.setStatusCode("01");
			response.setStatusDescription(ex.getMessage());
			response.setUserDetails(null);
			return response;

		}
	}

	public Response getUserAndAddressDetailsById(int userId) {
		Response response = new Response();
		UserDetails dbUserDetails = null;
		AddressDetails dbAddressDetails = null;
		dbUserDetails = userDao.findUserById(userId);
		dbAddressDetails = addressDao.findAddressByUserId(userId);
		List<String> errors = new ArrayList<>();

		if (dbUserDetails != null && dbAddressDetails != null) {
			response.setStatusCode("00");
			response.setStatusDescription(SUCCESS_STRING);
			response.setAddressDetails(dbAddressDetails);
			response.setUserDetails(dbUserDetails);
			response.setStatus(HttpStatus.CREATED);
			return response;

		} else {
			response.setStatusCode("01");
			response.setStatusDescription(ERROR_STRING);
			response.setUserDetails(dbUserDetails);
			response.setStatus(HttpStatus.NOT_FOUND);
			if (dbUserDetails == null) {
				errors.add("user data not found in usertable");
				response.setUserDetails(dbUserDetails);
			} else {
				errors.add("user data not found in address table");

			}

			response.setErrors(errors);
			return response;

		}

	}

}
