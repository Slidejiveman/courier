package courierpd.core;

import courierpd.enums.EmployeeRole;

/**
 * The OrderTaker is the main user of Ubiquity and has access 
 * to all of the normal business functions. The OrderTaker's name 
 * is required on each Delivery Ticket the OrderTaker creates.
 */
public class OrderTaker extends User {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = -7049422626028170867L;
	/**
     * The role employees of this class fulfill in the system. 
     * The OrderTaker class fulfills the OrderTaker roll. 
     * This is an intended redundancy to offer some flexibility 
     * when coding as well to provide an avenue for extending functionality in the future.
     */
    protected EmployeeRole employeeRole = courierpd.enums.EmployeeRole.OrderTaker;

    public EmployeeRole getEmployeeRole() {
        return this.employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    /**
     * The default no argument constructor.
     */
    public OrderTaker() {
        // TODO - implement OrderTaker.OrderTaker
        throw new UnsupportedOperationException();
    }

}