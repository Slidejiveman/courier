package courierpd;

import java.util.*;

/**
 * A report specifically for clients that shows how well the company as a whole has performed for them. Information, such as bonus amounts, is encapsulated into another report.
 */
public class CompanyPerformanceReport extends Report {

    /**
     * The couriers within the company required for the report. This should be every courier who was active during the period of performance the report captures.
     */
    private Collection<Courier> courier;
    /**
     * The client or clients who will receive the performance reports.
     */
    private Collection<Client> client;

    public Collection<Courier> getCourier() {
        return this.courier;
    }

    public void setCourier(Collection<Courier> courier) {
        this.courier = courier;
    }

    public Collection<Client> getClient() {
        return this.client;
    }

    public void setClient(Collection<Client> client) {
        this.client = client;
    }

    /**
     * The default constructor for a performance report. Once a reporting object is instantiated, operations are performed on it to produce a file in the file system.
     */
    public CompanyPerformanceReport() {
        // TODO - implement CompanyPerformanceReport.CompanyPerformanceReport
        throw new UnsupportedOperationException();
    }

}