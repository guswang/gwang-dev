package test.com.h2rd.refactoring.integration;

import java.util.Arrays;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;

public class UserIntegrationTest {
	
	@Test
	public void testCurrentCRUD() {
		for (int i=0; i<5; i++) {
			User user = new User();
			user.setName("UserCRUD" + i);
			user.setEmail("UserCRUD" + i + "@gmail");
			user.setRoles(Arrays.asList("UserCRUD" + i + "role"));
			AddGetUpdDelThread browserThread = new AddGetUpdDelThread(user);
			browserThread.start();
		}
	}
	
/*	
	@Test
	public void createUserTest() {
		UserResource userResource = new UserResource();
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(new ArrayList<String>());
        
        //Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		Response response = userResource.addUser(integration);
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		UserResource userResource = new UserResource();
		
		createUserTest();
        
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("updated@integration.com");
        updated.setRoles(new ArrayList<String>());
        
        //Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
		Response response = userResource.updateUser(updated);
        Assert.assertEquals(200, response.getStatus());
	}
*/	
}
