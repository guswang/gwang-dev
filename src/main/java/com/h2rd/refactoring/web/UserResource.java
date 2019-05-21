package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.h2rd.refactoring.exception.DaoException;
import com.h2rd.refactoring.exception.UserException;
import org.apache.log4j.Logger;

@Path("/users")
@Repository
public class UserResource{

	final static Logger logger = Logger.getLogger(UserResource.class);
	static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
    		"classpath:/application-config.xml"	
    	});
	UserDao userDao; // = context.getBean(UserDao.class);
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserResource (){
		userDao = context.getBean(UserDao.class);
	}

    //public UserDao userDao;
    @POST
    //@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
	    try {
    		userDao.addUser(user);
            logger.debug("===== add a user : " + user.getName());
            return Response.ok().entity(user).build();
    	}
    	catch (UserException uEx) {
    		return Response.ok().entity(uEx.getMessage()).build();
    	}
    	catch (DaoException daoEx) {
    		return Response.status(500).entity(daoEx.getMessage()).build();
    	}
        
    }	
	
    @PUT
    //@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        
		try {
    		userDao.updateUser(user);
            logger.debug("===== update a user : " + user.getName());
            return Response.ok().entity(user).build();
    	}
    	catch (UserException uEx) {
    		return Response.ok().entity(uEx.getMessage()).build();
    	}
    	catch (DaoException daoEx) {
    		return Response.status(500).entity(daoEx.getMessage()).build();
    	}
    }	

    @DELETE
    @Path("/{name}")
    public Response deleteUser(@PathParam("name") String name) {

        try {
    		userDao.deleteUser(name);
            logger.debug("===== delete a user : " + name);
            return Response.status(204).entity("").build();
    	}
		catch (DaoException daoEx) {
    		return Response.status(500).entity(daoEx.getMessage()).build();
    	}
    }

    @GET
    //@Path("/")
    public Response getUsers() {
		try {
    		List<User> users = userDao.getUsers();
    	    if (users == null) {
    		  users = new ArrayList<User>();
    	    }

    	    logger.debug("===== find all users : " + users);
            GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
            return Response.ok().entity(usersEntity).build();
    	}
		catch (DaoException daoEx) {
    		return Response.status(500).entity(daoEx.getMessage()).build();
    	}    	
    }

    @GET
    @Path("/{name}")
    public Response findUser(@PathParam("name") String name) {

		try {
    		User user = userDao.findUser(name);

            return Response.ok().entity(user).build();
    	}
		catch (DaoException daoEx) {
    		return Response.status(500).entity(daoEx.getMessage()).build();
    	}
		
    }
}
