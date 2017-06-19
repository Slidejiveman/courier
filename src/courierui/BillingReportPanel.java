package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BillingReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BillingReportPanel(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JLabel lblClient = new JLabel("Client: ");
		lblClient.setBounds(69, 35, 46, 14);
		add(lblClient);
		
		JLabel lblInsertName = new JLabel("Insert Name");
		lblInsertName.setBounds(125, 35, 88, 14);
		add(lblInsertName);
		
		
		
		JList list = new JList();
		list.setBounds(39, 92, 528, 269);
		add(list);
		
		JButton btnSaveAsPdf = new JButton("Save as PDF");
		btnSaveAsPdf.setBounds(125, 372, 98, 23);
		add(btnSaveAsPdf);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new BillingReportOptionsPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(383, 372, 89, 23);
		add(btnCancel);
		
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(57, 67, 46, 14);
		add(lblDate);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setBounds(135, 67, 78, 14);
		add(lblPackageId);
		
		JLabel lblPickupTime = new JLabel("Pick-up Time");
		lblPickupTime.setBounds(235, 67, 69, 14);
		add(lblPickupTime);
		
		JLabel lblDeliveryTime = new JLabel("Delivery Time");
		lblDeliveryTime.setBounds(358, 67, 69, 14);
		add(lblDeliveryTime);
		
		JLabel lblBillingRate = new JLabel("Billing Rate");
		lblBillingRate.setBounds(468, 67, 63, 14);
		add(lblBillingRate);

	}
}
