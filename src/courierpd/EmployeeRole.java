package courierpd;

/**
 * An enumerator which holds the valid values for an employee's role. 
 * These are Courier, OrderTaker, and Owner. 
 * These values will be used when checking for permissions in the system.
 */
public enum EmployeeRole {
    /**
     * The employee that performs the actual delivery of packages.
     */
    Courier,
    /**
     * The employee who converses with clients on the 
     * phone and initially opens the delivery ticket.
     */
    OrderTaker,
    /**
     * The superuser of the system. The Owner can take all actions. 
     * The Owner is the only user that can add or remove employees 
     * and adjust the business parameter values.
     */
    Owner
}