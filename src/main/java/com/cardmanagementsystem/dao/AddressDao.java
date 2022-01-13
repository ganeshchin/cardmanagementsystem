package com.cardmanagementsystem.dao;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cardmanagementsystem.model.AddressDetails;

@Transactional
@Repository
public class AddressDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	public AddressDetails saveAddress(AddressDetails address) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(address);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			LOGGER.error(e.getMessage());
			return null;
		} finally {
			session.close();
		}
		return address;
	}

	public AddressDetails findAddressByUserId(Integer userId) {
		Session session = sessionFactory.openSession();
		AddressDetails addressDetails = null;
		try {
			Transaction tx = session.beginTransaction();
			addressDetails = session.get(AddressDetails.class, userId);
			tx.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return addressDetails;
	}

}
