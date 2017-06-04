package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
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
	 */
	public DeleteTicketPanel(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JLabel lblDeleteDeliveryTiceket = new JLabel("Delete Delivery Ticket");
		lblDeleteDeliveryTiceket.setBounds(464, 38, 157, 14);
		add(lblDeleteDeliveryTiceket);
		
		JLabel lblPackageIdNumber = new JLabel("Package Id Number: ");
		lblPackageIdNumber.setBounds(385, 203, 116, 14);
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
		
		JLabel label = new JLabel("*");
		label.setBounds(587, 203, 46, 14);
		add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(587, 244, 46, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(587, 288, 46, 14);
		add(label_2);

	}

}
