package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.h2rd.refactoring.exception.UserException;
import org.apache.log4j.Logger;

public class UserStoreImpl implements UserStore {

    final static Logger logger = Logger.getLogger(UserStoreImpl.class);
	//private static List<User> users = new ArrayList<User>();
    
    private List<User> users;
    
    public UserStoreImpl() {
    	users = new ArrayList<User>();
    }

	@Override
	public synchronized void addUser(User user) throws UserException {
		if (user == null) {
			throw new UserException("User data is null. Nothing to add!");
		}
		if (user.getRoles() == null || user.getRoles().size() == 0) {
			throw new UserException("User missing role! User must have at least one role");
		}
		if (users.contains(user)) {
			throw new UserException("User email already exists. Email must be unique!");
		}
		users.add(user);
		/*
		try {
			users.add(user);
		} catch (Exception e) {
			throw new UserException("Add User Exception:" + e.getMessage());
		}
		*/
	}

	@Override
	public List<User> getUsers() {
		return users;

	}

	@Override
	public synchronized void deleteUser(String userName) {
		for (Iterator<User> userIter = users.iterator(); userIter.hasNext();) {
			User aUser = userIter.next();
			if (aUser.getName().equalsIgnoreCase(userName)) {
				userIter.remove();
			}
		}

	}

	@Override
	public User updateUser(User userToUpdate) throws UserException {
		boolean isUserExist = false;
		if (userToUpdate.getRoles() == null || userToUpdate.getRoles().size() == 0) {
			throw new UserException("User missing role! User must have at least one role");
		}
		synchronized(this) {
		  for (User user : users) {
			if (user.getName().equalsIgnoreCase(userToUpdate.getName())) {
				if (users.contains(userToUpdate) && !user.getEmail().equalsIgnoreCase(userToUpdate.getEmail())) {
					throw new UserException("User email already exists. Email must be unique!");
				}
				user.setEmail(userToUpdate.getEmail());
				user.setRoles(userToUpdate.getRoles());
				isUserExist = true;
				break;	//only update the first user for simpliciy if multiple user with same name and different emails
			}
		  }
		}
		if (!isUserExist) {
			addUser(userToUpdate);
		}
		return userToUpdate;
	
	}

	@Override
	public User findUser(String name) {
		logger.debug("===== findUser : " + name + " * users count:" + users.size());
		User theUser = null;
		//potentially there could be multiple users with same name, but simplify to return the last one. Otherwise, just tedious work
		//could enforce name uniqueness, but that's not in the requirement
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				theUser = user;
				logger.debug("===== theUser : " + theUser.getName());
				// return user;
			}
		}
		return theUser;
	}

}
