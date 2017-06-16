package courierpd.core;

import java.util.List;

import courierdm.EmployeeDBAO;
import courierpd.enums.EmployeeRole;

/**
 * A static class used for determining the default courier to suggest for a given delivery ticket. 
 * All active couriers can be read in from the database, 
 * and that information can be used to determine which courier to use.
 */
public class DefaultCourierAlgorithm {
    /**
     * The algorithm for determining which courier to suggest to an 
     * OrderTaker while filling in the delivery ticket fields. 
     * This is determined by whether or not a courier is currently 
     * out on delivery and the number of deliveries a courier has performed on a given day.
     */
    public static Courier suggestDefaultCourier() {
        // TODO - implement DefaultCourierAlgorithm.suggestDefaultCourier
        Courier defaultCourier = null;                                        // Placeholder for courier to return
    	List<User> employees =  EmployeeDBAO.listUsers();                     // get all employees
      	Integer maxDeliveries = Integer.MAX_VALUE;                            // Placeholder for current highest delivery count
        for (User employee : employees) {                                     // for each employee
      		if (employee.employeeRole.equals(EmployeeRole.Courier)) {         // If the employee is a courier, continue
      			Courier courier = (Courier) employee;                         // Cast to a Courier for readability
      			if(courier.getIsActive() && !courier.getIsOutForDelivery()) { // If active and not out for a delivery
      				if(courier.getDeliveriesToday() < maxDeliveries) {        // See if the number of deliveries is less than current max
      					defaultCourier = courier;                             // Set the defaultCourier as the courier with fewer deliveries
      					maxDeliveries = defaultCourier.getDeliveriesToday();  // set this Courier's deliveries as the new maximum.
      				}
      			}
      		}
      	}
        // Return the default courier as suggested by the algorithm.
        return defaultCourier;
    }

}