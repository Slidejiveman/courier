package courieremail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import courierpd.core.Courier;
import courierpd.core.DeliveryTicket;

/**
 * A static class used to interact with the mail server. 
 * This provides utilities to parse emails from couriers 
 * as well as send confirmation emails out to clients 
 * when a delivery has been reported as complete.
 */
public class EmailUtil {
	
	private static final String userName = "ubiquitymail@gmail.com";
	private static final String password = "UbiquityM41l$";
	
	
    /**
     * Parses an email received from a valid courier.
     */
    public static void parseMail() {
        // TODO - implement EmailUtil.parseMail
        throw new UnsupportedOperationException();
    }
    
    /**
     * Listening for Courier emails to come in.
     * Whenever there is a hit in the Gmail IMAP
     * inbox, then the last email to arrive is parsed
     * and the delivery ticket is updated with the
     * reported time.
     */
    public static void listenForIncomingEmail() {
    	
    	/**
    	 * Defines properties needed to connect to the
    	 * gmail instances incoming mail.
    	 */
    	Properties props = new Properties();
    	props.put("mail.store.protocol", "imaps");
    	props.put("mail.imaps.host", "imap.gmail.com");
    	props.put("mail.imaps.port", "993");
    	props.put("mail.imaps.timeout", "10000");
    	
    	// The mail session.
    	Session session = Session.getInstance(props);
    	
    	IMAPStore store = null;
    	Folder inbox = null;
    	
    	try {
    		store = (IMAPStore) session.getStore("imaps");
    		store.connect(userName, password);
    		
    		// Check to see if the mail holder can support idling.
    		if (!store.hasCapability("IDLE")) {
    			throw new RuntimeException("IDLE not supported");
    		}
    		
    		// Listening to the inbox
    		inbox = (IMAPFolder) store.getFolder("INBOX");
    		// Count listener performs activities when a new email comes in.
    		// This code block will be modified to handle the parsing of the
    		// email based on a newly added one.
    		inbox.addMessageCountListener(new MessageCountAdapter() {
    			
    			// This method handles the possibility that multiple
    			// messages may have been added during a single event.
    			@Override
    			public void messagesAdded(MessageCountEvent event) {
    				Message[] messages = event.getMessages();
    				
    				for (Message message : messages) {
    					try {
    						System.out.println("Mail Subject:- " + message.getSubject());
    					} catch (MessagingException mex) {
    						mex.printStackTrace();
    					}
    				}
    			}
    		});
    		
    		// Start the thread that will continuously be listening
    		// for new emails. 
    		IdleThread idleThread = new IdleThread(inbox);
    		idleThread.setDaemon(true); // This Thread is a daemon, so it is helper processing and dies as soon as app does.
    		idleThread.start();
    		//idleThread.join(); // join causes the next thread to wait until this is finished to start.
    		//idleThread.kill(); // example of how to stop this thread from another thread
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		close(inbox);
    		close(store);
    	}
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
    		 DeliveryTicket deliveryTicket) {
    	
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

    
    /**
     * Private inner class used to allow Ubiquity to listen for emails while
     * the application is running. In order for the program to listen as
     * well as perform the operations desired by the customer, there has
     * to be a second thread.
     * 
     * The IdleThread extends the Java Thread class, which allows it to work.
     * 
     * @author rdnot
     * @see https://stackoverflow.com/questions/23424003/right-way-to-poll-gmail-inbox-for-incoming-mail-from-stand-alone-application/44405826
     */
    private static class IdleThread extends Thread {
    	/**
    	 * The folder to open
    	 */
    	private final Folder folder;
    	/**
    	 * volatile is required in order for this to be
    	 * seen from multiple threads. Otherwise, some threads
    	 * would see non-updated data.
    	 */
    	private volatile boolean running = true;
    	
    	/**
    	 * Constructor for the IdleThread. It calls super
    	 * to set up a Java Thread. It also sets up the folder
    	 * that is going to be used.
    	 * @param folder - the folder to open and listen to (inbox)
    	 */
    	public IdleThread(Folder folder) {
    		super();
    		this.folder = folder;
    	}
    	
    	/**
    	 * This synchronized method stops the thread from running.
    	 * The running boolean variable is used in the run method's
    	 * while loop to determine whether the thread should continue to listen.
    	 * 
    	 * This method is synchronized, meaning that it can only be called by
    	 * one thread at a time. This makes this "thread-safe".
    	 * 
    	 * Unused warning suppressed because if it is to be used, it will be used
    	 * from another class. There's no need for a warning.
    	 */
    	@SuppressWarnings("unused")
    	public synchronized void kill() {
    		if (!running) {
    			return;
    		}
    		this.running = false;
    	}
    	
    	/**
    	 * Runs the thread. It ensures that there is a connection to the
    	 * Gmail folder. Then it idles, waiting for updates.
    	 */
    	@Override
    	public void run() {
    		// until the thread is killed, make sure the inbox folder is
    		// open, and idle.
    		while (running) {
    			try {
    				ensureOpen(folder);
    				System.out.println("enter idle");
    				((IMAPFolder) folder).idle();
    			} catch (Exception ex) {
    				// if an error occurs, we want to wait
    				// then try again.
    				ex.printStackTrace();
    				try {
    					Thread.sleep(1000); // 1 second
    				} catch (InterruptedException iex) {
    					System.err.println("I AM ERROR: The thread was interrupted. Trying again....");
    				}
    			}
    		}
    	}
    }
    
    /**
     * Closes the folder.
     * @param folder
     */
    public static void close(final Folder folder) {
    	try {
    		if (folder != null && folder.isOpen()) {
    			folder.close(false);
    		}
    	} catch (final Exception e) {
    		System.err.println("I AM ERROR: The folder didn't close properly.");
    	}
    }
    
    /**
     * Closes the store.
     * @param store
     */
    public static void close (final Store store) {
    	try {
    		if (store != null && store.isConnected()) {
    			store.close();
    		}
		} catch (final Exception ex) {
			System.err.println("I AM ERROR: The store didn't close properly.");
		}
    
    }
    
    /**
     * Ensures that there is a connection to the folder that we want to listen to.
     * @param folder
     * @throws MessagingException
     */
    public static void ensureOpen(final Folder folder) throws MessagingException {
    	if (folder != null) {
    		Store store = folder.getStore(); // the store holds messages and the protocol to access them.
    		if (store != null && !store.isConnected()) {
    			store.connect(userName, password);
    		}
    	} else {
    		throw new MessagingException("Unable to open an null folder");
    	}
    	
    	if (folder.exists() && !folder.isOpen() && (folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
    		System.out.println("open folder " + folder.getFullName());
    		folder.open(Folder.READ_ONLY);
    		if (!folder.isOpen()) {
    			throw new MessagingException("Unable to open folder " + folder.getFullName());
    		}
    	}
    }
    
}