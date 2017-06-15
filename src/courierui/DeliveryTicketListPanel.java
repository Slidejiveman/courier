package courierui;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import courierdm.DeliveryTicketDBAO;
import courierpd.core.DeliveryTicket;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class DeliveryTicketListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049134341489404618L;
	JButton btnUpdate, btnDelete;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeliveryTicketListPanel(CourierMainFrame mainFrame) {
		setLayout(null);
		
		JLabel lblDeliveryTicketList = new JLabel("Delivery Ticket List");
		lblDeliveryTicketList.setBounds(451, 11, 229, 14);
		add(lblDeliveryTicketList);
		
		JLabel lblSortTicketsBy = new JLabel("Sort Tickets By");
		lblSortTicketsBy.setBounds(51, 26, 89, 14);
		add(lblSortTicketsBy);
		
		JComboBox sortComboBox = new JComboBox();
		sortComboBox.addItem("Package Id");
		sortComboBox.addItem("Order Date");
		sortComboBox.addItem("Status");
		sortComboBox.addItem("Sender names");
		sortComboBox.addItem("Receiver names");

		sortComboBox.setBounds(160, 23, 140, 20);
		add(sortComboBox);
		
		DefaultListModel listModel = new DefaultListModel();
		for (DeliveryTicket ticket: DeliveryTicketDBAO.listDeliveryTickets()){		
			listModel.addElement(ticket);
		}
		JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				btnDelete.setEnabled(true);
					 		
				btnUpdate.setEnabled(true);
			}
		});
		list.setBounds(50, 100, 900,380);
		add(list);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new AddUpdateDeliveryTicketPanel(mainFrame,new DeliveryTicket(),true));
				mainFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(211, 500, 89, 23);
		add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new AddUpdateDeliveryTicketPanel(mainFrame,(DeliveryTicket)list.getSelectedValue(),false));
				mainFrame.getContentPane().revalidate();
			}
		});
		btnUpdate.setBounds(456, 500, 89, 23);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new DeleteTicketPanel(mainFrame,(DeliveryTicket)list.getSelectedValue() ));
				mainFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setBounds(703, 500, 89, 23);
		add(btnDelete);
		
		JLabel lblPackageId = new JLabel("Package ID Number");
		lblPackageId.setBounds(100, 70, 200, 16);
		add(lblPackageId);
		
		JLabel lblTicketDate = new JLabel("Date");
		lblTicketDate.setBounds(300, 70, 56, 16);
		add(lblTicketDate);
		
		JLabel lblCourierId = new JLabel("Status");
		lblCourierId.setBounds(450, 75, 118, 16);
		add(lblCourierId);
		
		JLabel lblSender = new JLabel("Sending Client");
		lblSender.setBounds(600, 75, 97, 16);
		add(lblSender);
		
		JLabel lblReceiver = new JLabel("Receiving Client");
		lblReceiver.setBounds(800, 75, 106, 16);
		add(lblReceiver);
	}
}
