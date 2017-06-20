package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import courierdm.DeliveryTicketDBAO;
import courierpd.core.Client;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.other.DateParser;

public class CourierPerformanceReport extends JPanel {

	/**
	 * Create the panel.
	 */
	public CourierPerformanceReport(CourierMainFrame currentFrame, User activeUser, List<User> userList, boolean allCouriers, Date startDate, Date endDate) {
		List<DeliveryTicket> persistedDeliveryTickets = DeliveryTicketDBAO.listDeliveryTickets();
		setLayout(null);

		DefaultListModel listModel = new DefaultListModel();
		for(User user: userList)
	
		{
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if ((deliveryTicket.getCourier().getNumber() == user.getNumber()) && ((deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate))))
				{ 
					String bonusstr;
					if(deliveryTicket.getIsBonusEarned()){
						bonusstr = "Yes";
					}
					else {
						bonusstr = "No";
					}
					listModel.addElement(DateParser.printDate(deliveryTicket.getOrderDate()) + "       " + deliveryTicket.getCourier().getNumber() + "        " + DateParser.printTime(deliveryTicket.getRequestedPickUpTime()) + "       " + DateParser.printTime(deliveryTicket.getActualPickUpTime()) + "     " + DateParser.printTime(deliveryTicket.getEstDeliveryTime()) + "      " + DateParser.printTime(deliveryTicket.getActualDeliveryTime()) + "     " + bonusstr); 
			
				}
			}
		}
		JList list = new JList(listModel);
		list.setBounds(64, 135, 897, 288);
		add(list);
		
		JLabel lblCouriersId = new JLabel("Date: " /*+ DateParser.printDate(firstDate) + "-" + DateParser.printDate(secondDate)*/);
		lblCouriersId.setBounds(73, 110, 61, 14);
		add(lblCouriersId);
		
		JLabel lblPackageId = new JLabel("Courier's ID");
		lblPackageId.setBounds(144, 110, 70, 14);
		add(lblPackageId);
		
		JLabel lblDateOfThe = new JLabel("Estimated Pickup Time");
		lblDateOfThe.setBounds(247, 110, 137, 14);
		add(lblDateOfThe);
		
		JLabel lblDateOfThe_1 = new JLabel("Actual Pickup Time");
		lblDateOfThe_1.setBounds(418, 110, 127, 14);
		add(lblDateOfThe_1);
		
		JLabel lblReportedDeliveryTime = new JLabel("Estimated Delivery Time");
		lblReportedDeliveryTime.setBounds(551, 110, 151, 14);
		add(lblReportedDeliveryTime);
		
		JLabel lblActualDeliveryTime = new JLabel("Actual Delivery Time");
		lblActualDeliveryTime.setBounds(712, 110, 127, 14);
		add(lblActualDeliveryTime);
		
		JButton btnSaveAsPdf = new JButton("Save As PDF");
		btnSaveAsPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSaveAsPdf.setBounds(320, 445, 108, 23);
		add(btnSaveAsPdf);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame,activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(494, 445, 100, 23);
		add(btnCancel);
		
		JLabel lblCompanyPerformanceReport = new JLabel("Courier Performance Report");
		lblCompanyPerformanceReport.setBounds(337, 40, 216, 14);
		add(lblCompanyPerformanceReport);
		
		JLabel lblBonusRecieved = new JLabel("Bonus Recieved");
		lblBonusRecieved.setBounds(845, 110, 116, 14);
		add(lblBonusRecieved);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(64, 58, 344, 14);
		add(lblDate);
		
		String name ="";
		if(allCouriers){
			name = "All Couriers";
		}
		else{
			for(User user: userList)
			{
				name = user.getName();
			}
		}
		
		JLabel lblCourier = new JLabel("Courier: " + name );
		lblCourier.setBounds(64, 85, 364, 14);
		add(lblCourier);

	}
}
