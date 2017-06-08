package courierui;

import javax.swing.JPanel;

import courierdm.CourierEntityManager;
import courierdm.DeliveryTicketDBAO;
import courierpd.core.DeliveryTicket;

import javax.swing.JLabel;
import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteTicketPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5742023638915775509L;

	/**
	 * Create the panel.
	 * @param deliveryTicket 
	 */
	public DeleteTicketPanel(CourierMainFrame currentFrame, DeliveryTicket deliveryTicket) {
		setLayout(null);
		
		JLabel lblDeleteDeliveryTiceket = new JLabel("Delete Delivery Ticket");
		lblDeleteDeliveryTiceket.setBounds(464, 38, 157, 14);
		add(lblDeleteDeliveryTiceket);
		
		JLabel lblPackageIdNumber = new JLabel("Package Id Number: ");
		lblPackageIdNumber.setBounds(385, 203, 140, 14);
		add(lblPackageIdNumber);
		
		JLabel lblSenderClient = new JLabel("Sender Client: ");
		lblSenderClient.setBounds(385, 244, 101, 14);
		add(lblSenderClient);
		
		JLabel lblReceiverClient = new JLabel("Receiver Client:");
		lblReceiverClient.setBounds(385, 288, 101, 14);
		add(lblReceiverClient);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete the following ticket?");
		lblAreYouSure.setBounds(385, 163, 400, 14);
		add(lblAreYouSure);
		
		JButton btnYesDelete = new JButton("Yes, Delete");
		btnYesDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				userTransaction.begin();
				DeliveryTicketDBAO.deleteDeliveryTicket(deliveryTicket);
				userTransaction.commit();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnYesDelete.setBounds(385, 400, 100, 23);
		add(btnYesDelete);
		
		JButton btnNoCancel = new JButton("No, Cancel");
		btnNoCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnNoCancel.setBounds(587, 400, 100, 23);
		add(btnNoCancel);
		
		JLabel label = new JLabel(Integer.toString(deliveryTicket.getPackageID()));
		label.setBounds(587, 203, 62, 14);
		add(label);
		JLabel label_1;
		if(deliveryTicket.getPickUpClient()!=null){
			 label_1 = new JLabel(deliveryTicket.getPickUpClient().getName());
		}else
			 label_1 = new JLabel("*");
		
		label_1.setBounds(587, 244, 80, 14);
		add(label_1);
		
		JLabel label_2;
		if(deliveryTicket.getPickUpClient()!=null){
			 label_2 = new JLabel(deliveryTicket.getDeliveryClient().getName());
		}else
			 label_2 = new JLabel("*");
		label_2.setBounds(587, 288, 80, 14);
		add(label_2);

	}

}
