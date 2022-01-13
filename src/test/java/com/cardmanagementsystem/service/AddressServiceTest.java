package com.cardmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
 class AddressServiceTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressService addressService;

	@Test
	void TestSaveAddress() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mr",
				"DONE");
		userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		Response response = addressService.saveAddress(addressDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void TestSaveAddressFailure() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mr",
				"DONE");
		userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"", "mancherial", "telangana", "india");
		Response response = addressService.saveAddress(addressDetails);
		assertEquals(true, response.getStatusCode().equals("01"));
	}

	@Test
	void TestSaveAddressFailureUserIdNotExit() {
		AddressDetails addressDetails = new AddressDetails(500, "YAPAL", "mandamarri", "mancherial", "", "mancherial",
				"telangana", "india");
		Response response = addressService.saveAddress(addressDetails);
		assertEquals(true, response.getStatusCode().equals("01"));
	}

	@Test
	void TestSaveAddressThrowEroor() {
		Response response = addressService.saveAddress(null);
		assertEquals(true, response.getStatusCode().equals("01"));
	}
}