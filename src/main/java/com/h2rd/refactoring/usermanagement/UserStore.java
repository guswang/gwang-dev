package com.h2rd.refactoring.usermanagement;

import java.util.List;

import com.h2rd.refactoring.exception.UserException;

public interface UserStore {

	public void addUser(User user) throws UserException;
	public List<User> getUsers();
	public void deleteUser(String name);
	public User updateUser(User userToUpdate) throws UserException;
	public User findUser(String name);
}
