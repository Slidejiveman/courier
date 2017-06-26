package courierpd.other;

import java.util.*;

import javax.swing.JOptionPane;

import courierdm.EmployeeDBAO;
import courierpd.core.User;
import courierpd.enums.EmployeeRole;

/**
 * The Authorizer class backs the login screen. 
 * It checks that the entered credential match the username and 
 * password held by a single user. It will ensure that a 
 * username cannot be validated by any entering any valid password. 
 * The username and password must match as a pair and be linked to a single, valid user.
 */
public class Authorizer {

    /**
     * The authorized users here should be a list of all authorized users in the system. 
     * The entered login credentials of a user are compared with the credentials the 
     * Authorizer has to grant access into the system.
     */
    private List<User> authorizedUsers;
    /**
     * The message string that displays onscreen if invalid credentials are 
     * entered at the login window.
     */
    private String invalidMsg = "Invalid login credentials. Please try again.";
    /**
     * The User currently logged into the system. 
     * The authorizer keeps track of this so that the permissions can 
     * be checked whenever required based on the User's role.
     */
    private User activeUser;

    /**
     * This method compares the entered in login credentials with the 
     * usernames and passwords Ubiquity's Authorizer already knows 
     * until there is a match. If there is not a match, then the 
     * invalid message string is updated to indicate to the user that 
     * the credentials were not authenticated.
     */
    public void validateLoginCredentials(String userName,String passWord) {
        
    	for (User user : authorizedUsers) {
    		if (!user.getEmployeeRole().equals(EmployeeRole.Courier)) {
	    		if (user.getUsername().equals(userName)){
	    		   if (user.getPassword().equals(passWord)) {
	    			   activeUser = user;
	    		   }  
	    		} 
    		}
    	}
    	if (activeUser == null) {
    		
	    JOptionPane.showMessageDialog(null, 
    			getInvalidMsg(), "Login Failed", JOptionPane.ERROR_MESSAGE);
    	}
    }

    /**
     * Returns the invalid message string as it is currently in the system.
     */
    public String getInvalidMsg() {
        return this.invalidMsg;
    }

    /**
     * Sets the invalid message string to the newly provided value.
     * @param invalidMsg The new message to display to the user 
     * when invalid credentials are entered.
     */
    public void setInvalidMsg(String invalidMsg) {
        this.invalidMsg = invalidMsg;
    }

    /**
     * The default constructor.
     */
    public Authorizer(String userName,String passWord) {
    	authorizedUsers = EmployeeDBAO.listUsers();   
    	validateLoginCredentials(userName, passWord);
    
    }

    /**
     * Returns the active user.
     */
    public User getActiveUser() {
        return this.activeUser;
    }

    /**
     * Sets the active user after a successful login.
     * @param activeUser The active user logged into Ubiquity.
     */
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}