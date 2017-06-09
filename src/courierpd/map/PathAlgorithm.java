package courierpd.map;

import java.util.ArrayList;
import java.util.Set;

import courierdm.IntersectionDBAO;
import courierpd.core.DeliveryTicket;

/**
 * A static class used as a utility by a Route to populate it with the intersections 
 * the courier must traverse while on delivery.
 */
public class PathAlgorithm {

    /**
     * Given the state of the CityMap and the current delivery ticket, 
     * populates a Route object with valid intersections to take during 
     * the delivery. The given path, or Route, will be the quickest one to take.
     * @param currentMap Represents the current state of the City Center. 
     *     The current state is required to produce accurate and usable delivery paths.
     * @param currentOrder The current delivery ticket being serviced. 
     *     The current delivery ticket is needed to know who the sending 
     *     and receiving clients are. The clients know their locations, 
     *     which will determine which paths will be included into the 
     *     route as the client locations are the delivery stops.
     */
	private static Set<Intersection> shortestPathSet;
	private static Set<Intersection> allNodes;
	private static  Intersection currentNode = new Intersection();
    public static Route findShortestPath(CityMap currentMap, DeliveryTicket currentOrder) {
        Route shortestRoute = new Route();
        Office office = new Office();
        Intersection officeLocation = office.getLocation();
        Intersection pickupLocation = currentOrder.getPickUpClient().getLocation();
        Intersection deliveryLocation = currentOrder.getDeliveryClient().getLocation();
        
        //add all intersections from the map into the nodes set
        for(Intersection intersection: IntersectionDBAO.listIntersections()){
        	allNodes.add(intersection);
        }
        getShortestPathGivenFromToCoords(officeLocation,pickupLocation, currentOrder,currentMap);
        getShortestPathGivenFromToCoords(pickupLocation,deliveryLocation, currentOrder,currentMap);
        getShortestPathGivenFromToCoords(deliveryLocation,officeLocation, currentOrder,currentMap);

        return shortestRoute;
    }

	private static void getShortestPathGivenFromToCoords(Intersection fromLocation, Intersection toLocation,
			DeliveryTicket currentOrder, CityMap currentMap) {
		Integer edgeLength = 1; //one edge = 1 block.
			
		//for each intersection on the map, set their value to zero
		for(Intersection intersection: IntersectionDBAO.listIntersections()){
			if(!intersection.equals(fromLocation)){
				intersection.setNodeValue(Integer.MAX_VALUE);
			}
		}
		//set the node value of the from intersection to 0
		fromLocation.setNodeValue(0);
		currentNode = pickNodeToEvaluate();
		while(!currentNode.equals(toLocation)){
			shortestPathSet.add(currentNode);//adding the current node to the shortest path
			ArrayList<Intersection>adjacentNodes = new ArrayList<Intersection>();
			getAdjacentIntersections(adjacentNodes,currentNode);
			for(Intersection intersection:adjacentNodes){
				if(!shortestPathSet.contains(intersection)){
					if(currentNode.getNodeValue()+edgeLength>intersection.getNodeValue()){
						intersection.setNodeValue(currentNode.getNodeValue()+edgeLength);
					}
				}
			}
			currentNode = pickNodeToEvaluate();
		}
	
	}
	public static Intersection pickNodeToEvaluate(){
		Intersection lightNode=(Intersection) allNodes.toArray()[0];
		for(Intersection i: allNodes){
			if(i.getNodeValue()<lightNode.getNodeValue()){
				lightNode = i;
			}
		}
		return lightNode;
		
	}
	public static void getAdjacentIntersections(ArrayList<Intersection> adjNodes, Intersection currentNode){
		for(Intersection i: allNodes){
			if(Math.abs(i.getXCoord()-currentNode.getXCoord())==1
					||Math.abs(i.getYCoord()-currentNode.getYCoord())==1){
				adjNodes.add(i);
			}
		}
	}

}