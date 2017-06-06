package courierpd.map;

import java.util.*;

/**
 * Contains the all the intersections in the City Center as well as all of the streets. 
 * The intersections are nodes in a graph. The streets are the edges. 
 * Taken together, the location of the turns as well as the relationship 
 * of the turns to one another can be determined from information held within the City Map.
 */
public class CityMap {

    /**
     * The Office is a special node used for extensibility and logical purposes. 
     * The Office is the origin and termination of every delivery. 
     * It is located at the center of the city.
     */
    private Office acmeOffice;
    public Office getAcmeOffice() {
		return acmeOffice;
	}

	/**
     * The city map is made up of many intersections. 
     * A collection of them is kept in the city map so that a route can 
     * be made for each delivery ticket.
     */
    private Collection<Intersection> intersections;
    private Collection<Street> streets;

    /**
     * No argument constructor for a city map. The city map instance holds 
     * the current state of the intersections within its collections.
     */
    public CityMap() {
        
    }

    /**
     * This method closes the given intersection. 
     * If the intersection is already closed, there is no effect.
     * @param intersection The intersection to be closed.
     */
    public void closeIntersection(Intersection intersection) {
        
    }

    /**
     * Opens the given intersection. 
     * If the intersection is already happened, there is no effect.
     * @param intersection The intersection to be opened.
     */
    public void openIntersection(Intersection intersection) {
        
    }

    public void setAcmeOffice(Office acmeOffice) {
		this.acmeOffice = acmeOffice;
	}

	public Collection<Intersection> getIntersections() {
		return intersections;
	}

	public void setIntersections(Collection<Intersection> intersections) {
		this.intersections = intersections;
	}

	public Collection<Street> getStreets() {
		return streets;
	}

	public void setStreets(Collection<Street> streets) {
		this.streets = streets;
	}
}