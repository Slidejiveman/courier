package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.IntersectionDBAO;
import courierpd.core.Client;
import courierpd.map.Intersection;

public class AddUpdateClientPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5368791176130275696L;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox;
	JLabel lblAddClient;
	
	/**
	 * Create the panel.
	 */
	public AddUpdateClientPanel(CourierMainFrame currentFrame, Client client, boolean isAdd) {
		List<Intersection> persistedIntersections = IntersectionDBAO.listIntersections();
		
		setLayout(null);
		
		// Refresh the object so that it is up to date in the database
		// This will cause the update button to throw an exception if
		// there isn't a selected client
		if(!isAdd) {
			//CourierEntityManager.getEntityManager().refresh(client);
		}
		
		// Determine which label should be displayed on the screen
		if(isAdd) {
			lblAddClient = new JLabel("Add Client");
		} else {
			lblAddClient = new JLabel("Update Client");
		}
		
		lblAddClient.setBounds(321, 72, 75, 14);
		add(lblAddClient);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(162, 164, 46, 14);
		add(lblName);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(162, 200, 120, 14);
		add(lblAccountNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(162, 236, 46, 14);
		add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(162, 271, 94, 14);
		add(lblPhoneNumber);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(162, 339, 63, 14);
		add(lblLocation);
		
		JCheckBox clientActiveCheckBox = new JCheckBox("Is this client active?");
		clientActiveCheckBox.setSelected(true);
		clientActiveCheckBox.setBounds(385, 330, 140, 23);
		add(clientActiveCheckBox);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				userTransaction.begin();
				if(textField.getText() != null) {
					client.setName(textField.getText());
				}
				if(textField_2.getText() != null) {
					client.setEmail(textField_2.getText());
				}
				if(textField_3.getText() != null) {
					client.setPhoneNumber(textField_3.getText());
				}			
				client.setLocation((Intersection) comboBox.getSelectedItem());
				client.setIsActive(clientActiveCheckBox.isSelected());
				if(isAdd)
				{
					ClientDBAO.addClient(client);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ClientManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		saveButton.setBounds(232, 438, 89, 23);
		add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ClientManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancelButton.setBounds(385, 438, 89, 23);
		add(cancelButton);
		
		String strName = "";
		if(client.getName() != null)
			strName = client.getName();
		textField = new JTextField(strName);
		textField.setBounds(266, 161, 232, 20);
		add(textField);
		textField.setColumns(10);
		
		String strEmail = "";
		if(client.getEmail() != null)
			strEmail = client.getEmail();
		textField_2 = new JTextField(strEmail);
		textField_2.setColumns(10);
		textField_2.setBounds(266, 233, 232, 20);
		add(textField_2);
		
		String strPhoneNumber = "";
		if(client.getPhoneNumber() != null)
			strPhoneNumber = client.getPhoneNumber();
		textField_3 = new JTextField(strPhoneNumber);
		textField_3.setColumns(10);
		textField_3.setBounds(266, 268, 232, 20);
		add(textField_3);
		
		comboBox = new JComboBox(); // Occupied intersections probably shouldn't be assignable.
		comboBox.setBounds(227, 336, 94, 20);
		
		DefaultListModel listModel = new DefaultListModel();
		for(Intersection intersection: persistedIntersections)
			comboBox.addItem(intersection);
		
/*		comboBox.addItem("A St. & 1st");
		comboBox.addItem("A St. & 2nd");
		comboBox.addItem("A St. & 3rd");
		comboBox.addItem("A St. & 4th");
		comboBox.addItem("A St. & 5th");
		comboBox.addItem("A St. & 6th");
		comboBox.addItem("A St. & 7th");
		
		comboBox.addItem("B St. & 1st");
		comboBox.addItem("B St. & 2nd");
		comboBox.addItem("B St. & 3rd");
		comboBox.addItem("B St. & 4th");
		comboBox.addItem("B St. & 5th");
		comboBox.addItem("B St. & 6th");
		comboBox.addItem("B St. & 7th");
		
		comboBox.addItem("C St. & 1st");
		comboBox.addItem("C St. & 2nd");
		comboBox.addItem("C St. & 3rd");
		comboBox.addItem("C St. & 4th");
		comboBox.addItem("C St. & 5th");
		comboBox.addItem("C St. & 6th");
		comboBox.addItem("C St. & 7th");
		
		comboBox.addItem("D St. & 1st");
		comboBox.addItem("D St. & 2nd");
		comboBox.addItem("D St. & 3rd");
		// Location of the office.
		comboBox.addItem("D St. & 5th"); 
		comboBox.addItem("D St. & 6th");
		comboBox.addItem("D St. & 7th");
		
		comboBox.addItem("E St. & 1st");
		comboBox.addItem("E St. & 2nd");
		comboBox.addItem("E St. & 3rd");
		comboBox.addItem("E St. & 4th");
		comboBox.addItem("E St. & 5th");
		comboBox.addItem("E St. & 6th");
		comboBox.addItem("E St. & 7th");
		
		comboBox.addItem("F St. & 1st");
		comboBox.addItem("F St. & 2nd");
		comboBox.addItem("F St. & 3rd");
		comboBox.addItem("F St. & 4th");
		comboBox.addItem("F St. & 5th");
		comboBox.addItem("F St. & 6th");
		comboBox.addItem("F St. & 7th");
		
		comboBox.addItem("G St. & 1st");
		comboBox.addItem("G St. & 2nd");
		comboBox.addItem("G St. & 3rd");
		comboBox.addItem("G St. & 4th");
		comboBox.addItem("G St. & 5th");
		comboBox.addItem("G St. & 6th");
		comboBox.addItem("G St. & 7th"); */
		add(comboBox);
		
		JLabel lblAcctGoes = new JLabel(Integer.valueOf(client.getAccountNumber()).toString());
		lblAcctGoes.setBounds(266, 200, 232, 14);
		add(lblAcctGoes);

	}
}
