package com.cardmanagementsystem.dao;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cardmanagementsystem.model.UserDetails;

@Transactional
@Repository
public class UserDao {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	public UserDetails saveUser(UserDetails userDetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer isSuccess=0;
		try {
			tx = session.beginTransaction();
			isSuccess = (Integer) session.save(userDetails);
			tx.commit();
			if (isSuccess >= 1) {
				return userDetails;
			} 
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	public UserDetails findUserById(Integer userId) {
		Session session = sessionFactory.openSession();
		UserDetails dbUser = null;
		try {
			Transaction tx = session.beginTransaction();
			dbUser =  session.get(UserDetails.class, userId);
			tx.commit();
			if (dbUser != null) {
				return dbUser;
			} 
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
}
