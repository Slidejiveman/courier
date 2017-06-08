package courierdm;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.JList;

import courierpd.core.Client;

/**
 * This class will hold the database access code so that we can read values in
 * from the database and populate the application lists with good data.
 * 
 * @author rdnot
 *
 */
public class ClientDBAO {
    
	/**
	 * Add the given client to the database
	 * @param client to be added to the database.
	 */
	public static void saveClient(Client client) {
		CourierEntityManager.getEntityManager().persist(client);
	}
	
	/**
	 * Alias for saveClient method. They are identical.
	 * @param client
	 */
	public static void addClient(Client client) {
		CourierEntityManager.getEntityManager().persist(client);
	}
	
	// The SQL statements need to be checked.
	/**
	 * Returns the list of all clients in the database. This is very useful for list panels.
	 * @return List<Client> - all clients in the database
	 */
	public static List<Client> listClients() {
		TypedQuery<Client> query = CourierEntityManager.getEntityManager().createQuery("SELECT client FROM client client", Client.class);
		return query.getResultList();
	}
	
	/**
	 * Returns the client specified by the given id number.
	 * @param id - the number that uniquely identifies the client
	 * @return client - the client specified by the id
	 */
	public static Client findClientById(int id) {
		Client client = CourierEntityManager.getEntityManager().find(Client.class, new Integer(id));
		return client;
	}
	
	/**
	 * Removes the given client from the database.
	 * @param client
	 */
	public static void removeClient(Client client) {
		CourierEntityManager.getEntityManager().remove(client);
	}
	
	/**
	 * An alias for the removeClient method. It is identical.
	 * @param client
	 */
	public static void deleteClient(Client client) {
		CourierEntityManager.getEntityManager().remove(client);
	}
}
