package courierdm;

import java.util.List;

import javax.persistence.TypedQuery;

import courierpd.core.BusinessParameters;

/**
 * This class will hold the database access code so that we can read values in
 * from the database and populate the application lists with good data.
 * 
 * Note: There should only ever be 1 Business Parameter object at any
 * given time.
 * 
 * Note: Do not remove the Business Parameters or Add them. They should
 * only be read in with the list and updated within the transaction.
 * The business_parameters table should only have one row, which is
 * populated by the database setup script with the default values.
 * 
 * @author rdnot
 *
 */
public class BusinessParametersDBAO {

	/**
	 * Add the given BusinessParameters to the database
	 * @param BusinessParameters to be added to the database.
	 */
	public static void saveBusinessParameters(BusinessParameters businessParameters) {
		CourierEntityManager.getEntityManager().persist(businessParameters);
	}
	
	/**
	 * Alias for saveBusinessParameters method. They are identical.
	 * @param BusinessParameters
	 */
	public static void addBusinessParameters(BusinessParameters businessParameters) {
		CourierEntityManager.getEntityManager().persist(businessParameters);
	}
	
	/**
	 * Returns the list of all BusinessParameterss in the database. This is very useful for list panels.
	 * @return List<BusinessParameters> - all BusinessParameterss in the database
	 */
	public static List<BusinessParameters> listBusinessParameterss() {
		TypedQuery<BusinessParameters> query = CourierEntityManager.getEntityManager().createQuery("SELECT business_parameters FROM business_parameters business_parameters", BusinessParameters.class);
		return query.getResultList();
	}
	
	/**
	 * Returns the BusinessParameters specified by the given id number.
	 * @param id - the number that uniquely identifies the BusinessParameters
	 * @return BusinessParameters - the BusinessParameters specified by the id
	 */
	public static BusinessParameters findBusinessParametersById(int id) {
		BusinessParameters BusinessParameters = CourierEntityManager.getEntityManager().find(BusinessParameters.class, new Integer(id));
		return BusinessParameters;
	}
	
	/**
	 * Removes the given BusinessParameters from the database.
	 * @param BusinessParameters
	 */
	public static void removeBusinessParameters(BusinessParameters businessParameters) {
		CourierEntityManager.getEntityManager().remove(businessParameters);
	}
	
	/**
	 * An alias for the removeBusinessParameters method. It is identical.
	 * @param BusinessParameters
	 */
	public static void deleteBusinessParameters(BusinessParameters businessParameters) {
		CourierEntityManager.getEntityManager().remove(businessParameters);
	}

}
