package courierpd.map;

/**
 * The Street class represents an edge on the CityMap. 
 * The edges are needed to know which intersections are connected to other intersections. 
 * Knowing the street that connects to intersections will allow the software to determine 
 * directions for the route.
 */
public class Street {

    /**
     * The name of the street.
     */
    private String name;
    private Intersection to;
    private Intersection from;
    public Intersection getTo() {
		return to;
	}

	/**
     * The length of the street from end to end in the City Center.
     */
    private int length;

    /**
     * Returns the name of the street.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the street to a given String value.
     * @param name The name of the street.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the length of the street.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Sets the length of the street in the system.
     * @param length The length of the street.
     */
    public void setLength(int length) {
        this.length = length;
    }

    public void setTo(Intersection to) {
		this.to = to;
	}

	public Intersection getFrom() {
		return from;
	}

	public void setFrom(Intersection from) {
		this.from = from;
	}
}