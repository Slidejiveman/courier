package courierpd;

/**
 * A static class used to interact with the mail server. 
 * This provides utilities to parse emails from couriers 
 * as well as send confirmation emails out to clients 
 * when a delivery has been reported as complete.
 */
public class EmailUtil {

    /**
     * Parses an email received from a valid courier.
     */
    public static void parseMail() {
        // TODO - implement EmailUtil.parseMail
        throw new UnsupportedOperationException();
    }

    /**
     * Sends confirmation emails out to the clients of a given delivery 
     * ticket once the delivery time has been set.
     * @param sender The client who sent a package to the receiving client.
     * @param receiver The client receiving the package from the sending client.
     */
    public static void sendConfirmationMail(Client sender, Client receiver) {
        // TODO - implement EmailUtil.sendConfirmationMail
        throw new UnsupportedOperationException();
    }

    /**
     * Writes the time reported to a courier onto the delivery ticket. 
     * This is done after parsing the email from the courier to know 
     * which one of the delivery ticket fields needs to be updated with the new time.
     * @param currentOrder The delivery ticket to update with the new time information.
     */
    public static void updateDeliveryTicket(DeliveryTicket currentOrder) {
        // TODO - implement EmailUtil.updateDeliveryTicket
        throw new UnsupportedOperationException();
    }

    /**
     * Determines that the email originated from a valid courier. 
     * If the email did not originate from a valid courier, then it is ignored. 
     * Valid courier emails are required in order to update the delivery ticket via email.
     * @param courier The courier who is performing the delivery. 
     *        A courier knows its own email, so the email field is 
     *        validated against the sender field in received email messages.
     */
    public static void validateSender(Courier courier) {
        // TODO - implement EmailUtil.validateSender
        throw new UnsupportedOperationException();
    }

}