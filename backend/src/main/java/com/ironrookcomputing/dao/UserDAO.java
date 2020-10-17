package com.ironrookcomputing.dao;

import java.util.Set;

import com.ironrookcomputing.hibernate.beans.User;

public interface UserDAO {
	public User addUser(User user);
	public User getUser(User user);
	public Set<User> getUsers();
	public User updateUser(User user);
	public void deleteUser(User user);
}
