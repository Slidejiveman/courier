package courierpd.core;

import java.util.List;

import courierdm.EmployeeDBAO;

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
      List<User> employees =  EmployeeDBAO.listUsers(); // get all employees
    	
    	
    	return new Courier();
    }

}