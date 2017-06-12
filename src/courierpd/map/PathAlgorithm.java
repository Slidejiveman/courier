package courierpd.map;

import java.util.ArrayList;
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

	
	private static ArrayList<Intersection> shortestPathSet = new ArrayList<Intersection>();
	private static ArrayList<Intersection> allNodes = new ArrayList<Intersection>();
	private static  Intersection currentNode = new Intersection();
	ArrayList<Intersection>nodesToIgnore = new ArrayList<Intersection>();

    public static Route findShortestPath(DeliveryTicket currentOrder) {
        Route shortestRoute = new Route();
        ArrayList<Intersection> pathFromOfficeToPickup = new ArrayList<Intersection>();
        ArrayList<Intersection> pathFromPickupToDelivery = new ArrayList<Intersection>();
        ArrayList<Intersection>pathFromDeliveryToOffice = new ArrayList<Intersection>();
        Intersection officeLocation=null;
        Intersection pickupLocation = currentOrder.getPickUpClient().getLocation();
        Intersection deliveryLocation = currentOrder.getDeliveryClient().getLocation();
        //add all intersections from the map into the nodes set
        for(Intersection intersection: IntersectionDBAO.listIntersections()){
        	if(intersection.getIsOpen()){
            	allNodes.add(intersection);
        	}
        }
        
        //get the office Intersection
        for(Intersection i: allNodes){
        	if(i.getXCoord()==3&&i.getYCoord()==3){
        		officeLocation = i;
        	}
        }
        
        System.out.println("From Office To Pickup");
        pathFromOfficeToPickup = getShortestPathGivenFromToCoords(officeLocation,pickupLocation, currentOrder);
        System.out.println();
        reset();

        System.out.println("From Pickup To Delivery");
        pathFromPickupToDelivery = getShortestPathGivenFromToCoords(pickupLocation,deliveryLocation, currentOrder);
        System.out.println();
        reset();
		
        System.out.println("From Delivery To Office");
        pathFromDeliveryToOffice = getShortestPathGivenFromToCoords(deliveryLocation,officeLocation, currentOrder);
        System.out.println();
        reset();
        
        //Adding the shortest paths to the route
        shortestRoute.setOfficeToPickupPath(pathFromOfficeToPickup);
        shortestRoute.setPickupToDeliveryPath(pathFromPickupToDelivery);
        shortestRoute.setDeliveryToOfficePath(pathFromDeliveryToOffice);
		
        return shortestRoute;
    }
	private static ArrayList<Intersection> getShortestPathGivenFromToCoords(Intersection fromLocation, Intersection toLocation,
			DeliveryTicket currentOrder) {
		Integer edgeLength = 1; //one edge = 1 block.
			
		//for each intersection on the map, set their value to MAX VALUE
		for(Intersection intersection: IntersectionDBAO.listIntersections()){
			if(!intersection.equals(fromLocation)){
				intersection.setNodeValue(Integer.MAX_VALUE);
			}
		}
		//set the node value of the from intersection to 0
		fromLocation.setNodeValue(0);
		currentNode = pickNodeToEvaluate(getIntersectionsNotInThePathSet(),toLocation);
		shortestPathSet.add(currentNode);//adding the source node to the shortest path

		while(!currentNode.equals(toLocation)){
			ArrayList<Intersection>adjacentNodes = new ArrayList<Intersection>();
			getAdjacentIntersections(adjacentNodes,currentNode);
			for(Intersection intersection:adjacentNodes){
				if(!shortestPathSet.contains(intersection)){
					if((currentNode.getNodeValue()+edgeLength)<intersection.getNodeValue()){
						intersection.setNodeValue(currentNode.getNodeValue()+edgeLength);
					}
				}
			}
			Intersection currentNodeCopy = currentNode; 
			currentNode = pickNodeToEvaluate(getIntersectionsNotInThePathSet(),toLocation);
			currentNode.setPrevious(currentNodeCopy);
			shortestPathSet.add(currentNode);//adding the current node to the shortest path

		}
		
		//Printing the intersections in the shortestPath set
		for(Intersection i: shortestPathSet){
			System.out.print("("+i.getXCoord()+","+i.getYCoord()+")");
		}
		return shortestPathSet;
	}
	public static Intersection pickNodeToEvaluate(ArrayList<Intersection> intersectionsNotInPathSet, Intersection toLocation){
		Intersection lightNode=(Intersection) intersectionsNotInPathSet.toArray()[0];
		for(Intersection i: intersectionsNotInPathSet){
			if(i.getNodeValue()<lightNode.getNodeValue()){
				lightNode = i;
			}
		}
		
		//If there are more than 1 nodes with the same value, choose a best move
		//by looking ahead, what would take less distance to the 
		// destination node
		ArrayList<Intersection> nodesMatchingVal = new ArrayList<Intersection>();
		for(Intersection intersection: intersectionsNotInPathSet){
			if(intersection.getNodeValue()==lightNode.getNodeValue()){
				nodesMatchingVal.add(intersection);
			}
		}
		
		if(nodesMatchingVal.size()>1){
			Intersection bestMove =nodesMatchingVal.get(0);
			int minDistToDest = calculateMinCostToDest(nodesMatchingVal.get(0),toLocation);
			for(Intersection i: nodesMatchingVal){
				int costToDestination = calculateMinCostToDest(i,toLocation);
				if(costToDestination<minDistToDest){
					minDistToDest = costToDestination;
					bestMove = i;
				}
				
			}
			//set the values of the adjacent nodes that were ignored to infinity
			for(Intersection i: nodesMatchingVal){
				if(!i.equals(bestMove)){
					i.setNodeValue(Integer.MAX_VALUE);
				}
			}

			return bestMove;	//return the best move
		}
		return lightNode;
	}
	public static void getAdjacentIntersections(ArrayList<Intersection> adjNodes, Intersection currentNode){
		for(Intersection i: allNodes){
			if((i.getXCoord()==currentNode.getXCoord())&&(Math.abs(i.getYCoord()-currentNode.getYCoord())==1)
					||(i.getYCoord()==currentNode.getYCoord())&&(Math.abs(i.getXCoord()-currentNode.getXCoord())==1)){
				adjNodes.add(i);
			}
		}
	}
	public static ArrayList<Intersection> getIntersectionsNotInThePathSet(){
		ArrayList<Intersection> intersections= new ArrayList<Intersection>();
		for(Intersection i: allNodes){
			if(!shortestPathSet.contains(i)){
				intersections.add(i);
			}
		}
		return intersections;
	}
	public static void reset(){
		for(Intersection i: allNodes){
			i.setNodeValue(Integer.MAX_VALUE);
		}
		shortestPathSet = new ArrayList<Intersection>();
	}

	public static Integer calculateMinCostToDest(Intersection i, Intersection destination){
		return (Math.abs(destination.getXCoord()-i.getXCoord())+Math.abs(destination.getYCoord()-i.getYCoord()));
	}
}