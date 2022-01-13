package com.cardmanagementsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.model.CardDetails;
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
public class CardDaoTest {
	private Long futerDAtemillis = 1673461800000L;
	@Mock
	private Session session;
	@Mock
	private SessionFactory sessionFactory;
	@Autowired
	private CardDao cardDao;
	@Autowired
	private UserDao userDao;

	@BeforeEach
	void setUp() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		if (sessionFactory == null) {
			session = sessionFactory.getCurrentSession();
		}
	}

	@Test
	void testSaveCard() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cardDetails = (CardDetails) session.save(cardDetails);
			tx.commit();
			CardDetails getFromDb = cardDao.findCardById(cardDetails.getUserId());
			assertEquals(cardDetails, getFromDb);
		} catch (Exception e) {
		}
	}

	@Test
	void testSaveUserErrorCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), null, 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		try {
			cardDetails = cardDao.saveCard(cardDetails);
			assertEquals(null, cardDetails);
		} catch (Exception e) {
		}
	}

	@Test
	void testFindCardById() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDetails = cardDao.saveCard(cardDetails);
		CardDetails getFromDb = cardDao.findCardById(cardDetails.getUserId());
		assertEquals(cardDetails.getUserId(), getFromDb.getUserId());
	}

	@Test
	void testFindCardByIdErrorCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDetails = cardDao.saveCard(cardDetails);
		CardDetails getFromDb = cardDao.findCardById(null);
		assertEquals(null, getFromDb);
	}

	@Test
	void tesGetAllCards() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDetails = userDao.saveUser(userDetails);
		CardDetails cardDetails = new CardDetails(userDetails.getUserId(), "1234566666", 5, "CREDIT", "ACTIVE",
				new Date(futerDAtemillis));
		cardDetails = cardDao.saveCard(cardDetails);
		List<CardDetails> allCards = cardDao.getAllCards();
		assertEquals(true, allCards.size() >= 1);

	}

}