package com.ironrookcomputing.services;

import org.springframework.stereotype.Service;

import com.ironrookcomputing.dao.LoginDAO;
import com.ironrookcomputing.dao.UserDAO;
import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.hibernate.beans.User;
import com.ironrookcomputing.hibernate.dao.LoginHibernateDAO;
import com.ironrookcomputing.hibernate.dao.UserHibernateDAO;

@Service
public class UserService {
	public User addUser(Login login) {
		User user = userDao.addUser(login.getUser());
		
		login.setUser(user);
		loginDao.updateLogin(login);
		
		return user;
	}
	
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	public User getUser(String username) {
		return loginDao.getLoginByUsername(username).getUser();
	}

	public User getUser(User user) {
		return userDao.getUser(user);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public void deleteUser(User user) {
		loginDao.deleteLogin(loginDao.getLoginByUser(user));
		userDao.deleteUser(user);
	}
	
	private UserDAO userDao = new UserHibernateDAO();
	private LoginDAO loginDao = new LoginHibernateDAO();
}
