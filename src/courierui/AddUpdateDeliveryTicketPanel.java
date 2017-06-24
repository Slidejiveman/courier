package courierui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.DeliveryTicketDBAO;
import courierdm.EmployeeDBAO;
import courieremail.EmailUtil;
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

public class AddUpdateDeliveryTicketPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6213870661817657700L;
	private JTextField departureTimetextField;
	private JTextField courierRetTimetextField;
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
	private JButton btnSave;
	private String errorPaneMessages = "";
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
			deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
			deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
			
			departureTimetextField = new JTextField();
			courierRetTimetextField = new JTextField();
			PickUpTimetextField = new JTextField();
			DeliveryTimeTextField = new JTextField();
			estBlocksLabel = new JLabel("TBE");
			estDeliveryTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));
			estDepartureTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));
			requestedPickupTimetextField = new JTextField();
			orderDateLabel = new JLabel(new Date().toString());	
			orderTimeLabel = new JLabel(DateParser.printTime(new Date()));
			estPriceLabel = new JLabel("TBE");
			chckbxBonus = new JCheckBox("Bonus");
			chckbxBonus.setEnabled(false);
		
		}else{
			CourierEntityManager.getEntityManager().refresh(deliveryTicket);
			PickupCustomercomboBox.setSelectedItem(deliveryTicket.getPickUpClient());
			deliveryCustomercomboBox.setSelectedItem(deliveryTicket.getDeliveryClient());
			PickupCustomercomboBox.setEnabled(false);
			deliveryCustomercomboBox.setEnabled(false);
			deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
			deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
			specialDeliverytextArea = new JTextArea(deliveryTicket.getSpecialDeliveryInstructions());
			courierNamecomboBox.setSelectedItem(deliveryTicket.getCourier());
			if (deliveryTicket.getActualDepartureTime()!=null)
				departureTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getActualDepartureTime()));
			else{
				departureTimetextField = new JTextField();
			}
			if(deliveryTicket.getCourierReturnTime()!=null)
				courierRetTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getCourierReturnTime()));
			else{
				courierRetTimetextField = new JTextField();
			}
			if(deliveryTicket.getActualPickUpTime()!=null)
				PickUpTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getActualPickUpTime()));
			else{
				PickUpTimetextField = new JTextField();
			}
			if(deliveryTicket.getActualDeliveryTime()!=null)
				DeliveryTimeTextField = new JTextField(DateParser.printTime(deliveryTicket.getActualDeliveryTime()));
			else
				DeliveryTimeTextField = new JTextField();
			estBlocksLabel = new JLabel(Integer.toString(deliveryTicket.getEstBlocks()));
			if(deliveryTicket.getEstDeliveryTime()!=null)
				estDeliveryTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getEstDeliveryTime()));
			else
				estDeliveryTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));
			if(deliveryTicket.getEstimatedDepartureTime()!=null)
				estDepartureTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getEstimatedDepartureTime()));
			else
				estDepartureTimeLabel = new JLabel(DateParser.printTime(parseStringDatabaseDateToDate("Sat Jan 01 00:01:00 CDT 2000")));

			requestedPickupTimetextField = new JTextField(DateParser.printTime(deliveryTicket.getRequestedPickUpTime()));
			if(deliveryTicket.getOrderDate()!=null){
				orderDateLabel = new JLabel(((Date)(deliveryTicket.getOrderDate())).toString());		
				orderTimeLabel = new JLabel(DateParser.printTime(deliveryTicket.getOrderPlacementTime()));	
			}
			estPriceLabel= new JLabel(Float.toString(deliveryTicket.getEstPrice()));
			chckbxBonus = new JCheckBox("Bonus");
			chckbxBonus.setSelected(deliveryTicket.getIsBonusEarned());
			chckbxBonus.setEnabled(false);
		}
		departureTimetextField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				//deliveryTicket.getCourier().setIsOutForDelivery(true);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				deliveryTicket.getCourier().setIsOutForDelivery(true);
				System.out.println("Courier delivery status: "+deliveryTicket.getCourier().getIsOutForDelivery());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				deliveryTicket.getCourier().setIsOutForDelivery(false);
				System.out.println("Courier delivery status: "+deliveryTicket.getCourier().getIsOutForDelivery());
			}
			
		});
		
		DeliveryTimeTextField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				//send client e-mails
				//this also happens if updated from the courier's incoming emails
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				//send client e-mails
				EmailUtil.sendConfirmationMail(deliveryTicket.getDeliveryClient().getEmail(), 
						deliveryTicket.getPickUpClient().getEmail(), // string email will be made into Addresses
						"ubiquitymail@gmail.com", "smtp.gmail.com",
			    		 deliveryTicket);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				//send client e-mails
			}
			
		});
		
		courierRetTimetextField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				//deliveryTicket.setStatus(TicketStatus.Closed);
				//deliveryTicket.getCourier().setIsOutForDelivery(false);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				deliveryTicket.setStatus(TicketStatus.Closed);
				statusComboBox.setSelectedItem(TicketStatus.Closed);
				deliveryTicket.getCourier().setIsOutForDelivery(false);
				System.out.println("Courier delivery status: "+deliveryTicket.getCourier().getIsOutForDelivery());

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				deliveryTicket.setStatus(TicketStatus.Opened);
				statusComboBox.setSelectedItem(TicketStatus.Opened);
				deliveryTicket.getCourier().setIsOutForDelivery(true);
				System.out.println("Courier delivery status: "+deliveryTicket.getCourier().getIsOutForDelivery());

			}
			
		});
		PickupCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				departureTimetextField.setText("");
				courierRetTimetextField.setText("");
				PickUpTimetextField.setText("");
				DeliveryTimeTextField.setText("");
				deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
				EntityTransaction resetTimesTransaction = CourierEntityManager.getEntityManager().getTransaction();
				resetTimesTransaction.begin();
				deliveryTicket.setActualDepartureTime(null);
				deliveryTicket.setActualPickUpTime(null);
				deliveryTicket.setActualDeliveryTime(null);
				deliveryTicket.setCourierReturnTime(null);
				resetTimesTransaction.commit();
				
			}
		});
		deliveryCustomercomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				departureTimetextField.setText("");
				courierRetTimetextField.setText("");
				PickUpTimetextField.setText("");
				DeliveryTimeTextField.setText("");
				deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
				
				EntityTransaction resetTimesTransaction = CourierEntityManager.getEntityManager().getTransaction();
				resetTimesTransaction.begin();
				deliveryTicket.setActualDepartureTime(null);
				deliveryTicket.setActualPickUpTime(null);
				deliveryTicket.setActualDeliveryTime(null);
				deliveryTicket.setCourierReturnTime(null);
				resetTimesTransaction.commit();
			}
		});
		
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
	    		
		
		chckbxBonus.setBounds(720, 61, 170, 23);
		OrderInfopanel.add(chckbxBonus);
		
		JLabel lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(650, 105, 46, 14);
		OrderInfopanel.add(lblStatus);
		
		statusComboBox = new JComboBox();
		statusComboBox.addItem(TicketStatus.Opened);
		statusComboBox.addItem(TicketStatus.Closed);
		statusComboBox.addItem(TicketStatus.Canceled);
		if(isAdd){
			statusComboBox.setSelectedItem(TicketStatus.Opened);
			statusComboBox.setEnabled(false);
		}else{
			statusComboBox.setSelectedItem(deliveryTicket.getStatus());
		}
		statusComboBox.setBounds(720, 102, 170, 20);
		OrderInfopanel.add(statusComboBox);
		
		packageIdLabel= new JLabel(Integer.toString(deliveryTicket.getPackageID()));
		packageIdLabel.setBounds(205, 62, 150, 20);
		OrderInfopanel.add(packageIdLabel);
		
		
		JLabel orderTaker = new JLabel();
		orderTaker.setText(activeUser.getName());
		orderTaker.setBounds(205, 38, 150, 20);
		OrderInfopanel.add(orderTaker);
		JButton btnUpdateEstimates = new JButton("Save Ticket");
		if(isAdd){
			btnUpdateEstimates.setVisible(false);
		}
		btnUpdateEstimates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				try{
					userTransaction.begin();
					deliveryTicket.setCourier((Courier)courierNamecomboBox.getSelectedItem());
					deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
					deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
					
					if(buttonGroup.isSelected(rdbtnBillToPickup.getModel())) {
						deliveryTicket.setIsBillPickUp(true);
					} else {
						deliveryTicket.setIsBillPickUp(false);
					}
					
					deliveryTicket.setOrderTaker((OrderTaker)activeUser);
					if(packageIdLabel.getText()==""){
						deliveryTicket.setPackageID(0);
					}else{
						deliveryTicket.setPackageID(Integer.parseInt(packageIdLabel.getText()));
					}
					
					deliveryTicket.setSpecialDeliveryInstructions(specialDeliverytextArea.getText());
					deliveryTicket.setStatus((TicketStatus)statusComboBox.getSelectedItem());
					if(!requestedPickupTimetextField.getText().equals("")){
						deliveryTicket.setRequestedPickUpTime(new Timestamp(parseStringTime(requestedPickupTimetextField.getText()).getTime()));
					}else{
						errorPaneMessages+= "\n";
						errorPaneMessages+= "No pick up time specified";
						System.out.println("No pick up time specified.");
					}
					if(isAdd){
						deliveryTicket.setOrderDate(new Timestamp(new Date().getTime()));
						DeliveryTicketDBAO.addDeliveryTicket(deliveryTicket);
					}
					DeliveryTicketDBAO.saveDeliveryTicket(deliveryTicket);
					userTransaction.commit();
				}catch(Exception e){
					userTransaction.rollback();
					JOptionPane.showMessageDialog(currentFrame, errorPaneMessages,"INPUT ERRORS",JOptionPane.ERROR_MESSAGE);
					
				}
				
				new PathAlgorithm();
				Route deliveryRoute = PathAlgorithm.findShortestPath(deliveryTicket);
				deliveryRoute.getTranslatedDirections();
				
				EntityTransaction estimatesTransaction = CourierEntityManager.getEntityManager().getTransaction();
				CourierEntityManager.getEntityManager().refresh(deliveryTicket);
				try{
					estimatesTransaction.begin();
					deliveryTicket.setEstBlocks(deliveryRoute.estimateBlocks());
					deliveryTicket.setEstimatedDepartureTime(new Timestamp(parseStringTime(DateParser.printTime(deliveryRoute.estimateDepartureTime())).getTime()));
					if(!departureTimetextField.getText().equals("")){
						deliveryTicket.setActualDepartureTime(new Timestamp(parseStringTime(departureTimetextField.getText()).getTime()));
					}
					if(!PickUpTimetextField.getText().equals("")){
						deliveryTicket.setActualPickUpTime(new Timestamp(parseStringTime(PickUpTimetextField.getText()).getTime()));
					}
					if(!DeliveryTimeTextField.getText().equals("")){
						deliveryTicket.setActualDeliveryTime(new Timestamp(parseStringTime(DeliveryTimeTextField.getText()).getTime()));
					}
					if(!courierRetTimetextField.getText().equals("")){
						deliveryTicket.setCourierReturnTime(new Timestamp(parseStringTime(courierRetTimetextField.getText()).getTime()));
					}
					
					deliveryTicket.setEstDeliveryTime(new Timestamp(parseStringTime(DateParser.printTime(deliveryRoute.estimateDeliveryTime())).getTime()));
					deliveryTicket.setEstPrice(deliveryRoute.estimatePrice());
					if(!DeliveryTimeTextField.getText().equals("")){
						System.out.println("Is bonus earned "+deliveryRoute.deliveryTimesMet());
						deliveryTicket.setIsBonusEarned(deliveryRoute.deliveryTimesMet());
					}
					
					estimatesTransaction.commit();
				}catch (Exception exception){
					estimatesTransaction.rollback();
					JOptionPane.showMessageDialog(currentFrame, errorPaneMessages,"INPUT ERRORS",JOptionPane.ERROR_MESSAGE);
				}
				//Refresh the page
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Order Date", activeUser));
				currentFrame.revalidate();
			}
		});
		btnUpdateEstimates.setBounds(191, 501, 150, 23);
		add(btnUpdateEstimates);
		
		btnSave = new JButton("Save & Get Directions");
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings({ })
			public void actionPerformed(ActionEvent e) {

					EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
					try{
						userTransaction.begin();
						deliveryTicket.setCourier((Courier)courierNamecomboBox.getSelectedItem());
						deliveryTicket.setPickUpClient((Client)PickupCustomercomboBox.getSelectedItem());
						deliveryTicket.setDeliveryClient((Client)deliveryCustomercomboBox.getSelectedItem());
						
						if(buttonGroup.isSelected(rdbtnBillToPickup.getModel())) {
							deliveryTicket.setIsBillPickUp(true);
						} else {
							deliveryTicket.setIsBillPickUp(false);
						}
						
						deliveryTicket.setOrderTaker((OrderTaker)activeUser);
						if(packageIdLabel.getText()==""){
							deliveryTicket.setPackageID(0);
						}else{
							deliveryTicket.setPackageID(Integer.parseInt(packageIdLabel.getText()));
						}
						
						deliveryTicket.setSpecialDeliveryInstructions(specialDeliverytextArea.getText());
						deliveryTicket.setStatus((TicketStatus)statusComboBox.getSelectedItem());
						if(requestedPickupTimetextField.getText()!=("")){
							deliveryTicket.setRequestedPickUpTime(new Timestamp(parseStringTime(requestedPickupTimetextField.getText()).getTime()));
						}else{
							errorPaneMessages+= "\n";
							errorPaneMessages+= "No pick up time specified";
							System.out.println("No pick up time specified.");
						}
						if(isAdd){
							deliveryTicket.setOrderDate(new Timestamp(new Date().getTime()));
							DeliveryTicketDBAO.addDeliveryTicket(deliveryTicket);
						}
						DeliveryTicketDBAO.saveDeliveryTicket(deliveryTicket);
						userTransaction.commit();
					}catch(Exception saveException1){
						userTransaction.rollback();
						JOptionPane.showMessageDialog(currentFrame, errorPaneMessages,"INPUT ERRORS",JOptionPane.ERROR_MESSAGE);
					}
					
					new PathAlgorithm();
					Route deliveryRoute = PathAlgorithm.findShortestPath(deliveryTicket);
					String translatedDirections = deliveryRoute.getTranslatedDirections();
					
					EntityTransaction estimatesTransaction = CourierEntityManager.getEntityManager().getTransaction();
					
					try{
						estimatesTransaction.begin();
						deliveryTicket.setEstBlocks(deliveryRoute.estimateBlocks());
						deliveryTicket.setEstimatedDepartureTime(new Timestamp(parseStringTime(DateParser.printTime(deliveryRoute.estimateDepartureTime())).getTime()));
						deliveryTicket.setEstDeliveryTime(new Timestamp(parseStringTime(DateParser.printTime(deliveryRoute.estimateDeliveryTime())).getTime()));
						deliveryTicket.setEstPrice(deliveryRoute.estimatePrice());
						if(!DeliveryTimeTextField.getText().equals(""))
							deliveryTicket.setIsBonusEarned(deliveryRoute.deliveryTimesMet());
						
						
						System.out.println("Error date: "+departureTimetextField.getText());
						if(!departureTimetextField.getText().equals("")){
							deliveryTicket.setActualDepartureTime(new Timestamp(parseStringTime(departureTimetextField.getText()).getTime()));
							System.out.println(deliveryTicket.getActualDepartureTime());
						}
						if(!PickUpTimetextField.getText().equals("")){
							deliveryTicket.setActualPickUpTime(new Timestamp(parseStringTime(PickUpTimetextField.getText()).getTime()));
						}
						if(!DeliveryTimeTextField.getText().equals("")){
							deliveryTicket.setActualDeliveryTime(new Timestamp(parseStringTime(DeliveryTimeTextField.getText()).getTime()));
						}
						if(!courierRetTimetextField.getText().equals("")){
							deliveryTicket.setCourierReturnTime(new Timestamp(parseStringTime(courierRetTimetextField.getText()).getTime()));
						}
						estimatesTransaction.commit();
					}catch(Exception saveException2){
						estimatesTransaction.rollback();
						JOptionPane.showMessageDialog(currentFrame, errorPaneMessages,"INPUT ERRORS",JOptionPane.ERROR_MESSAGE);
					}
					if(!deliveryTicket.getCourier().getAssignedTickets().contains(deliveryTicket)){
						deliveryTicket.getCourier().getAssignedTickets().add(deliveryTicket);
						deliveryTicket.getCourier().setDeliveriesToday(
								deliveryTicket.getCourier().getDeliveriesToday()+1);
					}
					
					System.out.println("Courier's deliveries: "+deliveryTicket.getCourier().getDeliveriesToday());
					deliveryRoute.setCurrentOrder(deliveryTicket);
					//Frame revalidation section
					CourierEntityManager.getEntityManager().refresh(deliveryTicket);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new DeliveryDirectionsPanel(currentFrame,translatedDirections, deliveryRoute,activeUser));
					currentFrame.revalidate();
			}
		});
		if(isAdd){
			btnSave.setBounds(191, 501, 166, 23);
		}else
			btnSave.setBounds(458, 501, 166, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id", activeUser));
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(749, 501, 89, 23);
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
		courierRetTimetextField.setBounds(209, 97, 149, 20);
		DeliveryInfopanel.add(courierRetTimetextField);
		courierRetTimetextField.setColumns(10);
		
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
		
		orderDateLabel.setBounds(420, 87, 200, 20);
		OrderInfopanel.add(orderDateLabel);

		orderTimeLabel.setBounds(420, 62, 120, 20);
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
		lblTime.setBounds(376, 65, 46, 14);
		OrderInfopanel.add(lblTime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(376, 90, 46, 14);
		OrderInfopanel.add(lblDate);
		
		
		JLabel lbltbeToBe = new JLabel("*TBE: To Be Evaluated");
		lbltbeToBe.setBounds(442, 11, 166, 14);
		add(lbltbeToBe);

	}
	@SuppressWarnings("deprecation")
	public Date parseStringTime (String timeString){
		System.out.println("Input time: "+timeString);
		Date timeDate = parseStringDatabaseDateToDate(orderDateLabel.getText());
		Date tempDate = null;
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("h:mm a");
		try {
			tempDate = dateFormatter.parse(timeString);
			timeDate.setHours(tempDate.getHours());
			timeDate.setMinutes(tempDate.getMinutes());
		} catch (ParseException e1) {
			errorPaneMessages+= "\n";
			errorPaneMessages+= "The time, "+timeString+" that you provided cannot be parsed.\n";
			errorPaneMessages+=" It must follow this pattern: h:mm a\n";
			errorPaneMessages+="Where h represents hour \n"
					+"mm represent minutes\n"
					+"a specify if it is an am or a pm hour \n";
			
			//e1.printStackTrace();
		}
		return timeDate;
	}
	
	public Date parseStringDatabaseDateToDate (String databaseDate){
		TimeZone UTCZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("EEE MMM dd h:mm:ss z yyyy");
		dateFormatter.setTimeZone(UTCZone);
		Date date = null;
		
		try {
			date = dateFormatter.parse(databaseDate);
		} catch (ParseException e1) {
			errorPaneMessages+= "\n";
			errorPaneMessages+= "The date, "+databaseDate+" that you provided cannot be parsed.\n";
			errorPaneMessages+=" It must follow this pattern: EEE MMM dd h:mm:ss z yyyy \n";
			errorPaneMessages+="Where EEE represent 3 character name of day of the week\n"
					+"MMM represent 3 character name of the month\n"
					+"dd represent date\n"
					+"h represent hour\n"
					+"mm represent minutes\n"
					+"ss represent seconds\n"
					+"z represent your local time zone\n"
					+"yyyy represent 4 character year\n";
			
		}
		System.out.println("Parsed Date: "+date.toString());
		return date;
	}
	
}
