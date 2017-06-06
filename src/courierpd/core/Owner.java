package courierpd.core;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import courierpd.enums.EmployeeRole;

/**
 * The Owner has all permissions in the system. 
 * This class exists for potential extensibility.
 */
@Entity(name = "owner")
@DiscriminatorValue("Owner")
public class Owner extends OrderTaker {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = -6994515854910072640L;
	/**
     * The role of the Owner is "Owner" and should not change. The role or the class name is used to determine whether not actions may be taken within the system.
     */
	@Column(name = "employee_role", nullable = false)
    private EmployeeRole employeeRole = courierpd.enums.EmployeeRole.Owner;

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
        
    }

}