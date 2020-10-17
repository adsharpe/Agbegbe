package com.ironrookcomputing.hibernate.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ironrookcomputing.dao.LoginDAO;
import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.hibernate.beans.User;
import com.ironrookcomputing.utils.HibernateUtil;
import com.ironrookcomputing.utils.LogUtil;

public class LoginHibernateDAO implements LoginDAO {

	@Override
	public Login addLogin(Login login) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(login);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}

	@Override
	public Login getLogin(Login login) {
		Session session = hibernateInstance.getSession();
		Login newLogin = null;
		
		try {
			if(login.getId() != 0) {
				newLogin = session.get(Login.class, login.getId());
			} else {
				String queryString = "FROM Login l WHERE l.username=:username AND l.password=:password";
				Query<Login> query = session.createQuery(queryString, Login.class);
				query.setParameter("username", login.getUsername());
				query.setParameter("password", login.getPassword());
				newLogin = query.uniqueResult();
				
				System.out.println("newLogin=" + newLogin);
			}
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return newLogin;
	}
	
	@Override
	public Login getLoginByUsername(String username) {
		Session session = hibernateInstance.getSession();
		Login login = null;
		
		try {
			String queryString = "FROM Login l WHERE l.username=:username";
			Query<Login> query = session.createQuery(queryString, Login.class);
			query.setParameter("username", username);
			login = query.uniqueResult();
			
			System.out.println("login=" + login);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}
	
	@Override
	public Login getLoginByUser(User user) {
		Session session = hibernateInstance.getSession();
		Login login = null;
		
		try {
			String queryString = "FROM Login l WHERE l.user=:user";
			Query<Login> query = session.createQuery(queryString, Login.class);
			query.setParameter("user", user);
			login = query.uniqueResult();
			
			System.out.println("login=" + login);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}

	@Override
	public Login getLoginByToken(String token) {
		Session session = hibernateInstance.getSession();
		Login login = null;
		
		try {
			String queryString = "FROM Login l WHERE l.tokenValue=:token";
			Query<Login> query = session.createQuery(queryString, Login.class);
			query.setParameter("token", token);
			login = query.uniqueResult();
			
			System.out.println("login=" + login);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}

	@Override
	public Login getLoginByRefresh(String refresh) {
		Session session = hibernateInstance.getSession();
		Login login = null;
		
		try {
			String queryString = "FROM Login l WHERE l.tokenRefresh=:refresh";
			Query<Login> query = session.createQuery(queryString, Login.class);
			query.setParameter("refresh", refresh);
			login = query.uniqueResult();
			
			System.out.println("login=" + login);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}

	@Override
	public Set<Login> getLogins() {
		Session session = hibernateInstance.getSession();
		String queryString = "FROM Login";
		Set<Login> loginSet = new HashSet<Login>();
		
		try {
			Query<Login> query = session.createQuery(queryString, Login.class);
			List<Login> loginList = query.getResultList();
			
			loginSet.addAll(loginList);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return loginSet;
	}

	@Override
	public Login updateLogin(Login login) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(login);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
		return login;
	}

	@Override
	public void deleteLogin(Login login) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(login);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, LoginHibernateDAO.class);
		} finally {
			session.close();
		}
	}

	private HibernateUtil hibernateInstance = HibernateUtil.getInstance();
}
