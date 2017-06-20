package courierui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.persistence.EntityTransaction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import courierpd.other.DateParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddUpdateDeliveryTicketPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6213870661817657700L;
	private JTextField departureTimetextField;
	private JTextField returnTimetextField;
	private JLabel packageIdLabel;
	private JLabel orderDateLabel;
	private JLabel orderTimeLabel;
	private JTextField PickUpTimetextField;
	private JTextField DeliveryTimeTextField;
	private JTextField requestedPickupTimetextField;
	private JPanel CustomerInfopanel;
	private JPanel DeliveryInfopanel;
	private JPanel OrderInfopanel;
	@SuppressWarnings("rawtypes")
	private JComboBox PickupCustomercomboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox deliveryCustomercomboBox;
	private JTextArea specialDeliverytextArea;
	@SuppressWarnings("rawtypes")
	private JComboBox courierNamecomboBox;
	private JLabel estBlocksLabel;
	private JLabel estDeliveryTimeLabel;
	private JLabel estDepartureTimeLabel;
	private JCheckBox chckbxBonus;
	@SuppressWarnings("rawtypes")
	private JComboBox statusComboBox;
	private JLabel estPriceLabel;
	/**
	 * Create the panel.
	 * @param b 
	 * @param deliveryTicket 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddUpdateDeliveryTicketPanel(CourierMainFrame currentFrame, DeliveryTicket deliveryTicket,User activeUser, boolean isAdd) {
		setLayout(null);
		
		CustomerInfopanel = new JPanel();
		CustomerInfopanel.setBounds(50, 50, 900, 130);
		CustomerInfopanel.setBackground(Color.LIGHT_GRAY);
		add(CustomerInfopanel);
		CustomerInfopanel.setLayout(null);
		
		DeliveryInfopanel = new JPanel();
		DeliveryInfopanel.setBounds(50, 205, 900, 130);
		DeliveryInfopanel.setBackground(Color.LIGHT_GRAY);
		add(DeliveryInfopanel);
		DeliveryInfopanel.setLayout(null);
		
		OrderInfopanel = new JPanel();
		OrderInfopanel.setBounds(50, 360, 900, 130);
		OrderInfopanel.setBackground(Color.LIGHT_GRAY);
		add(OrderInfopanel);
		OrderInfopanel.setLayout(null);
		
		PickupCustomercomboBox = new JComboBox();
		deliveryCustomercomboBox = new JComboBox();
		courierNamecomboBox = new JComboBox();
		
		for(Client client: ClientDBAO.listClients()){
			if (client.getIsActive()) {
				PickupCustomercomboBox.addItem(client);
				deliveryCustomercomboBox.addItem(client);
			}
		}
		for(User courier: EmployeeDBAO.listUsers()){
			if(courier.getEmployeeRole().equals(EmployeeRole.Courier) && courier.getIsActive()){
				courierNamecomboBox.addItem(courier);
			}
		}
		if(isAdd){
			specialDeliverytextArea = new JTextArea();
			courierNamecomboBox.setSelectedItem(DefaultCourierAlgorithm.suggestDefaultCourier());
			departureTimetextField = new JTextField();
			returnTimetextField = new JTextField();
			PickUpTimetextField = new JTextField();
			DeliveryTimeTextField = new JTextField();
			estBlocksLabel = new JLabel("TBE");
			estDeliveryTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));
			estDepartureTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));
			requestedPickupTimetextField = new JTextField();
			orderDateLabel = new JLabel(DateParser.printDate(new Date()));	
			orderTimeLabel = new JLabel(DateParser.printTime(new Date()));
			estPriceLabel = new JLabel("TBE");
		
		}else{
			PickupCustomercomboBox.setSelectedItem(deliveryTicket.getPickUpClient());
			deliveryCustomercomboBox.setSelectedItem(deliveryTicket.getDeliveryClient());
			specialDeliverytextArea = new JTextArea(deliveryTicket.getSpecialDeliveryInstructions());
			courierNamecomboBox.setSelectedItem(deliveryTicket.getCourier());
			if (deliveryTicket.getActualDepartureTime()!=null)
				departureTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getActualDepartureTime()));
			if(deliveryTicket.getCourierReturnTime()!=null)
				returnTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getCourierReturnTime()));
			PickUpTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getActualPickUpTime()));
			DeliveryTimeTextField = new JTextField(DateParser.printTime(deliveryTicket.getActualDeliveryTime()));
			estBlocksLabel = new JLabel(Integer.toString(deliveryTicket.getEstBlocks()));
			estDeliveryTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getEstDeliveryTime()));
			estDepartureTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getEstimatedDepartureTime()));
			requestedPickupTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getRequestedPickUpTime()));
			if(deliveryTicket.getOrderDate()!=null){
				orderDateLabel = new JLabel(DateParser.printDate(deliveryTicket.getOrderDate()));		
				orderTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getOrderPlacementTime()));	

			}
			estPriceLabel= new JLabel(Float.toString(deliveryTicket.getEstPrice()));
		}

		JRadioButton rdbtnBillToPickup = new JRadioButton("Bill to pickup");
		rdbtnBillToPickup.setBounds(720, 7, 170, 23);
		OrderInfopanel.add(rdbtnBillToPickup);
		
		JRadioButton rdbtnBillToDelivery = new JRadioButton("Bill to Delivery");
		rdbtnBillToDelivery.setBounds(720, 34, 170, 23);
		OrderInfopanel.add(rdbtnBillToDelivery);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnBillToPickup);
	    buttonGroup.add(rdbtnBillToDelivery);	
	    if (!isAdd && deliveryTicket.getIsBillPickUp()) {
	    	buttonGroup.setSelected(rdbtnBillToPickup.getModel(), true);
	    } else if (!isAdd && !deliveryTicket.getIsBillPickUp()) {
	    	buttonGroup.setSelected(rdbtnBillToDelivery.getModel(), true);
	    } else { // case where we are adding
	    	buttonGroup.setSelected(rdbtnBillToPickup.getModel(), true);
	    }
	    		
		chckbxBonus = new JCheckBox("Bonus");
		chckbxBonus.setBounds(720, 61, 170, 23);
		OrderInfopanel.add(chckbxBonus);
		
		JLabel lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(650, 105, 46, 14);
		OrderInfopanel.add(lblStatus);
		
		statusComboBox = new JComboBox();
		statusComboBox.addItem(TicketStatus.Opened);
		statusComboBox.addItem(TicketStatus.Closed);
		statusComboBox.addItem(TicketStatus.Canceled);
		statusComboBox.setBounds(720, 102, 170, 20);
		OrderInfopanel.add(statusComboBox);
		
		packageIdLabel= new JLabel(Integer.toString(deliveryTicket.getPackageID()));
		packageIdLabel.setBounds(205, 62, 150, 20);
		OrderInfopanel.add(packageIdLabel);
		
		
		JComboBox orderTakerBox = new JComboBox();
		orderTakerBox.addItem(activeUser);
		orderTakerBox.setSelectedItem(activeUser);
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
				//try- catch starts here
				try {
					userTransaction.begin();
					if(!DeliveryTimeTextField.getText().equals("")){
						deliveryTicket.setActualDeliveryTime(parseStringTime(DeliveryTimeTextField.getText()));
					}
					if(!departureTimetextField.getText().equals("")){
						deliveryTicket.setActualDepartureTime(parseStringTime(departureTimetextField.getText()));
					}
					if(!PickUpTimetextField.getText().equals("")){
						deliveryTicket.setActualPickUpTime(parseStringTime(PickUpTimetextField.getText()));
					}
					deliveryTicket.setCourier((Courier)courierNamecomboBox.getSelectedItem());		
					if(!returnTimetextField.getText().equals("")){
						deliveryTicket.setCourierReturnTime(parseStringTime(returnTimetextField.getText()));
					}
					if(!requestedPickupTimetextField.getText().equals("")){
						deliveryTicket.setRequestedPickUpTime(parseStringTime(requestedPickupTimetextField.getText()));
					}else{
						System.out.println("No pick up time specified.");
					}
					deliveryTicket.setCourier((Courier)courierNamecomboBox.getSelectedItem());
					deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
					
					if(buttonGroup.isSelected(rdbtnBillToPickup.getModel())) {
						deliveryTicket.setIsBillPickUp(true);
					} else {
					}
					
					deliveryTicket.setOrderDate(parseStringDatabaseDateToDate(orderDateLabel.getText()));
					deliveryTicket.setOrderTaker((OrderTaker)orderTakerBox.getSelectedItem());
					if(packageIdLabel.getText()==""){
						deliveryTicket.setPackageID(0);
					}else{
						deliveryTicket.setPackageID(Integer.parseInt(packageIdLabel.getText()));
					}
					deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
					deliveryTicket.setSpecialDeliveryInstructions(specialDeliverytextArea.getText());
					deliveryTicket.setStatus((TicketStatus)statusComboBox.getSelectedItem());
					if(isAdd){
						DeliveryTicketDBAO.addDeliveryTicket(deliveryTicket);
					}
					DeliveryTicketDBAO.saveDeliveryTicket(deliveryTicket);
					userTransaction.commit();
					
					//Path Algorithm section
					PathAlgorithm pathAlgo = new PathAlgorithm();
					Route deliveryRoute = pathAlgo.findShortestPath(deliveryTicket);
					String translatedDirections = deliveryRoute.getTranslatedDirections();
					System.out.println("The following are the delivery directions");
					System.out.println("=========================================\n");
					System.out.print(translatedDirections);
					
					EntityTransaction estimatesTransaction = CourierEntityManager.getEntityManager().getTransaction();
					estimatesTransaction.begin();
					deliveryTicket.setEstBlocks(0);
					deliveryTicket.setEstDeliveryTime(parseStringDatabaseDateToDate(estDeliveryTimeLabel.getText()));
					deliveryTicket.setEstimatedDepartureTime(parseStringDatabaseDateToDate(estDepartureTimeLabel.getText()));
					deliveryTicket.setEstPrice(0.0f);
					deliveryTicket.setIsBonusEarned(true);
					estimatesTransaction.commit();
						
					//Frame revalidation section
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new DeliveryDirectionsPanel(currentFrame, translatedDirections, deliveryRoute, activeUser));
					currentFrame.revalidate();
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"The request pickup time is empty.", 
							"Save Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSave.setBounds(270, 510, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id", activeUser));
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(660, 510, 89, 23);
		add(btnCancel);
		
		
		PickupCustomercomboBox.setBounds(205, 46, 150, 21);
		CustomerInfopanel.add(PickupCustomercomboBox);
		deliveryCustomercomboBox.setBounds(205, 78, 150, 21);
		CustomerInfopanel.add(deliveryCustomercomboBox);
		specialDeliverytextArea.setBounds(600, 59, 290, 50);
		CustomerInfopanel.add(specialDeliverytextArea);
		courierNamecomboBox.setBounds(208, 26, 150, 20);
		DeliveryInfopanel.add(courierNamecomboBox);
		departureTimetextField.setBounds(209, 65, 149, 20);
		DeliveryInfopanel.add(departureTimetextField);
		departureTimetextField.setColumns(10);
		returnTimetextField.setBounds(209, 97, 149, 20);
		DeliveryInfopanel.add(returnTimetextField);
		returnTimetextField.setColumns(10);
		
		PickUpTimetextField.setBounds(511, 65, 101, 20);
		DeliveryInfopanel.add(PickUpTimetextField);
		PickUpTimetextField.setColumns(10);
		
		DeliveryTimeTextField.setBounds(511, 97, 101, 20);
		DeliveryInfopanel.add(DeliveryTimeTextField);
		DeliveryTimeTextField.setColumns(10);
		
		estBlocksLabel.setBounds(790, 68, 100, 14);
		DeliveryInfopanel.add(estBlocksLabel);
		
		estDeliveryTimeLabel.setBounds(790, 100, 100, 14);
		DeliveryInfopanel.add(estDeliveryTimeLabel);
		
		estDepartureTimeLabel.setBounds(790, 29, 100, 14);
		DeliveryInfopanel.add(estDepartureTimeLabel);
			
		requestedPickupTimetextField.setBounds(542, 40, 70, 20);
		DeliveryInfopanel.add(requestedPickupTimetextField);
		requestedPickupTimetextField.setColumns(10);
		
		orderDateLabel.setBounds(487, 87, 120, 20);
		OrderInfopanel.add(orderDateLabel);

		orderTimeLabel.setBounds(487, 62, 120, 20);
		OrderInfopanel.add(orderTimeLabel);

		JLabel lblPickupCustomer = new JLabel("Pick-up Customer: ");
		lblPickupCustomer.setBounds(75, 46, 120, 21);
		CustomerInfopanel.add(lblPickupCustomer);
		
		JLabel lblDeliveryCustomer = new JLabel("Delivery Customer");
		lblDeliveryCustomer.setBounds(75, 78, 120, 21);
		CustomerInfopanel.add(lblDeliveryCustomer);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(420, 11, 128, 14);
		CustomerInfopanel.add(lblCustomerInfo);
		
		JLabel lblSpecialDelivery = new JLabel("Special Delivery");
		lblSpecialDelivery.setBounds(675, 26, 100, 14);
		CustomerInfopanel.add(lblSpecialDelivery);
		
		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setBounds(420, 11, 85, 14);
		DeliveryInfopanel.add(lblDeliveryInfo);
		
		JLabel lblCourier = new JLabel("Courier: ");
		lblCourier.setBounds(78, 29, 120, 14);
		DeliveryInfopanel.add(lblCourier);
		
		JLabel lblDepartureTime = new JLabel("Departure Time: ");
		lblDepartureTime.setBounds(78, 65, 120, 20);
		DeliveryInfopanel.add(lblDepartureTime);
		
		JLabel lblCourierReturnTime = new JLabel("Courier Return Time: ");
		lblCourierReturnTime.setBounds(78, 96, 120, 23);
		DeliveryInfopanel.add(lblCourierReturnTime);
		
		JLabel lblPickupTime = new JLabel("Pick-up Time: ");
		lblPickupTime.setBounds(385, 68, 81, 14);
		DeliveryInfopanel.add(lblPickupTime);
		
		JLabel lblDeliveryTime = new JLabel("Delivery TIme: ");
		lblDeliveryTime.setBounds(385, 100, 102, 14);
		DeliveryInfopanel.add(lblDeliveryTime);
		
		JLabel lblEstBlocks = new JLabel("Est. Blocks: ");
		lblEstBlocks.setBounds(650, 68, 85, 14);
		DeliveryInfopanel.add(lblEstBlocks);
		
		JLabel lblEstDeliveryTime = new JLabel("Est. Delivery Time: ");
		lblEstDeliveryTime.setBounds(650, 100, 130, 14);
		DeliveryInfopanel.add(lblEstDeliveryTime);
		
		JLabel lblEstDepartureTime = new JLabel("Est. Departure Time:");
		lblEstDepartureTime.setBounds(651, 29, 127, 14);
		DeliveryInfopanel.add(lblEstDepartureTime);
		
		JLabel lblRequestedPickupTime = new JLabel("Requested Pickup Time:");
		lblRequestedPickupTime.setBounds(385, 43, 150, 14);
		DeliveryInfopanel.add(lblRequestedPickupTime);
		
		estPriceLabel.setBounds(205, 90, 150, 14);
		OrderInfopanel.add(estPriceLabel);
		
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
		lblTime.setBounds(391, 65, 46, 14);
		OrderInfopanel.add(lblTime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(391, 90, 46, 14);
		OrderInfopanel.add(lblDate);
		
		
		JLabel lbltbeToBe = new JLabel("*TBE: To Be Evaluated");
		lbltbeToBe.setBounds(442, 11, 166, 14);
		add(lbltbeToBe);

	}
	@SuppressWarnings("deprecation")
	public Date parseStringTime (String timeString){
	
		Date date = new Date();
		Date tempDate = null;
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("h:mm a");
		try {
			tempDate = dateFormatter.parse(timeString);
			date.setHours(tempDate.getHours());
			date.setMinutes(tempDate.getMinutes());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return date;
	}
	
	public Date parseStringDatabaseDateToDate (String databaseDate){
		Date date = null;
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("EEE MMM dd h:mm:ss z yyyy");
		try {
			date = dateFormatter.parse(databaseDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return date;
	}
}
