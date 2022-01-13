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
import com.cardmanagementsystem.model.UserDetails;

@SpringBootTest
 class UserDaoTest {
	@Mock
	private Session session;
	@Mock
	private SessionFactory sessionFactory;
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
	void testSaveUser() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userDetails = (UserDetails) session.save(userDetails);
			tx.commit();
			UserDetails getFromDb = userDao.findUserById(userDetails.getUserId());
			assertEquals(userDetails, getFromDb);
		} catch (Exception e) {
		}
	}

	@Test
	void testSaveUserErrorCase() {
		UserDetails userDetails = new UserDetails(1, null, "566566656655", "andrew", "CPMPK2943H", "Mrs", "DONE123");
		try {
			userDetails = userDao.saveUser(userDetails);
			assertEquals(null, userDetails);
		} catch (Exception e) {
		}
	}

	@Test
	void testFindUserById() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		UserDetails dbsave = userDao.saveUser(userDetails);
		UserDetails getFromDb = userDao.findUserById(userDetails.getUserId());
		assertEquals(dbsave.getUserId(), getFromDb.getUserId());
	}

	@Test
	void testFindUserByIdFailureCase() {
		UserDetails userDetails = new UserDetails(1, "5678908909", "566566656655", "andrew", "CPMPK2943H", "Mrs",
				"DONE");
		userDao.saveUser(userDetails);
		UserDetails getFromDb = userDao.findUserById(null);
		assertEquals(null, getFromDb);
	}
}
