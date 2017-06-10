package courieremail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import courierpd.core.Courier;
import courierpd.core.DeliveryTicket;

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
     * 
     * @param to1 - a Client email for a Client to be notified
     * @param to2 - a Client email for another Client to be notified
     * @param from - Ubiquity's email account address
     * @param host - this is smtp.gmail.com
     * @param userName - this is also Ubiquity's email account address
     * @param password - this is the password for Ubiquity's email account
     * @param packageID - Delivery Ticket with packageID and delivery time.
     */
    public static void sendConfirmationMail(String to1, String to2, String from, String host,
    		String userName, String password, DeliveryTicket deliveryTicket) {
    	
    	InternetAddress fromAddress = null;
    	InternetAddress to1Address = null;
    	InternetAddress to2Address = null;
    	Address[] addressArray = new Address[2];
    	int sendingPort = 465;
    	Properties properties = new Properties();
    	properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", String.valueOf(sendingPort));
        properties.put("mail.smtp.user", userName);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties);    
        
        // Convert the email accounts into Internet addresses.
        try {
        	 
            fromAddress = new InternetAddress(from);
            to1Address = new InternetAddress(to1);
            to2Address =  new InternetAddress(to2);
            addressArray[0] = to1Address;
            addressArray[1] = to2Address;
 
        } catch (AddressException addex) {
        	JOptionPane.showMessageDialog(null, 
        			"Sending failed.", "Failed to convert addresses!", JOptionPane.ERROR_MESSAGE);
        	addex.printStackTrace();
        }
        
        // populate the message and send it
        try {
        	// Create a default MimeMessage object.
        	MimeMessage message = new MimeMessage(session);
        	
        	// Set From: header field of the header
        	message.setFrom(fromAddress);
        	
        	// Set To: header field of the header.
        	// An array of addresses is expected, so it must be converted and casted.
        	message.addRecipients(Message.RecipientType.TO, addressArray);
        	
        	// Set Subject: header field.
        	message.setSubject("Package " + deliveryTicket.getPackageID() + ": Delivery Complete");
        	
        	// The actual message
        	message.setText("Your package, ID " + deliveryTicket.getPackageID() + 
        			", was delivered as of " + deliveryTicket.getActualDeliveryTime() +"\n" + 
        			"Thank you for using Acme Courier Services!");
        	
        	// Send the message
        	Transport transport = session.getTransport("smtps");
        	transport.connect(host, sendingPort, userName, password);
        	transport.sendMessage(message, message.getAllRecipients());
        	transport.close();
        	System.out.println("Sent message successfully");
        } catch (MessagingException mex) {
        	JOptionPane.showMessageDialog(null, 
        			"Sending failed.", "Failed to Send!", JOptionPane.ERROR_MESSAGE);
        	mex.printStackTrace();
        } 
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