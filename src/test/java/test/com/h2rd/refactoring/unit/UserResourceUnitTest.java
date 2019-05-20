package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.exception.DaoException;
import com.h2rd.refactoring.exception.UserException;
import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.usermanagement.UserStore;
import com.h2rd.refactoring.usermanagement.UserStoreImpl;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import javax.ws.rs.core.Response;

public class UserResourceUnitTest {

	UserResource userResource;
	UserDao userDao;

	@Before
	public void setUp() throws DaoException, UserException {
		userResource = new UserResource();
		userDao = new UserDao();
		UserStore userStore = new UserStoreImpl();
		userDao.setUserStore(userStore);
		userResource.setUserDao(userDao);
		/*
		User user = new User();
		user.setName("InitialUser1");
		user.setEmail("InitialUser1@user.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.addUser(user);
		User user2 = new User();
		user2.setName("InitialUser2");
		user2.setEmail("InitialUser2@user.com");
		user2.setRoles(Arrays.asList("admin", "master"));
		userDao.addUser(user2);
		*/
	}

	@Test
	public void testGetUsers() {
		User user = new User();
		user.setName("InitialUser1");
		user.setEmail("InitialUser1@user.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userResource.addUser(user);
		Response response = userResource.getUsers();

		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("InitialUser1");
		user.setEmail("InitialUser1@user.com");
		user.setRoles(Arrays.asList("admin", "master"));	
				
		Response response = userResource.addUser(user);
		User userAdded = response.readEntity(User.class);
		System.out.println("======== userAdded: " + userAdded.getName());
		System.out.println("***** users: " + response.getEntity());
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testAddUserDuplicateEmail() {
		User user = new User();
		user.setName("InitialUser1");
		user.setEmail("InitialUser1@user.com");
		user.setRoles(Arrays.asList("admin", "master"));
		Response response = userResource.addUser(user);
		Assert.assertEquals(200, response.getStatus());
		
		User user2 = new User();
		user2.setName("InitialUser2");
		user2.setEmail("InitialUser1@user.com");
		user2.setRoles(Arrays.asList("admin", "master"));		
		
		response = userResource.addUser(user);
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setName("InitialUser1");
		user.setEmail("InitialUser1@user.com");
		user.setRoles(Arrays.asList("admin", "master"));
		Response response = userResource.addUser(user);
		
		response = userResource.deleteUser("InitialUser1");
		Assert.assertEquals(204, response.getStatus());
	}
	
	
}
