package courierdm;

import java.util.List;

import javax.persistence.TypedQuery;

import courierpd.map.Intersection;

public class IntersectionDBAO {
	/**
	 * Add the given Intersection to the database
	 * @param Intersection to be added to the database.
	 */
	public static void saveIntersection(Intersection intersection) {
		CourierEntityManager.getEntityManager().persist(intersection);
	}
	
	/**
	 * Alias for saveIntersection method. They are identical.
	 * @param Intersection
	 */
	public static void addIntersection(Intersection intersection) {
		CourierEntityManager.getEntityManager().persist(intersection);
	}
	
	// The SQL statements need to be checked.
	/**
	 * Returns the list of all Intersections in the database. This is very useful for list panels.
	 * @return List<Intersection> - all Intersections in the database
	 */
	public static List<Intersection> listIntersections() {
		TypedQuery<Intersection> query = CourierEntityManager.getEntityManager().createQuery("SELECT intersection FROM intersection intersection", Intersection.class);
		return query.getResultList();
	}
	
	/**
	 * Returns the Intersection specified by the given id number.
	 * @param id - the number that uniquely identifies the Intersection
	 * @return Intersection - the Intersection specified by the id
	 */
	public static Intersection findIntersectionById(int id) {
		Intersection Intersection = CourierEntityManager.getEntityManager().find(Intersection.class, new Integer(id));
		return Intersection;
	}
	
	/**
	 * Removes the given Intersection from the database.
	 * @param Intersection
	 */
	public static void removeIntersection(Intersection intersection) {
		CourierEntityManager.getEntityManager().remove(intersection);
	}
	
	/**
	 * An alias for the removeIntersection method. It is identical.
	 * @param Intersection
	 */
	public static void deleteIntersection(Intersection intersection) {
		CourierEntityManager.getEntityManager().remove(intersection);
	}
}
