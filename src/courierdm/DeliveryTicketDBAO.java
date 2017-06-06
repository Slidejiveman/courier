package courierdm;

import java.util.List;

import javax.persistence.TypedQuery;

import courierpd.core.DeliveryTicket;

/**
 * This class will hold the database access code so that we can read values in
 * from the database and populate the application lists with good data.
 * 
 * @author rdnot
 *
 */
public class DeliveryTicketDBAO {
	/**
	 * Add the given DeliveryTicket to the database
	 * @param DeliveryTicket to be added to the database.
	 */
	public static void saveDeliveryTicket(DeliveryTicket deliveryTicket) {
		CourierEntityManager.getEntityManager().persist(deliveryTicket);
	}
	
	/**
	 * Alias for saveDeliveryTicket method. They are identical.
	 * @param DeliveryTicket
	 */
	public static void addDeliveryTicket(DeliveryTicket deliveryTicket) {
		CourierEntityManager.getEntityManager().persist(deliveryTicket);
	}
	
	// The SQL statements need to be checked.
	/**
	 * Returns the list of all DeliveryTickets in the database. This is very useful for list panels.
	 * @return List<DeliveryTicket> - all DeliveryTickets in the database
	 */
	public static List<DeliveryTicket> listDeliveryTickets() {
		TypedQuery<DeliveryTicket> query = CourierEntityManager.getEntityManager().createQuery("SELECT delivery_ticket FROM delivery_ticket delivery_ticket", DeliveryTicket.class);
		return query.getResultList();
	}
	
	/**
	 * Returns the DeliveryTicket specified by the given id number.
	 * @param id - the number that uniquely identifies the DeliveryTicket
	 * @return DeliveryTicket - the DeliveryTicket specified by the id
	 */
	public static DeliveryTicket findDeliveryTicketById(int id) {
		DeliveryTicket deliveryTicket = CourierEntityManager.getEntityManager().find(DeliveryTicket.class, new Integer(id));
		return deliveryTicket;
	}
	
	/**
	 * Removes the given DeliveryTicket from the database.
	 * @param DeliveryTicket
	 */
	public static void removeDeliveryTicket(DeliveryTicket deliveryTicket) {
		CourierEntityManager.getEntityManager().remove(deliveryTicket);
	}
	
	/**
	 * An alias for the removeDeliveryTicket method. It is identical.
	 * @param DeliveryTicket
	 */
	public static void deleteDeliveryTicket(DeliveryTicket deliveryTicket) {
		CourierEntityManager.getEntityManager().remove(deliveryTicket);
	}
}
