package com.cardmanagementsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
public class AddressDaoTest {
	@Mock
	private Session session;
	@Mock
	private SessionFactory sessionFactory;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	@BeforeEach
	public void setUp() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		if (sessionFactory == null) {
			session = sessionFactory.getCurrentSession();
		}
	}

	@Test
	void testSaveAddress() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			addressDetails = (AddressDetails) session.save(addressDetails);
			tx.commit();
			AddressDetails getFromDb = addressDao.findAddressByUserId(addressDetails.getUserId());
			assertEquals(addressDetails, getFromDb);
		} catch (Exception e) {
		}
	}

	@Test
	void testSaveAddressExceptionCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				null, "mancherial", "telangana", "india");
		try {
			addressDetails = addressDao.saveAddress(addressDetails);
			assertEquals(null, addressDetails);
		} catch (Exception e) {
		}
	}

	@Test
	void testFindAddressByUserId() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				"504789", "mancherial", "telangana", "india");
		addressDetails = addressDao.saveAddress(addressDetails);
		AddressDetails getFromDb = addressDao.findAddressByUserId(addressDetails.getUserId());
		assertEquals(addressDetails.getUserId(), getFromDb.getUserId());
	}

	@Test
	void testFindAddressByUserIdErrorCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		AddressDetails addressDetails = new AddressDetails(userDetails.getUserId(), "YAPAL", "mandamarri", "mancherial",
				null, "mancherial", "telangana", "india");
		addressDetails = addressDao.saveAddress(addressDetails);
		AddressDetails getFromDb = addressDao.findAddressByUserId(null);
		assertEquals(null, getFromDb);
	}
}
