package courierui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddUpdateClientPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public AddUpdateClientPanel() {
		setLayout(null);
		
		JLabel lblAddClient = new JLabel("Add Client");
		lblAddClient.setBounds(321, 72, 58, 14);
		add(lblAddClient);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(162, 164, 46, 14);
		add(lblName);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(162, 200, 94, 14);
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
			}
		});
		saveButton.setBounds(232, 438, 89, 23);
		add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton.setBounds(385, 438, 89, 23);
		add(cancelButton);
		
		textField = new JTextField();
		textField.setBounds(266, 161, 232, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(266, 233, 232, 20);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(266, 268, 232, 20);
		add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(227, 336, 94, 20);
		comboBox.addItem("A St. & 1st");
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
		comboBox.addItem("D St. & 4th");
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
		comboBox.addItem("G St. & 7th");
		add(comboBox);
		
		JLabel lblAcctGoes = new JLabel("Acct # goes here");
		lblAcctGoes.setBounds(266, 200, 232, 14);
		add(lblAcctGoes);

	}
}
