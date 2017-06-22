package courierpd.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import courierdm.BusinessParametersDBAO;
import courierpd.core.BusinessParameters;
import courierpd.core.DeliveryTicket;
import courierpd.enums.Direction;

/**
 * The Route is the path the courier will take throughout 
 * the course of a delivery. This will be the shortest 
 * path to complete the job.
 */
public class Route {

    /**
     * Represents the current delivery ticket being serviced. 
     * This will be used with the static path finder algorithm.
     */
    private DeliveryTicket currentOrder;
    /**
     * The current state of the City Center. 
     * This is used with the static path finder algorithm 
     * to populate the route with the path the courier should 
     * actually take.
     */
    private CityMap currentMap;
    /**
     * Represents all of the intersections that will be crossed 
     * during the courier's trip. The values in the used intersections 
     * may not be unique. The return journey may include the same 
     * streets made on the outset. Intersections function as nodes 
     * in the shortest path algorithm.
     */
    private ArrayList<Intersection> usedIntersections = new ArrayList<Intersection>();
    /**
     * The directions are the enumerators associated with the 
     * turns made at intersections. These are used for printing out 
     * the directions for a courier. At each intersection, the 
     * courier needs to know which direction must be in order to 
     * make it to the next intersection.
     */
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    /**
     * The lengths that each street must be traveled to the next turn 
     * intersection. This is a parallel structure with the directions 
     * and the usedIntersections.
     */
    private int streetTravelLengths;
    /**
     * The used streets for the shortest path. Streets function as edges 
     * in the graph algorithm.
     */
    private Collection<Street> usedStreets;

    /**
     * The default no argument constructor.
     */
    private ArrayList<Intersection> officeToPickupPath;
    private ArrayList<Intersection> pickupToDeliveryPath;
    private ArrayList<Intersection> deliveryToOfficePath;
    private Integer blocksCount=0;
    private Integer blocksToPickup=0;
    private Integer blocksToDelivery=0;
    private BusinessParametersDBAO parametersDBAO = new BusinessParametersDBAO();
    BusinessParameters params = new BusinessParameters();
    
    @SuppressWarnings("static-access")
	public Route() {
      setOfficeToPickupPath(new ArrayList<Intersection>());
      setPickupToDeliveryPath(new ArrayList<Intersection>());
      setDeliveryToOfficePath(new ArrayList<Intersection>());
      this.params= this.parametersDBAO.listBusinessParameterss().get(0);
    }

    /**
     * Once the path is determined, it can be used to estimate the delivery 
     * time based on the average speed a courier travels, which is one 
     * of the business parameters adjustable by the Owner. The estimated 
     * delivery time amounts to the estimated number of blocks to travel, 
     * converting that to miles then dividing that by the courier's average speed.
     */
    @SuppressWarnings("deprecation")
	public Date estimateDeliveryTime() {
    	Date estimatedDeliveryTime = new Date();
    	System.out.println("Blocks to delivery" +blocksToDelivery);
    	int minutesUsed = params.getDeliveryDelay()+(int) Math.ceil(0.1 * 60*(this.blocksToDelivery-this.blocksToPickup)/(params.getAvgCourierSpeed()));
    	System.out.println("Minutes used to delivery: "+minutesUsed);
    	int hours = (int)(minutesUsed/60);
    	System.out.println("Hours: "+hours);
		int minutes = (minutesUsed%60);
		System.out.println("Minutes: "+minutes);
		estimatedDeliveryTime.setHours(this.estimateDepartureTime().getHours()+hours);
		estimatedDeliveryTime.setMinutes(this.currentOrder.getRequestedPickUpTime().getMinutes()+minutes);
		System.out.println("delivery time hour: "+estimatedDeliveryTime.getHours());
		System.out.println("delivery time mins: "+estimatedDeliveryTime.getMinutes());
    	return  estimatedDeliveryTime;
    }

    @SuppressWarnings("deprecation")
	public Date estimateDepartureTime() {
		Date estimatedPickupTime = new Date();
    	System.out.println("Blocks to pickup" +blocksToPickup);

		int minutesToPickup = params.getPickUpDelay()+(int)Math.ceil(0.1*60*this.blocksToPickup/(params.getAvgCourierSpeed()));
		int hours = (int)(minutesToPickup/60);
		int minutes = (minutesToPickup%60);
		System.out.println("time minutes"+ (this.currentOrder.getRequestedPickUpTime().getMinutes()));
		estimatedPickupTime.setHours(this.currentOrder.getRequestedPickUpTime().getHours()-hours);
		estimatedPickupTime.setMinutes(this.currentOrder.getRequestedPickUpTime().getMinutes()-minutes);
		return estimatedPickupTime;
	}

	/**
     * Calculates the number of blocks that should be traveled 
     * throughout the deliver. This is determined with the used 
     * intersections and calculated based on the difference between 
     * the numerical values associated with the names of said intersections. 
     * For example, if the two intersections A St. & 1st Ave. and A St. & 2nd Ave. 
     * were used, then the difference between the two would be 1, which is the 
     * absolute value of the difference between 1 and 2.
     */
    public Integer estimateBlocks() {
        // TODO - implement Route.estimateBlocks
        return this.blocksCount;
    }

    /**
     * The estimated price for the delivery based on the estimated number 
     * of blocks times the business parameters for billing base and rate.
     */
    public float estimatePrice() {
        return (float) (params.getBillingBase()+(params.getBillingRate()*this.estimateBlocks()));
    }

    /**
     * Within this method, the static call to the shortest path algorithm 
     * utility class is made. After this function has executed, the 
     * usedIntersections represent the shortest path within the Route class.
     */
    public ArrayList<Intersection> findUsedIntersections() {
        return this.usedIntersections;
    }
   
    /**
     * Determines the length, in blocks, of the edge between two intersections 
     * used for turning to stay on the shortest path.
     */
    public void findLengths() {
        // TODO - implement Route.findLengths
        throw new UnsupportedOperationException();
    }

    /**
     * Finds the block length between two intersections used on the shortest path. 
     * This is a helper method for the findLengths() operation.
     */
    public void findSingleLength() {
        // TODO - implement Route.findSingleLength
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the delivery ticket this route is associated with.
     */
    public DeliveryTicket getCurrentOrder() {
        return this.currentOrder;
    }

    /**
     * Sets the order ticket this route will be associated with.
     * @param currentOrder The current delivery ticket to be used with this route.
     */
    public void setCurrentOrder(DeliveryTicket currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Returns the directions collection. This would normally be used for 
     * generating the directions for a courier.
     */
    public Collection<Direction> getDirections() {
        return this.directions;
    }

    /**
     * Sets the directions collection associated with this Route.
     * @param directions The directions to be associated with the Route.
     */
    public void setDirections(ArrayList<Direction> directions) {
        this.directions = directions;
    }

    /**
     * Returns the collection of travel lengths between intersections in the Route.
     */
    public int getStreetTravelLengths() {
        return this.streetTravelLengths;
    }

    /**
     * Sets the street travel lengths collection for a given Route.
     * @param streetTravelLengths The travel lengths for a given Route.
     */
    public void setStreetTravelLengths(int streetTravelLengths) {
        this.streetTravelLengths = streetTravelLengths;
    }

	public ArrayList<Intersection> getOfficeToPickupPath() {
		return officeToPickupPath;
	}

	public void setOfficeToPickupPath(ArrayList<Intersection> officeToPickupPath) {
		this.officeToPickupPath = officeToPickupPath;
	}

	public ArrayList<Intersection> getPickupToDeliveryPath() {
		return pickupToDeliveryPath;
	}

	public void setPickupToDeliveryPath(ArrayList<Intersection> pickupToDeliveryPath) {
		this.pickupToDeliveryPath = pickupToDeliveryPath;
	}

	public ArrayList<Intersection> getDeliveryToOfficePath() {
		return deliveryToOfficePath;
	}

	public void setDeliveryToOfficePath(ArrayList<Intersection> deliveryToOfficePath) {
		this.deliveryToOfficePath = deliveryToOfficePath;
	}
	
	public String getTranslatedDirections(){
		
		String directions = "\n\n";
		String tab = "\t";
		directions+=tab+tab+" From "+this.getOfficeToPickupPath().get(0).getName()+" (office) to the pickup location: \n";
		translatePath(this.officeToPickupPath);
		directions+=getsimplifiedDirections();
		blocksToPickup+=blocksCount;
		directions+=tab+tab+" From "+this.getPickupToDeliveryPath().get(0).getName()+" (the pickup location) to the delivery location: \n";
		translatePath(this.pickupToDeliveryPath);
		directions+=getsimplifiedDirections();
		blocksToDelivery+=blocksCount;
		directions+=tab+tab+" From "+this.getDeliveryToOfficePath().get(0).getName()+" (the delivery location) back to office: \n";
		translatePath(this.deliveryToOfficePath);
		directions+=getsimplifiedDirections();
		directions+="\n";
		this.currentOrder.setEstBlocks(blocksCount);
		return directions;
	}
	public String translatePath(ArrayList<Intersection> path){
		
		/*
		 * A string variable, translatedPath holds the English 
		 * version of the delivery instructions and initially has the value of “”
		 */
		String translatedPath = "";
		String tab = "\t";
		int counter=0;
		Intersection currentIntersection = path.get(0);
		this.usedIntersections.add(currentIntersection);
		while(counter<path.size()-1){
			Intersection nextIntersection = path.get(counter+1);
			Street street = new Street();
			street.setFrom(currentIntersection);
			street.setTo(nextIntersection);
			String direction ="";
			
			/*
			 * Compare the x coordinates
			 *	of the current intersection with the next intersection.
			*/
			int XsDifference = nextIntersection.getXCoord()-currentIntersection.getXCoord();
			
			/*
			 * Compare the y coordinate value of the current intersection
			 *	with the y coordinate value of the next intersection on the list.
			 */
			int YsDifference = nextIntersection.getYCoord()-currentIntersection.getYCoord(); 
			
			/*
			 * If the x values are equal, Subtract the “from” intersection y value
			 *	from the “to” intersection y value.
			 */
			if(XsDifference==0){
				
				/*
				 * If the difference is greater than 0, the courier will go North,
				 * street.length miles.
				 */
				if(YsDifference>0){
					directions.add(Direction.South);
					direction = tab+"Go " + Direction.South + " to "+nextIntersection.getName();
				}
			
				/*
				 * Otherwise (difference is less than 0), the courier will go South to the
				 * to intersection, street.length miles
				 */
				if(YsDifference<0){
					directions.add(Direction.North);
					direction = tab+"Go " + Direction.North + " to "+nextIntersection.getName();
				}
			}
				
			/*
			 * If the y values are equal, subtract the “from” intersection x value 
			 * from the “to” intersection x value.
			 */
			if(YsDifference==0){
				/*
				 * If the difference is greater than 0, the courier will go East, 
				 * street.length miles.
				 */
				if(XsDifference>0){
					directions.add(Direction.East);
					direction = tab+"Go " + Direction.East + " to "+nextIntersection.getName();
				}
			
				/*
				 * Otherwise (difference is less than 0), the courier will go West 
				 * to the “to” intersection, street.length miles.
				 */
				if(XsDifference<0){
					directions.add(Direction.West);
					direction = tab+"Go " + Direction.West + " to "+nextIntersection.getName();
				}
			}
				
			// Append the new direction to the list of existing directions (translatedPath).
			translatedPath+=direction+"\n";
			
			//update current intersection and the counter
			currentIntersection = nextIntersection;
			this.usedIntersections.add(currentIntersection);
			counter++;
		}
		
		return translatedPath;
	}
	/**
     * This method examines the intersections found among those that are 
     * used in the shortest path and determines which direction the courier 
     * will have to turn to stay on the path.
     */
	public String getsimplifiedDirections(){
		String direction="";
		int fromIndex = 0;
		int toIndex=0;
		int counter = 0;
		String turnDirection="";
		String tab = "\t";
		ArrayList<Direction> simplifiedDirections = new ArrayList<Direction>();
		while (counter<this.directions.size()-1){
			if(this.directions.get(counter).equals(this.directions.get(counter+1))){
				toIndex=counter+1;
			}else{
				simplifiedDirections.add(this.directions.get(toIndex));
				if((directions.get(toIndex)).equals(Direction.North)){
					if((directions.get(toIndex+1)).equals(Direction.East)){
						turnDirection="Turn Right, ";
					}else if((directions.get(toIndex+1)).equals(Direction.West)){
						turnDirection="Turn Left, ";
					}
					
				}else if((directions.get(toIndex)).equals(Direction.South)){
					if((directions.get(toIndex+1)).equals(Direction.East)){
						turnDirection="Turn Left, ";
					}else if((directions.get(toIndex+1)).equals(Direction.West)){
						turnDirection="Turn Right, ";
					}
				}else if((directions.get(toIndex)).equals(Direction.East)){
					if((directions.get(toIndex+1)).equals(Direction.South)){
						turnDirection="Turn Right, ";
					}else if((directions.get(toIndex+1)).equals(Direction.North)){
						turnDirection="Turn Left, ";
					}
				}else if((directions.get(toIndex)).equals(Direction.West)){
					if((directions.get(toIndex+1)).equals(Direction.South)){
						turnDirection="Turn Left, ";
					}else if((directions.get(toIndex+1)).equals(Direction.North)){
						turnDirection="Turn Right, ";
					}
				}
				direction +=tab+tab+tab+turnDirection+ " keep straight "+" for "+ ((toIndex+1)-fromIndex) 
						+" blocks to "+this.usedIntersections.get(toIndex+1).getName()+" on the "
						+getStreetNameGivenCoordinate(usedIntersections.get(toIndex));
				direction+="\n";
				toIndex++;
				fromIndex=toIndex;
			}
			if(toIndex == this.directions.size()-1){
				simplifiedDirections.add(this.directions.get(toIndex));
				direction +=tab+tab+tab+turnDirection+ "keep straight "+" for "+ ((toIndex+1)-fromIndex) 
						+" blocks to "+this.usedIntersections.get(toIndex+1).getName()+" on the "
						+getStreetNameGivenCoordinate(usedIntersections.get(toIndex));
				direction+="\n";
			}
			
			counter++;
		}
		
		//create a new directions collection
		blocksCount+=this.directions.size();
		this.directions=new ArrayList<Direction>();
		this.usedIntersections=new ArrayList<Intersection>();
		return direction;
	}

	private String getStreetNameGivenCoordinate(Intersection intersection) {
		String streetName="";
		if(intersection.getXCoord()==0){
			streetName = "A Street";
		}else if(intersection.getXCoord()==1){
			streetName = "B Street";
		}else if(intersection.getXCoord()==2){
			streetName = "C Street";
		}else if(intersection.getXCoord()==3){
			streetName = "D Street";
		}else if(intersection.getXCoord()==4){
			streetName = "E Street";
		}else if(intersection.getXCoord()==5){
			streetName = "F Street";
		}else if(intersection.getXCoord()==6){
			streetName = "G Street";
		}
		return streetName;
	}

	public boolean deliveryTimesMet() {
		if((this.currentOrder.getActualDeliveryTime().getTime()
				-this.currentOrder.getEstDeliveryTime().getTime())>=params.getBonusWindow()
				*600000){
			return true;
		}else	
			return false;
	}

}