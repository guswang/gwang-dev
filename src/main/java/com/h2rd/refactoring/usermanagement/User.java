package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Iterator;

@XmlRootElement
public class User {

    String name;
    String email;
    List<String> roles;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    @Override
    public boolean equals(Object obj) {
    	boolean isEqual = false;
    	if (obj == null || obj.getClass() != getClass()) {
    		isEqual = false;
    	}
    	else {
    		User userObj = (User)obj;
    		if (userObj.getEmail().equalsIgnoreCase(this.getEmail())) {
    			isEqual = true;
    		}
    	}
    	return isEqual;
    }

    @Override
    public String toString() {
    	String userAttributes;
    	userAttributes = this.getName() + ":" + this.getEmail() + ":";
    	for (Iterator<String> roleIter = this.getRoles().iterator(); roleIter.hasNext();) {
    		userAttributes = userAttributes + roleIter.next() + " ";
    	}
		return userAttributes;    	
    }	
}
