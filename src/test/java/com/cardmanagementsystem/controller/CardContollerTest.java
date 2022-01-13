package com.cardmanagementsystem.controller;

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
 class CardContollerTest {
	private Long futerDAtemillis = 1673461800000L;
	@Autowired
	private CardContoller cardContoller;
	@Autowired
	private UserDao userDro;
	@Autowired
	private AddressDao addDao;

	@Autowired
	private CardDao cardDao;

	@Test
	void testSaveCard() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDro.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addDao.saveAddress(addressDetails);
		Response response = cardContoller.saveCard(cardDetails);
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void testGetCard() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDro.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDao.saveCard(cardDetails);
		Response response = cardContoller.getCard(cardDetails.getUserId());
		assertEquals(true, response.getStatusCode().equals("00"));
	}

	@Test
	void testgetAll() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDro.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDao.saveCard(cardDetails);
		Response response = cardContoller.getAll();
		assertEquals(true, response.getStatusCode().equals("00"));
	}

}
