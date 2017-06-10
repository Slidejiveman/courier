package courierpd.other;

import java.util.List;

import courierdm.BusinessParametersDBAO;
import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.DeliveryTicketDBAO;
import courierdm.EmployeeDBAO;
import courierdm.IntersectionDBAO;
import courieremail.EmailUtil;
import courierpd.core.BusinessParameters;
import courierpd.core.Client;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.map.Intersection;
import courierpd.map.PathAlgorithm;
import courierui.CourierMainFrame;

/**
 * The initialization class will be used to handle things
 * that should be taken care of before the main application 
 * opens up. This could include database activities.
 * 
 * The Main method is where we should probably read in stuff from the
 * database so it will be available while the application. This way it
 * isn't read in each time we access a particular panel.
 * 
 * @author rdnot
 *
 */
public class InitApplication {

	public static void main(String[] args) {
		// Initialize the entity manager and factory
		CourierEntityManager.initEM();
		
		// Call to the database reading and
		// email sending test method.
		test();

		// Start the main application frame
		CourierMainFrame.startGUI(args);
	}
	
	/**
	 * This test method tests the sending and receiving of emails
	 * as well as reading in contents from the database. 
	 * References to this method should be commented out when not 
	 * intentionally testing.
	 */
    private static void test() {
    	// Test reading in the persisted data.
    	List<Client> persistedClients = ClientDBAO.listClients();
		List<User> persistedEmployees = EmployeeDBAO.listUsers();
		List<DeliveryTicket> persistedTickets = DeliveryTicketDBAO.listDeliveryTickets();
		List<Intersection> persistedIntersections = IntersectionDBAO.listIntersections();
		List<BusinessParameters> persistedBizParams = BusinessParametersDBAO.listBusinessParameterss();
		
		// sending test emails
		// Note that client emails that we use for testing will have to be actual gmail accounts.
		EmailUtil.sendConfirmationMail(
				"rdnotlaw91@gmail.com", "ryder.walton@eagles.oc.edu", // replace these two emails with client emails
				"ubiquitymail@gmail.com", "smtp.gmail.com",
	    		"ubiquitymail@gmail.com", "UbiquityM41l$", persistedTickets.get(0)
	    		);
    }
}
