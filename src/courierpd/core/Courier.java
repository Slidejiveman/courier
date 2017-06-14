package courierpd.core;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import courierpd.enums.EmployeeRole;

/**
 * Represents the courier who performs the delivery of a package. 
 * This class extends the abstract User class and adds some information specific to couriers. 
 * Note that usernames and passwords assigned to Couriers do not provide access into Ubiquity.
 */
@Entity(name = "courier")
@DiscriminatorValue("CO")
public class Courier extends User {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = 1216271864510733589L;
	/**
     * The number of deliveries performed during a day. 
     * This field is used with the default courier algorithm to 
     * determine which courier to suggest to the order taker.
     * This is a transient because it is assumed that deliveries
     * in a day will only be tracked during a single run of the
     * software.
     */
	@Transient
    private transient int deliveriesToday = 0;
    /**
     * Flag used to determine whether or not the courier 
     * is in the office and available to go on another delivery.
     * This is transient because a courier's out for delivery
     * status does not need to be persisted to the next day.
     */
	@Transient
    private transient boolean isOutForDelivery = false;
    /**
     * The role assigned to a courier is Courier. 
     * It has the lowest level of permissions. 
     * This value should not be changed.
     */
//	@Enumerated(EnumType.STRING)
//	@Column(name = "employee_role", nullable = false)
//    private EmployeeRole employeeRole = courierpd.enums.EmployeeRole.Courier;

	@OneToMany(targetEntity = DeliveryTicket.class, mappedBy = "courier")
	protected Collection<DeliveryTicket> deliveryTickets;
	
    /**
     * Sets the number of deliveries the courier has made on a given day.
     * @param deliveriesToday The number of deliveries made today.
     */
    public void setDeliveriesToday(int deliveriesToday) {
        this.deliveriesToday = deliveriesToday;
    }

    /**
     * Returns the deliveries today as stored in the system.
     */
    public int getDeliveriesToday() {
        return this.deliveriesToday;
    }

    /**
     * Returns the courier's delivery status.
     */
    public boolean getIsOutForDelivery() {
        return this.isOutForDelivery;
    }

    /**
     * Set the is out for delivery flag.
     * @param isOutForDelivery The boolean value to set the out for delivery flag with.
     */
    public void setIsOutForDelivery(boolean isOutForDelivery) {
        this.isOutForDelivery = isOutForDelivery;
    }

    /**
     * The default constructor for a courier.
     */
    public Courier() {
        
    }

}