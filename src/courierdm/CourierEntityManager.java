package courierdm;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CourierEntityManager {
    
	static EntityManagerFactory entityManagerFactory;
	static EntityManager em;
	
	private static void initEM() {
		entityManagerFactory = Persistence.createEntityManagerFactory("courierdb");
		em = entityManagerFactory.createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		if(em == null) {
			initEM();
		}
		return em;
	}
	
	public static void close() {
		em.close();
		entityManagerFactory.close();
	}
	
	private CourierEntityManager() {
		
	}
}
