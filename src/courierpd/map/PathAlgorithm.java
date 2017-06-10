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
    public static Route findShortestPath(DeliveryTicket currentOrder) {
        Route shortestRoute = new Route();
       
        Intersection officeLocation=null;
        Intersection pickupLocation = currentOrder.getPickUpClient().getLocation();
        Intersection deliveryLocation = currentOrder.getDeliveryClient().getLocation();
        
        //add all intersections from the map into the nodes set
        for(Intersection intersection: IntersectionDBAO.listIntersections()){
        	allNodes.add(intersection);
        }
        
        //get the office Intersection
        for(Intersection i: allNodes){
        	if(i.getXCoord()==3&&i.getYCoord()==3){
        		officeLocation = i;
        	}
        }
        System.out.println("From Office To Pickup");
        getShortestPathGivenFromToCoords(officeLocation,pickupLocation, currentOrder);
        reset();

        System.out.println("From Pickup To Delivery");
        getShortestPathGivenFromToCoords(pickupLocation,deliveryLocation, currentOrder);
        reset();

        System.out.println("From Delivery To Office");
        getShortestPathGivenFromToCoords(deliveryLocation,officeLocation, currentOrder);
        reset();

        return shortestRoute;
    }

	private static void getShortestPathGivenFromToCoords(Intersection fromLocation, Intersection toLocation,
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
		
		currentNode = pickNodeToEvaluate(getIntersectionsNotInThePathSet());
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
			
			currentNode = pickNodeToEvaluate(getIntersectionsNotInThePathSet());
		}
		
		//Printing the intersections in the shortestPath set
		for(Intersection i: shortestPathSet){
			System.out.println("("+i.getXCoord()+","+i.getYCoord()+")");
		}
	
	}
	@SuppressWarnings("null")
	public static Intersection pickNodeToEvaluate(ArrayList<Intersection> intersectionsNotInPathSet){
		Intersection lightNode=(Intersection) intersectionsNotInPathSet.toArray()[0];
		for(Intersection i: intersectionsNotInPathSet){
			if(i.getNodeValue()<lightNode.getNodeValue()){
				lightNode = i;
			}
		}
		//Integer smallestNodeValue = lightNode.getNodeValue(); // get the lightest node value
		//Set<Intersection> nodesWithEqualVal = null;
		
		/*
		//If there are several nodes with equal node value, look ahead and find the 
		//path with smallest next move
		
		for(Intersection i: getIntersectionsNotInThePathSet()){
			if(i.getNodeValue()==smallestNodeValue){
				nodesWithEqualVal.add(i);
			}
		}
		if(nodesWithEqualVal.size()>1){
			
			return null;
		}else{
			return lightNode;
		}*/
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
	@SuppressWarnings("null")
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
		for(int i=0;i<shortestPathSet.size();i++){
			shortestPathSet.remove(i);
		}
	}

}