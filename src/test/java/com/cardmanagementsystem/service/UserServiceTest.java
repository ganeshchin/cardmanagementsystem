package com.cardmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.AddressDao;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;

	@Test
	void createUserTestCheckingtitle() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "df",
				"DONE");
		Response response = userService.createUser(userDetails);
		assertEquals(true, response.getErrors().contains("please provide  proper title details"));
	}

	@Test
	void createUserTestCheckingPAN() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "12345678", "Mrs", "DONE");
		Response response = userService.createUser(userDetails);
		assertEquals(true, response.getErrors().contains("please provide  proper pan details"));
	}

	@Test
	void createUserTestCheckingStatus() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"correct");
		Response response = userService.createUser(userDetails);
		assertEquals(true, response.getErrors().contains("please provide proper valid status"));
	}

	@Test
	void createUserSuccess() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		Response response = userService.createUser(userDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
		System.out.println(response);
	}

	@Test
	void createUserFailure() {
		UserDetails userDetails = new UserDetails(1, "80084064079999999", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		Response response = userService.createUser(userDetails);
		assertEquals(true, response.getStatusCode().equals("01"));
	}

	@Test
	void createUserErrorCase() {
		Response response = userService.createUser(null);
		assertEquals(true, response.getStatusCode().equals("01"));
	}

	@Test
	void testGetUserAndAddressDetailsById() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDetails = addressDao.saveAddress(addressDetails);
		Response response = userService.getUserAndAddressDetailsById(userDetails.getUserId());
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void testGetUserAndAddressDetailsByIdFailureAddress() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		Response response = userService.getUserAndAddressDetailsById(userDetails.getUserId());
		assertEquals(true, response.getErrors().contains("user data not found in address table"));
	}

	@Test
	void testGetUserAndAddressDetailsByIdFailureUser() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		Response response = userService.getUserAndAddressDetailsById(userDetails.getUserId() + 1);
		assertEquals(true, response.getErrors().contains("user data not found in usertable"));
	}
}
