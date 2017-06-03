package courierpd;

import courierpd.enums.EmployeeRole;

/**
 * Represents the courier who performs the delivery of a package. 
 * This class extends the abstract User class and adds some information specific to couriers. 
 * Note that usernames and passwords assigned to Couriers do not provide access into Ubiquity.
 */
public class Courier extends User {

    /**
     * The number of deliveries performed during a day. 
     * This field is used with the default courier algorithm to 
     * determine which courier to suggest to the order taker.
     */
    private int deliveriesToday = 0;
    /**
     * Flag used to determine whether or not the courier 
     * is in the office and available to go on another delivery.
     */
    private boolean isOutForDelivery = false;
    /**
     * The role assigned to a courier is Courier. 
     * It has the lowest level of permissions. 
     * This value should not be changed.
     */
    private EmployeeRole employeeRole = courierpd.enums.EmployeeRole.Courier;

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
        // TODO - implement Courier.Courier
        throw new UnsupportedOperationException();
    }

}