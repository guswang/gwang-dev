package test.com.h2rd.refactoring.integration;

import javax.ws.rs.core.Response;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.usermanagement.UserStore;
import com.h2rd.refactoring.usermanagement.UserStoreImpl;
import com.h2rd.refactoring.web.UserResource;

import junit.framework.Assert;

public class AddGetUpdDelThread extends Thread {
	
	User user;
	UserResource userResource;
	public AddGetUpdDelThread(User client) {
		user = client;
		userResource = new UserResource();
		UserDao userDao = new UserDao();
		UserStore userStore = new UserStoreImpl();
		userDao.setUserStore(userStore);
		userResource.setUserDao(userDao);
	}
	
	@Override
	public void run() {
		System.out.println("********** CRUD thread RUNNING **********");
		Response response = userResource.addUser(user);
		Assert.assertTrue(response.getEntity().toString().contains(user.getName()));
		Assert.assertEquals(200, response.getStatus());
		
		response = userResource.findUser(user.getName());
		Assert.assertTrue(response.getEntity().toString().contains(user.getName()));
		Assert.assertEquals(200, response.getStatus());
		
		user.setEmail(Thread.currentThread().getName() + "@gmail.com");
		response = userResource.updateUser(user);
		
		Assert.assertTrue(response.getEntity().toString().contains(user.getEmail()));
		Assert.assertEquals(200, response.getStatus());
		
		response = userResource.deleteUser(user.getName());
		Assert.assertEquals(204, response.getStatus());
	}
}
