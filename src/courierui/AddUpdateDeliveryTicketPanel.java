package courierui;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.DeliveryTicketDBAO;
import courierdm.EmployeeDBAO;
import courierpd.core.Client;
import courierpd.core.Courier;
import courierpd.core.DefaultCourierAlgorithm;
import courierpd.core.DeliveryTicket;
import courierpd.core.OrderTaker;
import courierpd.core.User;
import courierpd.enums.EmployeeRole;
import courierpd.enums.TicketStatus;
import courierpd.map.PathAlgorithm;
import courierpd.map.Route;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddUpdateDeliveryTicketPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6213870661817657700L;
	private JTextField departureTimetextField;
	private JTextField returnTimetextField;
	private JTextField packageIdtextField;
	private JLabel orderDateLabel;
	private JLabel orderTimeLabel;

	/**
	 * Create the panel.
	 * @param b 
	 * @param deliveryTicket 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddUpdateDeliveryTicketPanel(CourierMainFrame currentFrame, DeliveryTicket deliveryTicket, boolean isAdd) {
		setLayout(null);
		
		JPanel CustomerInfopanel = new JPanel();
		CustomerInfopanel.setBounds(50, 50, 900, 130);
		CustomerInfopanel.setBackground(Color.LIGHT_GRAY);
		add(CustomerInfopanel);
		CustomerInfopanel.setLayout(null);
		
		JLabel lblPickupCustomer = new JLabel("Pick-up Customer: ");
		lblPickupCustomer.setBounds(75, 46, 120, 21);
		CustomerInfopanel.add(lblPickupCustomer);
		
		JLabel lblDeliveryCustomer = new JLabel("Delivery Customer");
		lblDeliveryCustomer.setBounds(75, 78, 120, 21);
		CustomerInfopanel.add(lblDeliveryCustomer);
		
		JComboBox PickupCustomercomboBox = new JComboBox();
		JComboBox deliveryCustomercomboBox = new JComboBox();

		for(Client client: ClientDBAO.listClients()){
			PickupCustomercomboBox.addItem(client);
			deliveryCustomercomboBox.addItem(client);
		}
		PickupCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		PickupCustomercomboBox.setBounds(205, 46, 150, 21);
		CustomerInfopanel.add(PickupCustomercomboBox);
		
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
		
		JTextArea specialDeliverytextArea = new JTextArea(deliveryTicket.getSpecialDeliveryInstructions());
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
		for(User user: EmployeeDBAO.listUsers()){
			if(user.getEmployeeRole().equals(EmployeeRole.Courier) && user.getIsActive()){
				courierNamecomboBox.addItem(user);
			}
		}
		courierNamecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		courierNamecomboBox.setBounds(208, 26, 150, 20);
		courierNamecomboBox.setSelectedItem(DefaultCourierAlgorithm.suggestDefaultCourier());
		DeliveryInfopanel.add(courierNamecomboBox);
		
		JLabel lblDepartureTime = new JLabel("Departure Time: ");
		lblDepartureTime.setBounds(78, 65, 120, 20);
		DeliveryInfopanel.add(lblDepartureTime);
		
		JLabel lblCourierReturnTime = new JLabel("Courier Return Time: ");
		lblCourierReturnTime.setBounds(78, 96, 120, 23);
		DeliveryInfopanel.add(lblCourierReturnTime);
		if(!isAdd && (deliveryTicket.getActualDepartureTime()!=null)){
			departureTimetextField = new JTextField(deliveryTicket.getActualDepartureTime().toString());
		}else{
			departureTimetextField = new JTextField();
		}
		
		departureTimetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!departureTimetextField.hasFocus()){
					Date today = new Date();
					deliveryTicket.setActualDepartureTime(today);
				}
			}
		});
		departureTimetextField.setBounds(209, 65, 149, 20);
		DeliveryInfopanel.add(departureTimetextField);
		departureTimetextField.setColumns(10);
		
		if(!isAdd&&(deliveryTicket.getCourierReturnTime()!=null)){
			returnTimetextField = new JTextField(deliveryTicket.getCourierReturnTime().toString());
		}else{
			returnTimetextField = new JTextField();
		}
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
		statusComboBox.addItem(TicketStatus.Opened);
		statusComboBox.addItem(TicketStatus.Closed);
		statusComboBox.addItem(TicketStatus.Canceled);
		statusComboBox.setBounds(720, 102, 130, 20);
		OrderInfopanel.add(statusComboBox);
		
		packageIdtextField = new JTextField(Integer.toString(deliveryTicket.getPackageID()));
		packageIdtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		packageIdtextField.setBounds(205, 62, 150, 20);
		OrderInfopanel.add(packageIdtextField);
		packageIdtextField.setColumns(10);
		if(!isAdd && (deliveryTicket.getOrderDate()!=null)){
			orderDateLabel = new JLabel(deliveryTicket.getOrderDate().toString());		
			}else{
				orderDateLabel = new JLabel(new Date().toString());	
		}
		orderDateLabel.setBounds(487, 87, 86, 20);
		OrderInfopanel.add(orderDateLabel);
		
		if(!isAdd&&(deliveryTicket.getOrderDate()!=null)){
			orderTimeLabel = new JLabel(deliveryTicket.getOrderPlacementTime().toString());	
			}else{
				orderTimeLabel = new JLabel(new Date().toString());
		}
		orderTimeLabel.setBounds(487, 62, 86, 20);
		OrderInfopanel.add(orderTimeLabel);
		
		JLabel lblToBeCalculated = new JLabel("TBE");// TBE = to be evaluated
		lblToBeCalculated.setBounds(205, 90, 150, 14);
		OrderInfopanel.add(lblToBeCalculated);
		
		JComboBox orderTakerBox = new JComboBox();

		for(User user: EmployeeDBAO.listUsers()){

			if(user.getEmployeeRole().equals(EmployeeRole.OrderTaker) && user.getIsActive()){
				orderTakerBox.addItem(user);
			}
		}
		PickupCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		orderTakerBox.setBounds(205, 38, 150, 20);
		OrderInfopanel.add(orderTakerBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings({ "static-access" })
			public void actionPerformed(ActionEvent e) {
				
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				userTransaction.begin();
				deliveryTicket.setActualDeliveryTime(null);
				//LocalTime departureTime = LocalTime.parse(departureTimetextField.getText());
				Date today = new Date();
				//today.setHours(departureTime.getHour());
				//today.setMinutes(departureTime.getMinute());
				deliveryTicket.setActualDepartureTime(today);
				deliveryTicket.setActualPickUpTime(null);
				deliveryTicket.setCourier((Courier)courierNamecomboBox.getSelectedItem());
				deliveryTicket.setCourierReturnTime(null);
				deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
				deliveryTicket.setEstBlocks(0);
				deliveryTicket.setEstDeliveryTime(null);
				deliveryTicket.setEstimatedDepartureTime(null);
				deliveryTicket.setEstPrice(0.0f);
				deliveryTicket.setIsBillPickUp(false);
				deliveryTicket.setIsBonusEarned(true);
				deliveryTicket.setOrderDate(null);
				deliveryTicket.setOrderTaker((OrderTaker)orderTakerBox.getSelectedItem());
				if(packageIdtextField.getText()==""){
					deliveryTicket.setPackageID(0);
				}else{
					deliveryTicket.setPackageID(Integer.parseInt(packageIdtextField.getText()));
				}
				deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
				deliveryTicket.setRequestedPickUpTime(null);
				deliveryTicket.setSpecialDeliveryInstructions(specialDeliverytextArea.getText());
				deliveryTicket.setStatus((TicketStatus)statusComboBox.getSelectedItem());
				if(isAdd){
					DeliveryTicketDBAO.addDeliveryTicket(deliveryTicket);
				}
				DeliveryTicketDBAO.saveDeliveryTicket(deliveryTicket);
				userTransaction.commit();
				
				PathAlgorithm pathAlgo = new PathAlgorithm();
				Route deliveryRoute = pathAlgo.findShortestPath(deliveryTicket);
				String translatedDirections = deliveryRoute.getTranslatedDirections();
				System.out.println("The following are the delivery directions");
				System.out.println("=========================================\n");
				System.out.print(translatedDirections);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryDirectionsPanel(currentFrame, translatedDirections, deliveryRoute));
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
