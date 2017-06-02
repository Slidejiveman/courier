package CourierPD;

/**
 * The Owner has all permissions in the system. This class exists for potential extensibility.
 */
public class Owner extends OrderTaker {

    /**
     * The role of the Owner is "Owner" and should not change. The role or the class name is used to determine whether not actions may be taken within the system.
     */
    private EmployeeRole employeeRole = CourierPD.EmployeeRole.Owner;

    public EmployeeRole getEmployeeRole() {
        return this.employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    /**
     * Default constructor for the Owner.
     */
    public Owner() {
        // TODO - implement Owner.Owner
        throw new UnsupportedOperationException();
    }

}