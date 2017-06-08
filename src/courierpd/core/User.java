package courierpd.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import courierpd.enums.EmployeeRole;

/**
 * An abstract class which is extended by Courier, OrderTaker, 
 * Owner, and any future role the company may create. 
 * The User class contains all of the basic information 
 * that is common to all users. It does not handle any 
 * permissions itself, but contains a field, Employee Role, 
 * that is used throughout the system to determine whether the 
 * actively logged in employee has adequate permissions to 
 * perform a task.
 */
@Entity(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_role")
public class User implements Serializable {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = 1499726952634178487L;

	/**
     * The name of the employee.
     */
	@Column(name = "employee_name")
    protected String name;
    /**
     * The unique identifier assigned to an employee.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id", updatable = false, nullable = false)
    protected int number;
    /**
     * The email associated with an employee. 
     * These should all be Gmail accounts.
     */
	@Column(name = "employee_email", nullable = false)
    protected String email;
    /**
     * The username associated with an employee.
     * A Courier does not have a username.
     */
	@Column(name = "employee_username")
    protected String username;
    /**
     * The password associated with a given employee. 
     * Employees are able to change their own password. 
     * Other users should not be able to see the password 
     * when navigating through the data management screens.
     * A Courier does not have a password
     */
	@Column(name = "employee_password")
    protected String password;
    /**
     * The shift that the employee usually works. 
     * Acme Courier is open twelve hours a day. 
     * Shifts begin at 8 am and end at 8pm. 
     * Each shift lasts 8 hours. 
     * There are three shifts beginning at 8am, 10am, and 12pm. 
     * These are known simply as shift 1, 2, and 3 respectively. 
     * Part-time help must work a minimum of four hours.  
     * Part-time shifts are based on the latest shift active from when they begin. 
     * Therefore, part-time help starting at 10am would be shift 2. 
     * Any time 12pm or later will be shift 3.
     */
	@Column(name = "employee_shift", nullable = false)
    protected int shift;
    /**
     * Flag that determines whether an employee is currently active in the system. 
     * Inactive employees are either on a leave of absence or have been terminated from the company.
     */
	@Column(name = "employee_is_active", nullable = false)
    protected boolean isActive = true;
    
    /**
     * The role the employee will serve in the company.
     * This enumerator is overridden in each child class.
     */
	@Enumerated(EnumType.STRING)
	@Column(name = "employee_role", nullable = false)
    protected EmployeeRole employeeRole;

	
	
    /**
     * Returns the employee's name in the system.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of an employee in the system.
     * @param employeeName The name to be used for an employee in the system.
     */
    public void setName(String employeeName) {
        this.name = employeeName;
    }

    /**
     * Returns the unique identifier for an employee.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Sets the unique identifier for an employee.
     * @param employeeNumber The unique identifier for an employee.
     */
    public void setNumber(int employeeNumber) {
        this.number = employeeNumber;
    }

    /**
     * Returns the email of an employee in the system.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email of an employee in the system.
     * @param employeeEmail The email to be used for an employee.
     */
    public void setEmail(String employeeEmail) {
        this.email = employeeEmail;
    }

    /**
     * Returns the employee role for a user in the system.
     */
    public EmployeeRole getEmployeeRole() {
        return this.employeeRole;
    }

    /**
     * Sets the role for an employee in the system.
     * @param employeeRole The employee's role at Acme Courier Services.
     */
    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    /**
     * Returns the username of the employee.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of an employee.
     * @param employeeUsername The username for an employee in the system.
     */
    public void setUsername(String employeeUsername) {
        this.username = employeeUsername;
    }

    /**
     * Returns the employee's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the password for an employee.
     * @param employeePassword The string value to be used as 
     *     an employee's password in the system.
     */
    public void setPassword(String employeePassword) {
        this.password = employeePassword;
    }

    /**
     * returns the employee's usual shift in the system.
     */
    public int getShift() {
        return this.shift;
    }

    /**
     * Sets the usual shift an employee will work in the system.
     * @param employeeShift The usual shift an employee will work. 
     *     Represented by the values 1, 2, or 3.
     */
    public void setShift(int employeeShift) {
        this.shift = employeeShift;
    }

    /**
     * Returns whether the employee is currently active in the system.
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * Sets whether an employee is currently actively working at Acme Courier Services.
     * @param employeeActive the value for whether an employee is active or not.
     */
    public void setIsActive(boolean employeeActive) {
        this.isActive = employeeActive;
    }

    /**
     * Gets the serialization unique identifier
     * @return serialVersionUID
     */
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    /**
     * Generates a new unique identifier for a user.
     * This method should check against all employees currently
     * stored in the database to ensure its uniqueness.
     */
    public void generateUserID() {
        // TODO - implement User.generateUserID
        throw new UnsupportedOperationException();
    }
    
    public User() {
    	
    }
    @Override
    public String toString(){
    	return this.name;
    }

}