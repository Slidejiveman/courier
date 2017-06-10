package courierpd.other;

import java.util.List;

import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.DeliveryTicketDBAO;
import courierdm.EmployeeDBAO;
import courierdm.IntersectionDBAO;
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
		CourierEntityManager.initEM();
		List<Client> persistedClients = ClientDBAO.listClients();
		// Noticed that due to inheritance, there are multiple employee Roles associated
		// with the employees. That may or may not be a problem. We'll have to see.
		List<User> persistedEmployees = EmployeeDBAO.listUsers();
		List<DeliveryTicket> persistedTickets = DeliveryTicketDBAO.listDeliveryTickets();
		List<Intersection> persistedIntersections = IntersectionDBAO.listIntersections();
		// In here, we can read something out of the database and then pass
		// it on to the main frame here. There's an example in the League code.
		CourierMainFrame.startGUI(args);
	}

}
