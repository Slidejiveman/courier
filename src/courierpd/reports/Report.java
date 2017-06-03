package courierpd.reports;

import java.util.*;

import courierpd.DeliveryTicket;

/**
 * The abstract class from which reporting objects are derived. 
 * Reporting objects are a logical structure that have access 
 * to the main problem domain classes they require to generate 
 * report files. All other reports extend this class.
 */
public abstract class Report {

    /**
     * A flag used to determine whether the generated 
     * report should be monthly or weekly.
     */
    protected boolean isMonthly = false;
    /**
     * The collection of delivery tickets that a report 
     * needs to know about in order to print the necessary details. 
     * All reports need knowledge of delivery tickets.
     */
    protected Collection<DeliveryTicket> deliveryTicket;
    /**
     * The system date when a report is generated.
     */
    protected Date reportDate;
    /**
     * The start date of a report.
     */
    protected Date reportStartDate;
    /**
     * The end date of a report.
     */
    protected Date reportEndDate;
    /**
     * A unique identifier for a report.
     */
    protected int reportID;

    /**
     * Returns the flag which determines whether the report is 
     * weekly or monthly.
     */
    public boolean getIsMonthly() {
        return this.isMonthly;
    }

    /**
     * Set the monthly or weekly flag used to determine whether 
     * the generated report should be weekly or monthly.
     * @param isMonthly The value for the monthly or weekly flag.
     */
    public void setIsMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    /**
     * Return the delivery ticket set.
     */
    public Collection<DeliveryTicket> getDeliveryTicket() {
        return this.deliveryTicket;
    }

    /**
     * Set the delivery tickets needed for reporting.
     * @param deliveryTicket The delivery tickets needed for reporting.
     */
    public void setDeliveryTicket(Collection<DeliveryTicket> deliveryTicket) {
        this.deliveryTicket = deliveryTicket;
    }

    /**
     * Provides the ability for a report to be written to a file.
     */
    public void writeToFile() {
        // TODO - implement Report.writeToFile
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the date of the report.
     */
    public Date getReportDate() {
        return this.reportDate;
    }

    /**
     * Sets the date on which the report was generated.
     * @param reportDate
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * Creates the report required. This method should be overridden 
     * in the subclasses to provide the specific details and form 
     * of a given report.
     */
    public void generate() {
        // TODO - implement Report.generate
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the start date of a report.
     */
    public Date getReportStartDate() {
        return this.reportStartDate;
    }

    /**
     * Sets the start date of a report.
     * @param reportStartDate The start date of a report.
     */
    public void setReportStartDate(Date reportStartDate) {
        this.reportStartDate = reportStartDate;
    }

    /**
     * Returns the end date of a report.
     */
    public Date getReportEndDate() {
        return this.reportEndDate;
    }

    /**
     * Sets the end date of a report.
     * @param reportEndDate The end date of the report.
     */
    public void setReportEndDate(Date reportEndDate) {
        this.reportEndDate = reportEndDate;
    }

    /**
     * Generates a unique identifier for a report.
     */
    public void generateReportID() {
        // TODO - implement Report.generateReportID
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the reports unique identifier.
     */
    public int getReportID() {
        return this.reportID;
    }

    /**
     * Sets the reports unique identifier.
     * @param reportID The reports unique identifier.
     */
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

}