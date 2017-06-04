package courierui;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDeliveryTicketPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6213870661817657700L;
	private JTextField departureTimetextField;
	private JTextField returnTimetextField;
	private JTextField packageIdtextField;
	private JTextField orderDatetextField;
	private JTextField orderTimetextField;

	/**
	 * Create the panel.
	 */
	public UpdateDeliveryTicketPanel(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JPanel CustomerInfopanel = new JPanel();
		CustomerInfopanel.setBounds(50, 50, 900, 130);
		CustomerInfopanel.setBackground(Color.LIGHT_GRAY);
		add(CustomerInfopanel);
		CustomerInfopanel.setLayout(null);
		
		JLabel lblPickupCustomer = new JLabel("Pick-up customer: ");
		lblPickupCustomer.setBounds(75, 46, 120, 21);
		CustomerInfopanel.add(lblPickupCustomer);
		
		JLabel lblDeliveryCustomer = new JLabel("Delivery Customer");
		lblDeliveryCustomer.setBounds(75, 78, 120, 21);
		CustomerInfopanel.add(lblDeliveryCustomer);
		
		JComboBox PickupCustomercomboBox = new JComboBox();
		PickupCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		PickupCustomercomboBox.setBounds(205, 46, 150, 21);
		CustomerInfopanel.add(PickupCustomercomboBox);
		
		JComboBox deliveryCustomercomboBox = new JComboBox();
		deliveryCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deliveryCustomercomboBox.setBounds(205, 78, 150, 21);
		CustomerInfopanel.add(deliveryCustomercomboBox);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(420, 11, 128, 14);
		CustomerInfopanel.add(lblCustomerInfo);
		
		JLabel lblSpecialDelivery = new JLabel("Special Delivery");
		lblSpecialDelivery.setBounds(675, 26, 100, 14);
		CustomerInfopanel.add(lblSpecialDelivery);
		
		JTextArea specialDeliverytextArea = new JTextArea();
		specialDeliverytextArea.setBounds(600, 59, 250, 50);
		CustomerInfopanel.add(specialDeliverytextArea);
		
		JPanel DeliveryInfopanel = new JPanel();
		DeliveryInfopanel.setBounds(50, 205, 900, 130);
		DeliveryInfopanel.setBackground(Color.LIGHT_GRAY);
		add(DeliveryInfopanel);
		DeliveryInfopanel.setLayout(null);
		
		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setBounds(420, 11, 85, 14);
		DeliveryInfopanel.add(lblDeliveryInfo);
		
		JLabel lblCourier = new JLabel("Courier: ");
		lblCourier.setBounds(78, 29, 120, 14);
		DeliveryInfopanel.add(lblCourier);
		
		JComboBox courierNamecomboBox = new JComboBox();
		courierNamecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		courierNamecomboBox.setBounds(208, 26, 150, 20);
		DeliveryInfopanel.add(courierNamecomboBox);
		
		JLabel lblDepartureTime = new JLabel("Departure Time: ");
		lblDepartureTime.setBounds(78, 65, 120, 20);
		DeliveryInfopanel.add(lblDepartureTime);
		
		JLabel lblCourierReturnTime = new JLabel("Courier Return Time: ");
		lblCourierReturnTime.setBounds(78, 96, 120, 23);
		DeliveryInfopanel.add(lblCourierReturnTime);
		
		departureTimetextField = new JTextField();
		departureTimetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		departureTimetextField.setBounds(209, 65, 149, 20);
		DeliveryInfopanel.add(departureTimetextField);
		departureTimetextField.setColumns(10);
		
		returnTimetextField = new JTextField();
		returnTimetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		returnTimetextField.setBounds(209, 97, 149, 20);
		DeliveryInfopanel.add(returnTimetextField);
		returnTimetextField.setColumns(10);
		
		JLabel lblPickupTime = new JLabel("Pick-up Time: ");
		lblPickupTime.setBounds(420, 68, 81, 14);
		DeliveryInfopanel.add(lblPickupTime);
		
		JLabel lblDeliveryTime = new JLabel("Delivery TIme: ");
		lblDeliveryTime.setBounds(420, 100, 102, 14);
		DeliveryInfopanel.add(lblDeliveryTime);
		
		JLabel lblEstBlocks = new JLabel("Est. Blocks: ");
		lblEstBlocks.setBounds(650, 68, 150, 14);
		DeliveryInfopanel.add(lblEstBlocks);
		
		JLabel lblEstDeliveryTime = new JLabel("Est. Delivery Time: ");
		lblEstDeliveryTime.setBounds(650, 100, 150, 14);
		DeliveryInfopanel.add(lblEstDeliveryTime);
		
		JLabel lblTbd = new JLabel("TBE");
		lblTbd.setBounds(501, 68, 46, 14);
		DeliveryInfopanel.add(lblTbd);
		
		JLabel lblTbd_1 = new JLabel("TBE");
		lblTbd_1.setBounds(501, 100, 46, 14);
		DeliveryInfopanel.add(lblTbd_1);
		
		JLabel lblTbe = new JLabel("TBE");
		lblTbe.setBounds(800, 68, 46, 14);
		DeliveryInfopanel.add(lblTbe);
		
		JLabel lblTbe_1 = new JLabel("TBE");
		lblTbe_1.setBounds(800, 100, 46, 14);
		DeliveryInfopanel.add(lblTbe_1);
		
		JPanel OrderInfopanel = new JPanel();
		OrderInfopanel.setBounds(50, 360, 900, 130);
		OrderInfopanel.setBackground(Color.LIGHT_GRAY);
		add(OrderInfopanel);
		OrderInfopanel.setLayout(null);
		
		JLabel lblOrderInfo = new JLabel("Order Info");
		lblOrderInfo.setBounds(420, 11, 86, 14);
		OrderInfopanel.add(lblOrderInfo);
		
		JLabel lblOrderTaker = new JLabel("Order Taker: ");
		lblOrderTaker.setBounds(75, 38, 120, 14);
		OrderInfopanel.add(lblOrderTaker);
		
		JLabel lblPackageId = new JLabel("Package ID: ");
		lblPackageId.setBounds(75, 65, 120, 14);
		OrderInfopanel.add(lblPackageId);
		
		JLabel lblEstPrice = new JLabel("Est. Price:");
		lblEstPrice.setBounds(75, 90, 120, 14);
		OrderInfopanel.add(lblEstPrice);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(420, 65, 46, 14);
		OrderInfopanel.add(lblTime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(420, 90, 46, 14);
		OrderInfopanel.add(lblDate);
		
		JRadioButton rdbtnBillToPickup = new JRadioButton("Bill to pickup");
		rdbtnBillToPickup.setBounds(720, 7, 130, 23);
		OrderInfopanel.add(rdbtnBillToPickup);
		
		JRadioButton rdbtnBillToDelivery = new JRadioButton("Bill to Delivery");
		rdbtnBillToDelivery.setBounds(720, 34, 130, 23);
		OrderInfopanel.add(rdbtnBillToDelivery);
		
		JCheckBox chckbxBonus = new JCheckBox("Bonus");
		chckbxBonus.setBounds(720, 61, 130, 23);
		OrderInfopanel.add(chckbxBonus);
		
		JLabel lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(650, 105, 46, 14);
		OrderInfopanel.add(lblStatus);
		
		JComboBox statusComboBox = new JComboBox();
		statusComboBox.setBounds(720, 102, 130, 20);
		OrderInfopanel.add(statusComboBox);
		
		JComboBox orderComboBox = new JComboBox();
		orderComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		orderComboBox.setBounds(205, 35, 150, 20);
		OrderInfopanel.add(orderComboBox);
		
		packageIdtextField = new JTextField();
		packageIdtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		packageIdtextField.setBounds(205, 62, 150, 20);
		OrderInfopanel.add(packageIdtextField);
		packageIdtextField.setColumns(10);
		
		orderDatetextField = new JTextField();
		orderDatetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		orderDatetextField.setBounds(487, 87, 86, 20);
		OrderInfopanel.add(orderDatetextField);
		orderDatetextField.setColumns(10);
		
		orderTimetextField = new JTextField();
		orderTimetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		orderTimetextField.setBounds(487, 62, 86, 20);
		OrderInfopanel.add(orderTimetextField);
		orderTimetextField.setColumns(10);
		
		JLabel lblToBeCalculated = new JLabel("TBE");// TBE = to be evaluated
		lblToBeCalculated.setBounds(205, 90, 150, 14);
		OrderInfopanel.add(lblToBeCalculated);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(270, 510, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(660, 510, 89, 23);
		add(btnCancel);
		
		JLabel lbltbeToBe = new JLabel("*TBE: To Be Evaluated");
		lbltbeToBe.setBounds(442, 11, 166, 14);
		add(lbltbeToBe);

	}
}
