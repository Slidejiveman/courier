package courierpd;

/**
 * A static utility class which contains the adjustable business 
 * parameters which may be changed by the Owner. The business 
 * parameters are used primarily with the delivery ticket and routes.
 */
public class BusinessParameters {

    /**
     * Represents the amount of time before the estimated 
     * delivery date that the courier must arrive in order to receive a bonus.
     * An example: If the estimated delivery time is 3:00, 
     * then the courier must report the delivery to Ubiquity by 2:55 exclusive.
     */
    private static int bonusWindow = 5;
    /**
     * Sets the amount the courier receives as a bonus when a delivery is made early.
     */
    private static double bonusPaymentAmt = 1.5;
    /**
     * The base cost for a delivery. All other charges are 
     * added to this base value.
     */
    private static double billingBase = 10.0;
    /**
     * Added to the billing base value for each block it takes to 
     * perform the delivery, round trip.
     */
    private static double billingRate = 2.0;
    /**
     * The amount of time Ubiquity adds for a pick up when calculating 
     * the estimated delivery time. This represents the time it takes for the courier to go to the pickup client's office and retrieve the package.
     */
    private static int pickUpDelay = 10;
    /**
     * The amount of time Ubiquity adds to the delivery ticket to compensate. 
     * This value compensates for the amount of time it takes the 
     * courier to drop a package off with the proper person at the 
     * receiving client's place of business.
     */
    private static int deliveryDelay = 10;
    /**
     * Represents the average speed a courier can travel on a bike. 
     * This is used to estimate delivery time.
     */
    private static double avgCourierSpeed = 5.0;

    /**
     * Returns the BonusWindow currently set within the system.
     */
    public static int getBonusWindow() {
        return bonusWindow;
    }

    /**
     * Returns the bonus payment amount as stored in the system.
     */
    public static double getBonusPaymentAmt() {
        return bonusPaymentAmt;
    }

    /**
     * Returns the billing base as stored in the system.
     */
    public static double getBillingBase() {
        return billingBase;
    }

    /**
     * Returns the billing rate as stored in the system.
     */
    public static double getBillingRate() {
        return billingRate;
    }

    /**
     * Returns the current pick up delay value as held within the system.
     */
    public static int getPickUpDelay() {
        return pickUpDelay;
    }

    /**
     * Return the delivery delay value as held within the system.
     */
    public static int getDeliveryDelay() {
        return deliveryDelay;
    }

    /**
     * Returns the average courier speed as held within the system.
     */
    public static double getAvgCourierSpeed() {
        return avgCourierSpeed;
    }

}