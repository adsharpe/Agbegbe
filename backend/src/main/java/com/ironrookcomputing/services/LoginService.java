package com.ironrookcomputing.services;

import java.util.Date;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ironrookcomputing.beans.LoginData;
import com.ironrookcomputing.dao.LoginDAO;
import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.hibernate.dao.LoginHibernateDAO;
import com.ironrookcomputing.utils.LogUtil;
import com.ironrookcomputing.utils.TokenUtil;

@Service
public class LoginService {
	public LoginService() {
		super();
	}
	
	public Login login(LoginData loginData) {
		Login login = new Login(loginData);
		
		System.out.println("loginData=" + loginData);
		
		try {
			// Get login from login data
			login = loginDao.getLogin(login);
			System.out.println("Found login: '" + login.getUsername() + "'" + "; with password: '" + login.getPassword() + "'");
			
			
			System.out.println("password verified");
			// Get token for login
			login = createAuthorization(login);
			updateLogin(login);
			
			// Sanitize password from login
			login = sanitizeLogin(login);
			
			return login;
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginService.class);
		}
		return null;
	}
	
	public Login sanitizeLogin(Login login) {
		login.setPassword("");
		return login;
	}
	
	public Login addLogin(Login login) {
		return loginDao.addLogin(login);
	}
	
	public Login getLogin(Login login) {
		return loginDao.getLogin(login);
	}
	
	public Set<Login> getLogins() {
		return loginDao.getLogins();
	}
	
	public Login updateLogin(Login login) {
		return loginDao.updateLogin(login);
	}
	
	public void deleteLogin(Login login) {
		loginDao.deleteLogin(login);
	}

	public Login createAuthorization(Login login) {
		try {
			String loginname = login.getUsername();
			
			if(loginname != null) {
				login = tokenUtil.createToken(login);
				login.setTokenDateTime(new Date());
			}
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginService.class);
		}
		
		return login;
	}
	
	public Login refreshToken(String refresh) {
		Login login = null;
		
		try {
			login = loginDao.getLoginByRefresh(refresh);
			
			login = tokenUtil.createToken(login);
			updateLogin(login);
			
			// Sanitize password from login
			login = sanitizeLogin(login);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginService.class);
		}
		
		return login;
	}
	
	public void deleteAuthorization(String token) {
		Login login = loginDao.getLoginByToken(token);
		
		login.setTokenValue(null);
		login.setTokenRefresh(null);
		login.setTokenDateTime(null);
		
		loginDao.updateLogin(login);
	}
	
	public Boolean isTokenExpired(String value) {
		Date date = new Date();
		Boolean isExpired = true;
		long currentTime = date.getTime();
		
		try {
			long expiredTime = loginDao.getLoginByToken(value).getTokenDateTime().getTime() + tokenTimeLimit;
			
			if(expiredTime > currentTime) {
				isExpired = false;
			}
			
			System.out.println("currentTime=" + currentTime);
			System.out.println("expiredTime=" + expiredTime);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, LoginService.class);
		}
		
		System.out.println(" Token expired: " + isExpired);
		
		
		return isExpired;
	}
	
	public long getTokenTimeLimit() {
		return tokenTimeLimit;
	}

	public void setTokenTimeLimit(long tokenTimeLimit) {
		this.tokenTimeLimit = tokenTimeLimit;
	}
	
	@Autowired
	private TokenUtil tokenUtil;
	
	// TODO: encode password on registration
	
	// TODO: create verification token, create verification link, & send verification email
	//       to allow login to activate account
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private LoginDAO loginDao = new LoginHibernateDAO();
	
	private long tokenTimeLimit = 300000; // Default to 5 minute token time limit
}
