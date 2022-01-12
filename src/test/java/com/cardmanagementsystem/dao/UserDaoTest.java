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
public class UserDaoTest {
	@Mock
	private Session session;
	@Mock
	private SessionFactory sessionFactory;
	@Autowired
	private UserDao userDao;
	@BeforeEach
	public void setUp() {
	    when(sessionFactory.getCurrentSession()).thenReturn(session);
	    if(sessionFactory == null) {
	        session = sessionFactory.getCurrentSession();
	    }
	}
	
	@Test
	public void	testSaveUser(){
			UserDetails userDetails=new UserDetails(1, "9985758068", "566566656655", "andrew", "CPMPK2943H", "Mrs", "DONE");
			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				userDetails=(UserDetails) session.save(userDetails);
				tx.commit();
				UserDetails getFromDb = userDao.findUserById(userDetails.getUserId());
				assertEquals(userDetails, getFromDb);
			} catch (Exception e) {
			}
		}


}
