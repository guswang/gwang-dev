package com.h2rd.refactoring.usermanagement;

import java.util.List;

import com.h2rd.refactoring.exception.DaoException;
import com.h2rd.refactoring.exception.UserException;

public class UserDao {

   // public ArrayList<User> users;
    UserStore userStore;

    public UserStore getUserStore() {
		return userStore;
	}

	public void setUserStore(UserStore userStore) {
		this.userStore = userStore;
	}

	//public static UserDao userDao;
/*
    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }
*/
    public void addUser(User user) throws DaoException, UserException {
    	try {
    		userStore.addUser(user);
    	}
    	catch (UserException uEx) {
    		throw uEx;	//propagate user data exception
    	}
    	catch (Exception otherEx) {
    		new DaoException(otherEx.getMessage());
    	}
    }

    public List<User> getUsers() throws DaoException {
        try {
            return userStore.getUsers();
        } catch (Exception ex) {
            
        	throw new DaoException(ex.getMessage());
        }
    }

    public void deleteUser(String userName) throws DaoException {
        try {
            userStore.deleteUser(userName);
            
        }  catch (Exception ex) {
            
        	throw new DaoException(ex.getMessage());
        }
    }

    public User updateUser(User userToUpdate) throws DaoException, UserException {
        try {
            return userStore.updateUser(userToUpdate);
        } 
        catch (UserException uEx) {
        	throw uEx;
        }
        catch (Exception ex) {
            
        	throw new DaoException(ex.getMessage());
        }
    }

    public User findUser(String name) throws DaoException {
		User theUser=null;
        try {
            theUser=userStore.findUser(name);
        } catch (Exception ex) {
            
        	throw new DaoException(ex.getMessage());
        }
        return theUser;
    }
}
