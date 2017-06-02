package CourierPD;

/**
 * A static class used as a utility by a Route to populate it with the intersections the courier must traverse while on delivery.
 */
public class PathAlgorithm {

    /**
     * Given the state of the CityMap and the current delivery ticket, populates a Route object with valid intersections to take during the delivery. The given path, or Route, will be the quickest one to take.
     * @param currentMap Represents the current state of the City Center. The current state is required to produce accurate and usable delivery paths.
     * @param currentOrder The current delivery ticket being serviced. The current delivery ticket is needed to know who the sending and receiving clients are. The clients know their locations, which will determine which paths will be included into the route as the client locations are the delivery stops.
     */
    public static Route findShortestPath(CityMap currentMap, DeliveryTicket currentOrder) {
        // TODO - implement PathAlgorithm.findShortestPath
        throw new UnsupportedOperationException();
    }

}