package com.ironrookcomputing.hibernate.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ironrookcomputing.dao.UserDAO;
import com.ironrookcomputing.hibernate.beans.User;
import com.ironrookcomputing.utils.HibernateUtil;
import com.ironrookcomputing.utils.LogUtil;

public class UserHibernateDAO implements UserDAO {

	@Override
	public User addUser(User user) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User getUser(User user) {
		Session session = hibernateInstance.getSession();
		User newUser = null;
		
		try {
			newUser = session.get(User.class, user.getId());
				
			System.out.println("newUser=" + newUser);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
		return newUser;
	}

	@Override
	public Set<User> getUsers() {
		Session session = hibernateInstance.getSession();
		String queryString = "FROM User";
		Set<User> userSet = new HashSet<User>();
		
		try {
			Query<User> query = session.createQuery(queryString, User.class);
			List<User> userList = query.getResultList();
			
			userSet.addAll(userList);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
		return userSet;
	}

	@Override
	public User updateUser(User user) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public void deleteUser(User user) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
	}

	private HibernateUtil hibernateInstance = HibernateUtil.getInstance();
}
