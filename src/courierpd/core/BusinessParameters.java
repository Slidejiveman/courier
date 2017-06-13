package courierpd.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A persisted class which contains the adjustable business 
 * parameters which may be changed by the Owner. The business 
 * parameters are used primarily with the delivery ticket and routes.
 * 
 * Note: Do not call the default constructor for this class at any
 * time. Read it in from the database using its DBAO. This will allow
 * us to perform static calls on its members.
 */
@Entity(name = "business_parameters")
public class BusinessParameters implements Serializable {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = 3050496403491761232L;

	@Id
	@Column(name = "business_parameters_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bizParamKey = 0;

	/**
     * Represents the amount of time before the estimated 
     * delivery date that the courier must arrive in order to receive a bonus.
     * An example: If the estimated delivery time is 3:00, 
     * then the courier must report the delivery to Ubiquity by 2:55 exclusive.
     */
	@Column(name = "bonus_window", nullable = false)
    private int bonusWindow = 5;

	/**
     * Sets the amount the courier receives as a bonus when a delivery is made early.
     */
	@Column(name = "bonus_payment_amount", nullable = false)
    private double bonusPaymentAmt = 1.5;
    /**
     * The base cost for a delivery. All other charges are 
     * added to this base value.
     */
	@Column(name = "billing_base", nullable = false)
    private double billingBase = 10.0;
    /**
     * Added to the billing base value for each block it takes to 
     * perform the delivery, round trip.
     */
	@Column(name = "billing_Rate", nullable = false)
    private double billingRate = 2.0;
    /**
     * The amount of time Ubiquity adds for a pick up when calculating 
     * the estimated delivery time. This represents the time it takes for the courier to go to the pickup client's office and retrieve the package.
     */
	@Column(name = "pick_up_delay", nullable = false)
    private int pickUpDelay = 10;
    /**
     * The amount of time Ubiquity adds to the delivery ticket to compensate. 
     * This value compensates for the amount of time it takes the 
     * courier to drop a package off with the proper person at the 
     * receiving client's place of business.
     */
	@Column(name = "delivery_delay", nullable = false)
    private int deliveryDelay = 10;
    /**
     * Represents the average speed a courier can travel on a bike. 
     * This is used to estimate delivery time.
     */
	@Column(name = "avg_courier_speed", nullable = false)
    private double avgCourierSpeed = 5.0;

    /**
     * Returns the BonusWindow currently set within the system.
     */
    public int getBonusWindow() {
        return bonusWindow;
    }

    /**
     * Returns the bonus payment amount as stored in the system.
     */
    public double getBonusPaymentAmt() {
        return bonusPaymentAmt;
    }

    /**
     * Returns the billing base as stored in the system.
     */
    public double getBillingBase() {
        return billingBase;
    }

    /**
     * Returns the billing rate as stored in the system.
     */
    public double getBillingRate() {
        return billingRate;
    }

    /**
     * Returns the current pick up delay value as held within the system.
     */
    public int getPickUpDelay() {
        return pickUpDelay;
    }

    /**
     * Return the delivery delay value as held within the system.
     */
    public int getDeliveryDelay() {
        return deliveryDelay;
    }

    /**
     * Returns the average courier speed as held within the system.
     */
    public double getAvgCourierSpeed() {
        return avgCourierSpeed;
    }
    
    public void setBonusWindow(int bonusWindow) {
		this.bonusWindow = bonusWindow;
	}

	public void setBonusPaymentAmt(double bonusPaymentAmt) {
		this.bonusPaymentAmt = bonusPaymentAmt;
	}

	public void setBillingBase(double billingBase) {
		this.billingBase = billingBase;
	}

	public void setBillingRate(double billingRate) {
		this.billingRate = billingRate;
	}

	public void setPickUpDelay(int pickUpDelay) {
		this.pickUpDelay = pickUpDelay;
	}

	public void setDeliveryDelay(int deliveryDelay) {
		this.deliveryDelay = deliveryDelay;
	}

	public void setAvgCourierSpeed(double avgCourierSpeed) {
		this.avgCourierSpeed = avgCourierSpeed;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getBizParamKey() {
		return bizParamKey;
	}

	public void setBizParamKey(int bizParamKey) {
		this.bizParamKey = bizParamKey;
	}
	
	// Do nothing constructor needed for the database.
	public BusinessParameters() {
		
	}

}