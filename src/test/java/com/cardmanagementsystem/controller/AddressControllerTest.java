package com.cardmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
 class AddressControllerTest {
	@Autowired
	private AddressController addController;
	@Autowired
	private UserDao userDao;

	@Test
	void testSaveAddress() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		Response response = addController.saveAddress(addressDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
	}

}
