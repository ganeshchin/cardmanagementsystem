package com.cardmanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cardmanagementsystem.model.CardDetails;

@Transactional
@Repository
public class CardDao {
	private static final Logger LOGGER=LoggerFactory.getLogger(CardDao.class);
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
			LOGGER.error(e.getMessage());
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
			dbCard =  session.get(CardDetails.class, userId);
			tx.commit();
			if (dbCard != null) {
				return dbCard;
			} 
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	public List<CardDetails> getAllCards() {
		Session session = sessionFactory.openSession();
		List<CardDetails> allDbCardDetails = new ArrayList<>();
		try {
			Transaction tx = session.beginTransaction();
			CriteriaQuery<CardDetails> cq = session.getCriteriaBuilder().createQuery(CardDetails.class);
			cq.from(CardDetails.class);
			allDbCardDetails = session.createQuery(cq).getResultList();
			tx.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return allDbCardDetails;
	}
}
