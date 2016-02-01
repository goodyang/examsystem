package com.goodyang.examsystem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goodyang.examsystem.hibernate.HibernateSessionFactory;
import com.goodyang.examsystem.po.Subject;
import com.goodyang.examsystem.util.Page;

public class SubjectDAOImpl implements SubjectDAO {

	@Override
	public void addSubject(Subject subject) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();//开启事务
			session.save(subject);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		HibernateSessionFactory.closeSession();
	}

	@Override
	public Subject findSubjectByTitle(String subjectTitle) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle=?");
		query.setString(0, subjectTitle);
		List<?> list = query.list();
		HibernateSessionFactory.closeSession();
		if(list.size() == 0) {
			return null;
		}else {
			return (Subject)list.get(0);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Subject> findSubjectByPage(Page page) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Subject");
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public int findSubjectCount() {
		Session session = HibernateSessionFactory.getSession();
		Query query  = session.createQuery("from Subject");
		List list = query.list();
		int count = list.size();
		HibernateSessionFactory.closeSession();
		return count;
	}

	@Override
	public Subject findSubjectByID(int subjectID) {
		Session session = HibernateSessionFactory.getSession();
		Subject subject = (Subject) session.get(Subject.class, subjectID);
		HibernateSessionFactory.closeSession();
		return subject;
	}

	@Override
	public void updateSubject(Subject subject) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(subject);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		HibernateSessionFactory.closeSession();
	}

	@Override
	public void deleteSubject(int subjectID) {
		Session session = HibernateSessionFactory.getSession();
		Subject subject = (Subject) session.get(Subject.class, subjectID);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(subject);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		HibernateSessionFactory.closeSession();
	}

	@Override
	public List<Subject> likeQueryByTitle(String subjectTitle, Page page) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title");
		query.setString("title", "%"+subjectTitle+"%");
		query.setMaxResults(page.getEveryPage());
		query.setFirstResult(page.getBeginIndex());
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public int findLinkQueryCount(String subjectTitle) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Subject as sub where sub.subjectTitle like : title");
		query.setString("title", "%"+subjectTitle+"%");
		List list = query.list();
		int count = list.size();
		HibernateSessionFactory.closeSession();
		return count;
	}

	@Override
	public List<Subject> randomFindsSubject(int number) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Subject as sub order by rand()");
		query.setMaxResults(number);
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
}
