package com.cardmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.AddressDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
 class UserControllerTest {
	@Autowired
	private UserController userController;
	@Autowired
	private AddressDao addressDao;

	@Test
	void TestsaveUser() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		Response response = userController.saveUser(userDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void TestgetUserAndAddressDetailsById() {
		UserDetails userDetails = new UserDetails(1, "8008406407", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userController.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDao.saveAddress(addressDetails);
		Response response = userController.getUserAndAddressDetailsById(userDetails.getUserId());
		assertEquals(true, response.getStatusCode().equals("00"));

	}

}
