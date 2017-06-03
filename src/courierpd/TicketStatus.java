package courierpd;

/**
 * Ticket Status is used to specify whether a delivery ticket 
 * is in one of three valid states in the system: 
 * Open, Closed, or Canceled.
 */
public enum TicketStatus {
    /**
     * Denotes that a delivery ticket is opened and currently 
     * being serviced. This is the default state for a ticket 
     * in the system.
     */
    Opened,
    /**
     * Denotes that a delivery ticket is in the closed state. 
     * This means that the package was successfully delivered.
     */
    Closed,
    /**
     * Denotes that a delivery ticket is in the canceled state. 
     * This means that a delivery ticket was opened but that a 
     * package was not delivered for some reason.
     */
    Canceled
}