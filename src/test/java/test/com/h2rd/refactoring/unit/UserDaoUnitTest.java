package test.com.h2rd.refactoring.unit;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.h2rd.refactoring.exception.DaoException;
import com.h2rd.refactoring.exception.UserException;
import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.usermanagement.UserStore;
import com.h2rd.refactoring.usermanagement.UserStoreImpl;

public class UserDaoUnitTest {

    private UserDao userDao;

    @Before
	public void setUp() throws DaoException, UserException {

		userDao=new UserDao();
		UserStore userStore = new UserStoreImpl();
		userDao.setUserStore(userStore);

	} 
	
    @Test
    public void testAddUser() throws DaoException, UserException {

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user);
        User userAdded = userDao.findUser("Fake Name");
        Assert.assertEquals(user.toString(), userAdded.toString());

    }
    
    @Test(expected = UserException.class)
    public void testAddUserWithNonUniqueEmail() throws DaoException, UserException {
        //userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user);
        
        User user2 = new User();
        user2.setName("Fake Name2");
        user2.setEmail("fake@email.com");
        user2.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user2);
    }
    
    @Test(expected = UserException.class)
    public void testAddUserWithNullRole() throws DaoException, UserException {
        //userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        //user.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user);
       
    }
    
    @Test(expected = UserException.class)
    public void testAddUserWithEmptyRole() throws DaoException, UserException {

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(new ArrayList<String>());
        userDao.addUser(user);
       
    }

    @Test
    public void testDeleteUser() throws DaoException, UserException {
		
		User user = new User();
        user.setName("initialUser");
        user.setEmail("initialUser@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user);
        
        userDao.deleteUser("initialUser");
        User userAfterDeleted = userDao.findUser("initialUser");
        Assert.assertNull(userAfterDeleted);
    }
    
    @Test
    public void testUpdateUser() throws DaoException, UserException {

        User user = new User();
        user.setName("initialUser");
        user.setEmail("testUpdateUser@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.updateUser(user);
        User userUpdated = userDao.findUser("initialUser");
        Assert.assertEquals(user.toString(), userUpdated.toString());

    }
	
    @Test(expected = UserException.class)
    public void testUpdateUserNonUniqueEmail() throws DaoException, UserException {
		User user = new User();
        user.setName("initialUser");
        user.setEmail("initialUser@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.addUser(user);
        User user2 = new User();
        user2.setName("initialUser2");
        user2.setEmail("initialUser2@email.com");
        user2.setRoles(Arrays.asList("admin"));
        userDao.addUser(user2);
		
        user = new User();
        user.setName("initialUser");
        user.setEmail("initialUser2@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.updateUser(user);
    }
    
    @Test(expected = UserException.class)
    public void testUpdateUserNoRole() throws DaoException, UserException {
        User user = new User();
        user.setName("initialUser");
        user.setEmail("initialUser@email.com");
        user.setRoles(new ArrayList<String>());
        userDao.updateUser(user);
    }
}