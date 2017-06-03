package courierpd;

/**
 * A static class used for determining the default courier to suggest for a given delivery ticket. All active couriers can be read in from the database, and that information can be used to determine which courier to use.
 */
public class DefaultCourierAlgorithm {

    /**
     * The algorithm for determining which courier to suggest to an OrderTaker while filling in the delivery ticket fields. This is determined by whether or not a courier is currently out on delivery and the number of deliveries a courier has performed on a given day.
     */
    public static void suggestDefaultCourier() {
        // TODO - implement DefaultCourierAlgorithm.suggestDefaultCourier
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the Couriers from the database by interacting with the Courier database access object.
     */
    public static Courier read() {
        // TODO - implement DefaultCourierAlgorithm.read
        throw new UnsupportedOperationException();
    }

}