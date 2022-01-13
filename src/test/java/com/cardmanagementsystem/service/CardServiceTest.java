package com.cardmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.AddressDao;
import com.cardmanagementsystem.dao.CardDao;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.CardDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
 class CardServiceTest {
	private Long futerDAtemillis = 1673461800000L;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CardService cardService;

	@Autowired
	private CardDao cardDao;

	@Test
	void getAllCardDetailsTestFailureCase() {
		Response response = cardService.getAllCardDetails();
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void cardTypeValidationTest() {
		CardDetails cardDetails = new CardDetails(23, "1234566666", 5, "CREDIT123", "ACTIVE",
				new Date(futerDAtemillis));
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getErrors().contains("please provide proper card type"));
	}

	@Test
	void cardStatusValidationTest() {
		CardDetails cardDetails = new CardDetails(23, "1234566666", 5, "CREDIT", "ACTIVE123",
				new Date(futerDAtemillis));
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getErrors().contains("please provide  proper card status"));
	}

	@Test
	void saveCarrdTestUserNotFound() {
		CardDetails cardDetails = new CardDetails(23, "1234566666", 5, "CREDIT", "ACTIVE", new Date(futerDAtemillis));
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getErrors().contains("given userid is not available in userstable"));
	}

	@Test
	void saveCardTestAddressNotFound() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getErrors().contains("given userid is not available in addresstable"));
	}

	@Test
	void saveUserTestErrorCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDao.saveAddress(addressDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "566566656655", 5, "CREDIT", "ACTIVE",
				new Date());
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getErrors().contains("data not inserted due to internal error"));
	}

	@Test
	void saveUserTestThorowErrorCase() {
		Response response = cardService.saveCard(null);
		assertEquals(true, response.getErrors().contains("internal server error"));
	}

	@Test
	void saveUserTestSuccessCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDao.saveAddress(addressDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234599999", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		Response response = cardService.saveCard(cardDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test()
	void getAllCardDetailsTestSuceessCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDao.saveAddress(addressDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "7878787787", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDao.saveCard(cardDetails);
		Response response = cardService.getAllCardDetails();
		assertEquals(true, response.getStatusCode().equals("00"));
	}

}
