package courierpd.core;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import courierpd.enums.TicketStatus;
import courierpd.map.Route;

/**
 * The Delivery Ticket is the heart of Ubiquity as well as of Acme Courier Services' business. 
 * It contains information related to Couriers, Order Takers, Clients, Routes, and much more. 
 * Delivery Tickets are the primary entity of interest in generating reports as well.
 */
@Entity(name = "delivery_ticket")
public class DeliveryTicket implements Serializable {

    /**
	 * Allows Serialization so that the item may be stored in the
	 * database
	 */
	private static final long serialVersionUID = 4198685346241980809L;
	/**
     * The date and time the delivery ticket was opened.
     */
	@Column(name = "order_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    
    /**
     * This is the pick up time the courier is supposed to shoot for. 
     * This is used when determining the departure time.
     */
	@Column(name = "requested_pickup_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date requestedPickUpTime;
    /**
     * The flag that determines which of the two clients to bill. 
     * By default, the pickup client is assumed to be the one who is 
     * billed because this is the client that initiated the delivery ticket. 
     * It can be manually changed to the delivery client.
     */
	@Column(name = "is_bill_pick_up", nullable = false)
    private boolean isBillPickUp = true;
    /**
     * The unique identifier of the package as well as the delivery ticket in the system. 
     * A package is not of much interest to the company or the software other than 
     * the fact that it exists. The Delivery Ticket itself is a package's primary agency in Ubiquity.
     */
    @Id //signifies the primary key
    @Column(name = "delivery_ticket_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageID;
    /**
     * The time the courier left from the Office. 
     * This is recorded by the Order Taker when the 
     * courier notifies them that they are leaving.
     */
    @Column(name = "departure_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date actualDepartureTime;
    /**
     * The time the courier received the package from the pick-up client. 
     * This should be recorded in the system by the courier via an email.
     */
    @Column(name = "pick_up_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date actualPickUpTime;
    /**
     * The time at which the courier delivered the package to the receiving client. 
     * This should be reported by the courier via an email to Ubiquity.
     */
    @Column(name = "delivery_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date actualDeliveryTime;
    /**
     * The time at which the courier returned to Acme Courier Services. 
     * This is recorded by the order taker when the courier checks back in.
     */
    @Column(name = "return_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date courierReturnTime;
    /**
     * A flag determined based on the actual delivery time and the bonus window business parameter. 
     * If the actual delivery time is less than the estimated delivery time minus the bonus window, 
     * then this flag is set to true and a bonus is received.
     */
    @Column(name = "bonus_earned", nullable = false)
    private boolean isBonusEarned = false;
    /**
     * Special instructions that are provided to the Order Taker over the phone by the client. 
     * Special Instructions are made available on the delivery instructions document 
     * so that the courier can more easily fulfill them. These are not required, 
     * but they should be held in the system to help the courier after arriving on site.
     */
    @Column(name = "special_instructions")
    private String specialDeliveryInstructions;
    /**
     * The courier assigned to perform the delivery.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "dt_courier_id", nullable = false, referencedColumnName="employee_id")
    private Courier courier;

	/**
     * The client who will be receiving the package.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "dt_delivery_client_id", nullable = false, referencedColumnName="client_id")
    private Client deliveryClient;
    /**
     * The client who is sending the package.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "dt_pick_up_client_id", nullable = false, referencedColumnName="client_id")
    private Client pickUpClient;
    /**
     * The route that the courier will take to perform the delivery and return 
     * back to the office. This route is also used to determine the estimated 
     * price and delivery time.
     */
    @Transient
    private transient Route shortestPath;
    
	/**
     * The estimated delivery time of the package. The estimated delivery 
     * time is determined using the Route, but it is stored in the database 
     * as part of the delivery ticket.
     */
    @Column(name = "estimated_delivery_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date estDeliveryTime;
    /**
     * The estimated number of blocks traveled, round-trip, for the delivery. 
     * This is determined with the help of a Route but stored as part of a delivery ticket.
     */
    @Column(name = "estimated_blocks", nullable = false)
    private int estBlocks;
    /**
     * The estimated price is the estimated amount that the client will be 
     * billed for the service. This is determined with the help of a route, 
     * but it is stored as part of a delivery ticket.
     */
    @Column(name = "estimated_price", nullable = false)
    private float estPrice;
    /**
     * The employee who took the order from the calling client.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "dt_order_taker_id", nullable = false, referencedColumnName="employee_id")
    private OrderTaker orderTaker;
    /**
     * The state of the delivery ticket. 
     * Certain actions, reporting, updating, deleting, etc., 
     * are based on the state of the delivery ticket.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", nullable = false)
    private TicketStatus status = courierpd.enums.TicketStatus.Opened;
    /**
     * The estimated time the the courier will have to 
     * depart in order to arrive at the destination on time.
     */
    @Column(name = "estimated_departure_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date estimatedDepartureTime;

    /**
     * Returns the order date.
     */
    public Date getOrderDate() {
        return this.orderDate;
    }

    /**
     * Sets the date for the order.
     * @param orderDate The date of the order.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Returns the special delivery instructions from the client.
     */
    public String getSpecialDeliveryInstructions() {
        return this.specialDeliveryInstructions;
    }

    /**
     * Sets the special delivery instructions in the system.
     * @param specialDeliveryInstructions The special delivery instructions to
     */
    public void setSpecialDeliveryInstructions(String specialDeliveryInstructions) {
        this.specialDeliveryInstructions = specialDeliveryInstructions;
    }

    /**
     * The default constructor of a delivery ticket.
     */
    public DeliveryTicket() {
        
    }

    /**
     * Returns the time requested by the client for package pick-up.
     */
    public Date getRequestedPickUpTime() {
        return this.requestedPickUpTime;
    }

    /**
     * Sets the time requested by the client for package pick-up.
     * @param requestedPickUpTime The time requested by the client for package pick-up.
     */
    public void setRequestedPickUpTime(Date requestedPickUpTime) {
        this.requestedPickUpTime = requestedPickUpTime;
    }

    /**
     * Return the flag which determines which customer will be billed.
     */
    public boolean getIsBillPickUp() {
        return this.isBillPickUp;
    }

    /**
     * Set the flag which determines which customer will be billed.
     * @param isBillPickUp The flag which determines the customer to bill.
     */
    public void setIsBillPickUp(boolean isBillPickUp) {
        this.isBillPickUp = isBillPickUp;
    }

    /**
     * Returns the unique identifier for the delivery ticket.
     */
    public int getPackageID() {
        return this.packageID;
    }

    /**
     * Sets the unique identifier for a delivery ticket.
     * @param packageID
     */
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    /**
     * Returns the actual departure time of a courier leaving to make a delivery.
     */
    public Date getActualDepartureTime() {
        return this.actualDepartureTime;
    }

    /**
     * Sets the actual departure time of a courier as reported to an Order Taker just prior to delivery.
     * @param actualDepartureTime The actual departure time of a courier.
     */
    public void setActualDepartureTime(Date actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    /**
     * Returns the actual pick-up time of the package.
     */
    public Date getActualPickUpTime() {
        return this.actualPickUpTime;
    }

    /**
     * Sets the actual pick-up time of the package.
     * @param actualPickUpTime The actual pick up time of the package.
     */
    public void setActualPickUpTime(Date actualPickUpTime) {
        this.actualPickUpTime = actualPickUpTime;
    }

    /**
     * Returns the actual delivery time of the package.
     */
    public Date getActualDeliveryTime() {
        return this.actualDeliveryTime;
    }

    /**
     * Sets the actual delivery time of the package.
     * @param actualDeliveryTime the actual delivery time of the package.
     */
    public void setActualDeliveryTime(Date actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }

    /**
     * Returns the courier's return time.
     */
    public Date getCourierReturnTime() {
        return this.courierReturnTime;
    }

    /**
     * Sets the courier return time in the system.
     * @param courierReturnTime The time the courier returned to Acme Courier Services after a delivery.
     */
    public void setCourierReturnTime(Date courierReturnTime) {
        this.courierReturnTime = courierReturnTime;
    }

    /**
     * Returns the bonus earned flag.
     */
    public boolean getIsBonusEarned() {
        return this.isBonusEarned;
    }

    /**
     * Sets the bonus earned flag.
     * @param isBonusEarned Whether or not a bonus is returned.
     */
    public void setIsBonusEarned(boolean isBonusEarned) {
        this.isBonusEarned = isBonusEarned;
    }

    /**
     * Returns the status of the delivery ticket.
     */
    public TicketStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the delivery ticket.
     * @param status The status of the delivery ticket: Open, Closed, or Canceled.
     */
    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    /**
     * Returns the estimated delivery time.
     */
    public Date getEstDeliveryTime() {
        return this.estDeliveryTime;
    }

    /**
     * Sets the estimated delivery time.
     * @param estDeliveryTime The estimated delivery time.
     */
    public void setEstDeliveryTime(Date estDeliveryTime) {
        this.estDeliveryTime = estDeliveryTime;
    }

    /**
     * Returns the estimated number of blocks it will take to 
     * complete a delivery rounded to a whole number.
     */
    public int getEstBlocks() {
        return this.estBlocks;
    }

    /**
     * Sets the estimated number of blocks as a whole number.
     * @param estBlocks The whole number estimate of the number of blocks required to complete a delivery.
     */
    public void setEstBlocks(int estBlocks) {
        this.estBlocks = estBlocks;
    }

    /**
     * Get the estimated price of the delivery.
     */
    public float getEstPrice() {
        return this.estPrice;
    }

    /**
     * Set the estimated price of the delivery.
     * @param estPrice The estimated price of the delivery.
     */
    public void setEstPrice(float estPrice) {
        this.estPrice = estPrice;
    }

    /**
     * Returns the estimated departure time for a courier's delivery to be on time.
     */
    public Date getEstimatedDepartureTime() {
        return this.estimatedDepartureTime;
    }

    /**
     * Sets the estimated departure time that a courier will 
     * have to meet in order to arrive on time.
     * @param estimatedDepartureTime The estimated departure time required for the delivery to arrive on time.
     */
    public void setEstimatedDepartureTime(Date estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    /**
     * Gets the serialization unique identifier
     * @return serialVersionUID
     */
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    /**
     * Creates a unique ID for the package associated with a delivery ticket. 
     * Therefore, this also creates a unique ID for a delivery ticket.
     */
    public void generatePackageID() {
        // TODO - implement DeliveryTicket.generatePackageID
        throw new UnsupportedOperationException();
    }


    public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Client getDeliveryClient() {
		return deliveryClient;
	}

	public void setDeliveryClient(Client deliveryClient) {
		this.deliveryClient = deliveryClient;
	}

	public Client getPickUpClient() {
		return pickUpClient;
	}

	public void setPickUpClient(Client pickUpClient) {
		this.pickUpClient = pickUpClient;
	}

	public OrderTaker getOrderTaker() {
		return orderTaker;
	}

	public void setOrderTaker(OrderTaker orderTaker) {
		this.orderTaker = orderTaker;
	}
	
	public Route getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(Route shortestPath) {
		this.shortestPath = shortestPath;
	}

	public void setPickupCustomer(Client client) {
		this.pickUpClient=client;
		
	}

	public void setDeliveryCustomer(Client client) {
		this.deliveryClient=client;
	}
	@SuppressWarnings("deprecation")
	public Time getOrderPlacementTime(){
		return new Time(this.orderDate.getHours(), this.orderDate.getMinutes(),this.orderDate.getSeconds());
	}
	@Override
	public String toString (){
		return "                       "+this.getPackageID()
		+ "                                "+this.getOrderDate()
		+"                             "+this.getStatus().toString()
		+"                             "+this.getPickUpClient().getName()
		+"                                 "+this.getDeliveryClient().getName();
	}
}