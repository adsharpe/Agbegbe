package com.ironrookcomputing.dao;

import java.util.Set;

import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.hibernate.beans.User;

public interface LoginDAO {
	public Login addLogin(Login login);
	public Login getLogin(Login login);
	public Login getLoginByUsername(String username);
	public Login getLoginByUser(User user);
	public Login getLoginByToken(String token);
	public Login getLoginByRefresh(String refresh);
	public Set<Login> getLogins();
	public Login updateLogin(Login login);
	public void deleteLogin(Login login);

}
