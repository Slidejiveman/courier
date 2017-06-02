package CourierPD;

/**
 * Handles the printing of Route information out in a usable format by couriers. This will create a file, which may be referenced multiple times, should the need arise.
 */
public class DeliveryDirections {

    /**
     * The Route containing all of the information needed for the directions file.
     */
    private Route routeToPrint;

    public Route getRouteToPrint() {
        return this.routeToPrint;
    }

    public void setRouteToPrint(Route routeToPrint) {
        this.routeToPrint = routeToPrint;
    }

    /**
     * Writes the contents of the Route to a file. This will be printable and stored on the file system where users will easily be able to access directions that are generated. The default name of the file will be the packageID-Directions. The default file format will be .pdf.
     */
    public void writeToFile() {
        // TODO - implement DeliveryDirections.writeToFile
        throw new UnsupportedOperationException();
    }

    /**
     * No argument default constructor.
     */
    public DeliveryDirections() {
        // TODO - implement DeliveryDirections.DeliveryDirections
        throw new UnsupportedOperationException();
    }

}