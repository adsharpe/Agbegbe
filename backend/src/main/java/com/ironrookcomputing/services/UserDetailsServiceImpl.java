package com.ironrookcomputing.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ironrookcomputing.dao.UserDAO;
import com.ironrookcomputing.hibernate.dao.UserHibernateDAO;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UserDAO userDAO = new UserHibernateDAO();
}
