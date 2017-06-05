package courierui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import courierpd.enums.EmployeeRole;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUpdateEmployeePanel extends JPanel {

	/**
	 * Generated serialization unique identifier.
	 * This would be used if the object were streamed
	 * in bit form so that it would be reconstructed correctly.
	 */
	private static final long serialVersionUID = 8874200017660895507L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public AddUpdateEmployeePanel() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(105, 128, 48, 16);
		add(lblName);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(105, 174, 68, 16);
		add(lblIdNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(105, 234, 37, 16);
		add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(105, 294, 68, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(105, 356, 68, 16);
		add(lblPassword);
		
		JLabel lblShift = new JLabel("Shift:");
		lblShift.setBounds(105, 410, 37, 16);
		add(lblShift);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(402, 410, 37, 16);
		add(lblRole);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setBounds(314, 65, 96, 16);
		add(lblAddEmployee);
		
		JLabel lblNumberGoesHere = new JLabel("Number goes here");
		lblNumberGoesHere.setBounds(222, 174, 114, 16);
		add(lblNumberGoesHere);
		
		JCheckBox chckbxIsEmployeeActive = new JCheckBox("Is Employee Active?");
		chckbxIsEmployeeActive.setBounds(314, 458, 146, 25);
		add(chckbxIsEmployeeActive);
		
		textField = new JTextField();
		textField.setBounds(220, 125, 335, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(220, 231, 335, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(220, 291, 335, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(222, 353, 327, 22);
		add(passwordField);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(222, 407, 57, 22);
		for(int i = 1; i <= 3; i++) {
			comboBox.addItem(i);
		}		
		add(comboBox);
		
		// An Owner cannot be added to the system under normal operating
		// circumstances. This would require special intervention at present
		JComboBox<EmployeeRole> comboBox_1 = new JComboBox<EmployeeRole>();
		comboBox_1.setBounds(492, 407, 57, 22);
		comboBox_1.addItem(courierpd.enums.EmployeeRole.Courier);
		comboBox_1.addItem(courierpd.enums.EmployeeRole.OrderTaker);
		add(comboBox_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(182, 508, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Go back to employee list panel
			}
		});
		btnCancel.setBounds(458, 508, 97, 25);
		add(btnCancel);

	}
}
