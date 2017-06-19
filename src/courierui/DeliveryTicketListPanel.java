package courierui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import courierdm.DeliveryTicketDBAO;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;

public class DeliveryTicketListPanel extends JPanel {

	/**
	 * Allows for this class to be streamed and reconstructed.
	 */
	private static final long serialVersionUID = -5049134341489404618L;
	JButton btnUpdate, btnDelete;
	/**
	 * Create the panel.
	 * @param activeUser 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeliveryTicketListPanel(CourierMainFrame mainFrame, String sortCriterium, User activeUser) {
		setLayout(null);
		
		JLabel lblDeliveryTicketList = new JLabel("Delivery Ticket List");
		lblDeliveryTicketList.setBounds(451, 11, 229, 14);
		add(lblDeliveryTicketList);
		
		JLabel lblSortTicketsBy = new JLabel("Sort Tickets By");
		lblSortTicketsBy.setBounds(51, 26, 89, 14);
		add(lblSortTicketsBy);
		
		// Read in the delivery tickets and sort them based on the passed
		// in sort criterium. The default is by package ID.
		// The criterium will be used to sort the list, which will
		// be given to a DefaultListModel, which will finally go into
		// a JList Component.
		List<DeliveryTicket> sortableList = DeliveryTicketDBAO.listDeliveryTickets();
		if (sortCriterium.equals("Package Id")) {					
			Collections.sort(sortableList);   // The default does not require a static call flag
		} else if (sortCriterium.equals("Order Date")) {
			Collections.sort(sortableList, DeliveryTicket.DeliveryTicketOrderDateComparator);
		} else if (sortCriterium.equals("Status")) {
			Collections.sort(sortableList, DeliveryTicket.DeliveryTicketStatusComparator);
		} else if (sortCriterium.equals("Sender names")) {
			Collections.sort(sortableList, DeliveryTicket.DeliveryTicketSendingClientComparator);
		} else if (sortCriterium.equals("Receiver names")) {
			Collections.sort(sortableList, DeliveryTicket.DeliveryTicketReceivingClientComparator);
		}
		
		// Add the sorted list to the list model, which
		// cannot be sorted with the Collections.sort
		// Elements need to be added individually.
		DefaultListModel listModel = new DefaultListModel();
		for (DeliveryTicket ticket : sortableList) {
			listModel.addElement(ticket);
		}
		
		// The list model is required for the JList.
		JList list = new JList(listModel);
		list.setFont(new Font("Courier New", Font.PLAIN, 12));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				btnDelete.setEnabled(true);
					 		
				btnUpdate.setEnabled(true);
			}
		});
		list.setBounds(50, 100, 900,380);
		add(list);
		
		// The combobox causes the screen to be revalidated
		JComboBox sortComboBox = new JComboBox();
		sortComboBox.addItem("Package Id");         // Make sure items are added to the combobox before
		sortComboBox.addItem("Order Date");         // the listener code is added. There will be a stack
		sortComboBox.addItem("Status");             // overflow otherwise!
		sortComboBox.addItem("Sender names");
		sortComboBox.addItem("Receiver names");
		sortComboBox.addItemListener(new ItemListener() {
			// Allows for delivery tickets to be sorted by the
			// selected item's natural ordering. This will also
			// refresh the screen so that the sorting is displayed.
			public void itemStateChanged(ItemEvent event) {
				Object item = event.getItem(); // Will be a string from the combobox
				if (item != null) {
					mainFrame.getContentPane().removeAll();
					mainFrame.getContentPane().add(new DeliveryTicketListPanel(mainFrame, item.toString(), activeUser));
					mainFrame.getContentPane().revalidate();
				}
			}
		});
		

		sortComboBox.setBounds(160, 23, 140, 20);
		add(sortComboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(new AddUpdateDeliveryTicketPanel(mainFrame,new DeliveryTicket(),activeUser,true));
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
				mainFrame.getContentPane().add(new AddUpdateDeliveryTicketPanel(mainFrame,(DeliveryTicket)list.getSelectedValue(),activeUser,false));
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
				mainFrame.getContentPane().add(new DeleteTicketPanel(mainFrame,(DeliveryTicket)list.getSelectedValue(), activeUser));
				mainFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setBounds(703, 500, 89, 23);
		add(btnDelete);
		
		JLabel lblPackageId = new JLabel("Package ID Number");
		lblPackageId.setBounds(51, 70, 200, 16);
		add(lblPackageId);
		
		JLabel lblTicketDate = new JLabel("Date");
		lblTicketDate.setBounds(290, 71, 56, 16);
		add(lblTicketDate);
		
		JLabel lblCourierId = new JLabel("Status");
		lblCourierId.setBounds(482, 71, 118, 16);
		add(lblCourierId);
		
		JLabel lblSender = new JLabel("Sending Client");
		lblSender.setBounds(652, 70, 97, 16);
		add(lblSender);
		
		JLabel lblReceiver = new JLabel("Receiving Client");
		lblReceiver.setBounds(827, 71, 106, 16);
		add(lblReceiver);
	}
}
