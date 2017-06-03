package courierpd;

/**
 * The report sent out to a client delineating the deliveries made 
 * for a set period of time. This report shows an itemized list of 
 * the deliveries performed and the cost of each.
 */
public class BillingReport extends Report {

    /**
     * The client for which a billing report is generated.
     */
    private Client client;

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * The default constructor for a billing report. 
     * Once the report is created, actions may be 
     * taken on it so that it is written to a file.
     */
    public BillingReport() {
        // TODO - implement BillingReport.BillingReport
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the report required. This method should be overridden 
     * in the subclasses to provide the specific details and form of a given report.
     */
    public void generate() {
        // TODO - implement BillingReport.generate
        throw new UnsupportedOperationException();
    }

}