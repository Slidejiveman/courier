package courierdm;

import java.util.List;

import javax.persistence.TypedQuery;

import courierpd.core.User;

/**
 * This class will hold the database access code so that we can read values in
 * from the database and populate the application lists with good data.
 * 
 * @author rdnot
 *
 */
public class EmployeeDBAO {
	/**
	 * Add the given User to the database
	 * @param User to be added to the database.
	 */
	public static void saveUser(User user) {
		CourierEntityManager.getEntityManager().persist(user);
	}
	
	/**
	 * Alias for saveUser method. They are identical.
	 * @param User
	 */
	public static void addUser(User user) {
		CourierEntityManager.getEntityManager().persist(user);
	}
	
	// The SQL statements need to be checked.
	/**
	 * Returns the list of all Users in the database. This is very useful for list panels.
	 * @return List<User> - all Users in the database
	 */
	public static List<User> listUsers() {
		TypedQuery<User> query = CourierEntityManager.getEntityManager().createQuery("SELECT employee FROM employee employee", User.class);
		return query.getResultList();
	}
	
	/**
	 * Returns the User specified by the given id number.
	 * @param id - the number that uniquely identifies the User
	 * @return User - the User specified by the id
	 */
	public static User findUserById(int id) {
		User User = CourierEntityManager.getEntityManager().find(User.class, new Integer(id));
		return User;
	}
	
	/**
	 * Removes the given User from the database.
	 * @param User
	 */
	public static void removeUser(User user) {
		CourierEntityManager.getEntityManager().remove(user);
	}
	
	/**
	 * An alias for the removeUser method. It is identical.
	 * @param User
	 */
	public static void deleteUser(User user) {
		CourierEntityManager.getEntityManager().remove(user);
	}
}
