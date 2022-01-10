package com.cardmanagementsystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cardmanagementsystem.model.CardDetails;

@Transactional
@Repository
public class CardDao {
	@Autowired
	private SessionFactory sessionFactory;

	public CardDetails saveCard(CardDetails cardDetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(cardDetails);
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return cardDetails;

	}

	public CardDetails findCardById(Integer userId) {
		Session session = sessionFactory.openSession();

		CardDetails dbCard = null;

		try {
			Transaction tx = session.beginTransaction();

			dbCard = (CardDetails) session.get(CardDetails.class, userId);
			tx.commit();
			if (dbCard != null) {
				return dbCard;
			} else {
				return null;
			}

		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	public List<CardDetails> getAllCards() {
		Session session = sessionFactory.openSession();

		List<CardDetails> allDbCardDetails = null;

		try {
			Transaction tx = session.beginTransaction();
			 allDbCardDetails =  session.createCriteria(CardDetails.class).list();
			tx.commit();
			if (allDbCardDetails != null) {
				return allDbCardDetails;
			} else {
				return null;
			}

		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}

		return allDbCardDetails;
	}
}
