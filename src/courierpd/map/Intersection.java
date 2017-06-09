package courierpd.map;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import courierpd.core.Client;

/**
 * An intersection is the point on the city map where two streets overlap. 
 * These points are the nodes for the shortest path algorithm as deliveries 
 * are made from one intersection to another. This is done because Ubiquity 
 * stores all locations as intersections, which is ultimately simpler than 
 * using a full length address. Intersections have X and Y coordinate values 
 * that represent their location in the City Center apart from their names. 
 * This internal representation for an intersection should not change.
 */
@Entity(name = "intersection")
public class Intersection implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Transient
	private transient Integer nodeValue;
	
	@Id //signifies the primary key
    @Column(name = "intersection_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int intersectionId;

	/**
     * Used to determine whether a given intersection is open or closed. 
     * An open intersection is traversable by a courier. A closed intersection is not.
     */
	@Column(name = "intersection_status", nullable = false)
    private boolean isOpen = true;
    /**
     * The name of an intersection within the City Center. 
     * Each intersection is made up of two crossing streets. 
     * The names for the individual streets may be calculated 
     * by parsing the street names our of the intersection by splitting on '&'.
     * An example Intersection name is "A St. & 1st Ave."
     */
	@Column(name = "intersection_name", nullable = false)
    private String name;
    /**
     * Represents the initial date of a reported intersection closing. 
     * This is not a required field, but might be useful for an enhancement 
     * which would allow scheduling closures ahead of time.
     */
	@Column(name = "closed_start_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.DATE)
    private Date closedDateStart;
    /**
     * Represents the end date when an intersection should be reopened. 
     * This is not a required field as an OrderTaker is able to change 
     * this value manually. An enhancement to Ubiquity's design would 
     * be to allow automated reactivation of an intersection after 
     * the system date exceeds the value of this variable.
     */
	@Column(name = "closed_end_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.DATE)
    private Date closedDateEnd;
    /**
     * The Horizontal position of an intersection. 
     * The upper left-hand corner of the city map is 0. 
     * The numbers increase going South.
     */
	@Column(name = "x_coordinate", nullable = false)
    private int xCoord;
    /**
     * The Vertical position of an intersection on the City Map. 
     * The upper left-hand corner is 0. The numbers increase heading East.
     */
	@Column(name = "y_coordinate", nullable = false)
    private int yCoord;

	/**
	 * The client that whose place of business is at this intersection
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "client_at_intersection", nullable = true, referencedColumnName = "client_id")
	private Client client;
	
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
     * Returns the current status of an intersection. 
     * The intersection is open if the return value is true. 
     * If the return value is false, then the intersection is closed.
     */
    public boolean getIsOpen() {
        return this.isOpen;
    }

    /**
     * 
     * @param status A boolean value representing whether an 
     * intersection is open or closed.
     */
    public void setIsOpen(boolean status) {
        this.isOpen = status;
    }

    /**
     * Returns the name of the intersection.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of an intersection.
     * Intersections will not likely change names, 
     * so this method should not be frequently used.
     * @param name The name to give to an intersection.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the starting date of the intersection closure.
     */
    public Date getClosedDateStart() {
        return this.closedDateStart;
    }

    /**
     * Sets the value starting date of the intersection closure.
     * @param closedDateStart The starting date of the intersection closure.
     */
    public void setClosedDateStart(Date closedDateStart) {
        this.closedDateStart = closedDateStart;
    }

    /**
     * Returns the value of the end of the intersection closure.
     */
    public Date getClosedDateEnd() {
        return this.closedDateEnd;
    }

    /**
     * Sets the end date for an intersection closure.
     * @param closedDateEnd The end date of an intersection closure.
     */
    public void setClosedDateEnd(Date closedDateEnd) {
        this.closedDateEnd = closedDateEnd;
    }

    /**
     * No argument constructor for an intersection. 
     * Sets a default name for the intersection and opens it. 
     * The default name is not unique.
     */
    public Intersection() {
        
    }

    /**
     * Constructor for an intersection that takes a constructor 
     * name as an argument. This constructor is used to create unique 
     * intersections. Each created intersection begins in the open state. 
     * It must be toggled to the closed state if desired.
     * @param name The name to be given to the intersection.
     */
    public Intersection(String name) {

    	// The named constructor can be used to
    	// create the office.
        setName(name);
        setXCoord(3);
        setYCoord(3);
        setNodeValue(0);
    }

    /**
     * Returns the X coordinate for an intersection.
     */
    public int getXCoord() {
        return this.xCoord;
    }

    /**
     * Sets the X coordinate for an intersection.
     * @param xCoord The X coordinate (horizontal position) of an intersection.
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Returns the Y coordinate of an intersection.
     */
    public int getYCoord() {
        return this.yCoord;
    }

    /**
     * Sets the Y coordinate (vertical position) of an intersection.
     * @param yCoord The Y coordinate of an intersection.
     */
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    public int getIntersectionId() {
		return intersectionId;
	}

    @Override 
    public String toString(){

    	return this.name +"                        "+ this.getClosedDateStart()+"            "
    			+ "      "+this.getClosedDateEnd();
    }

	public void setIntersectionId(int intersectionId) {
		this.intersectionId = intersectionId;
	}

	public Integer getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(Integer nodeValue) {
		this.nodeValue = nodeValue;
	}
}