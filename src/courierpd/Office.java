package courierpd;

/**
 * The Acme Courier Office is located in the center of the City Center. For extensibility reasons, the office is encapsulated in its own class. Should there be reasons to extend functionality in the future or should their be another office open up, this class would be useful.
 */
public class Office {

    /**
     * The location of the Acme Courier Services office in the City Center.
     */
    private Intersection location;

    /**
     * Returns the Intersection where the Acme Courier Office is located.
     */
    public Intersection getLocation() {
        return this.location;
    }

    /**
     * Sets the location of the Acme Courier Services office building. This should not be used frequently, though it is possible that this could change.
     * @param location The intersection where the Acme Courier Office building is located.
     */
    public void setLocation(Intersection location) {
        this.location = location;
    }

}