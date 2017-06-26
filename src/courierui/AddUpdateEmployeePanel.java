package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierdm.EmployeeDBAO;
import courieremail.EmailValidator;
import courierpd.core.User;
import courierpd.enums.EmployeeRole;
import courierpd.map.Intersection;
import courierpd.other.PasswordValidator;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	JComboBox<EmployeeRole> comboBox_1;

	/**
	 * Create the panel.
	 */
	public AddUpdateEmployeePanel(CourierMainFrame currentFrame, User employee, boolean isAdd) {
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
		
		JLabel lblAddEmployee = null;
		if(isAdd) {
			lblAddEmployee = new JLabel("Add Employee");
		} else {
			lblAddEmployee = new JLabel("Update Employee");
		}
		lblAddEmployee.setBounds(314, 65, 125, 16);
		add(lblAddEmployee);
		
		JLabel lblNumberGoesHere = new JLabel(String.valueOf(employee.getNumber()));
		lblNumberGoesHere.setBounds(222, 174, 114, 16);
		add(lblNumberGoesHere);
		
		JCheckBox chckbxIsEmployeeActive = new JCheckBox("Is Employee Active?");
		chckbxIsEmployeeActive.setBounds(314, 458, 146, 25);
		if (isAdd) {
			chckbxIsEmployeeActive.setSelected(true);
		} else {
			chckbxIsEmployeeActive.setSelected(employee.getIsActive());
		}
		add(chckbxIsEmployeeActive);
		
		String strName ="";
		if(employee.getName() !=null)
		strName = employee.getName();
		textField = new JTextField(strName);
		textField.setBounds(220, 125, 327, 22);
		add(textField);
		textField.setColumns(10);
		
		String strEmail ="";
		if(employee.getEmail() !=null)
		strEmail = employee.getEmail();
		textField_1 = new JTextField(strEmail);
		textField_1.setBounds(220, 231, 327, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		String strUsername ="";
		if(employee.getUsername() !=null)
			strUsername = employee.getUsername();
		textField_2 = new JTextField(strUsername);
		textField_2.setBounds(220, 291, 327, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		String strPassword ="";
		if(employee.getPassword() !=null)
			strPassword = employee.getPassword();
		passwordField = new JPasswordField(strPassword);
		passwordField.setBounds(220, 353, 327, 22);
		add(passwordField);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(222, 407, 57, 22);
		for(int i = 1; i <= 3; i++) {
			comboBox.addItem(i);
		}
		if (!isAdd) {
			comboBox.getModel().setSelectedItem(employee.getShift());
		}
		add(comboBox);
		
		// An Owner cannot be added to the system under normal operating
		// circumstances. This would require special intervention at present
		comboBox_1 = new JComboBox<EmployeeRole>();
		comboBox_1.addItemListener(new ItemListener() {
			
			// We advertised that couriers would not have usernames and passwords
			// and therefore would not be able to access Ubiquity through the app
			// but only through email. This listener disables the field if Courier
			// is in the combobox.
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Object item = event.getItem();
					if (item.equals(EmployeeRole.Courier)) {
						textField_2.setVisible(false);
						passwordField.setVisible(false);
						lblUsername.setVisible(false);
						lblPassword.setVisible(false);
					}
				} else {
					textField_2.setVisible(true);
					passwordField.setVisible(true);
					lblUsername.setVisible(true);
					lblPassword.setVisible(true);
				}
			}
		});
		
		comboBox_1.setBounds(455, 407, 92, 22);
		comboBox_1.addItem(EmployeeRole.Courier);
		comboBox_1.addItem(EmployeeRole.OrderTaker);
		if (!isAdd) {
			comboBox_1.getModel().setSelectedItem(employee.getEmployeeRole());
		} else {
			comboBox_1.getModel().setSelectedItem(EmployeeRole.Courier);
		}
		add(comboBox_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				
				try {
					userTransaction.begin();
				
				if(textField.getText() != null && !textField.getText().matches(".*\\d+.*")) { //Found this online, used to determine if string contains a number or not
					employee.setName(textField.getText());
				}
				else
				{
					userTransaction.rollback();
				}
				if(textField_1.getText() != null && EmailValidator.validate(textField_1.getText())) {
					employee.setEmail(textField_1.getText());
				} else {
					userTransaction.rollback();
				}
				
				// Couriers do not need usernames and passwords
				if (!comboBox_1.getModel().getSelectedItem().equals(EmployeeRole.Courier)) {
					if(textField_2.getText() != null) {
						employee.setUsername(textField_2.getText());
					}	
				
					if(passwordField.getPassword() != null && PasswordValidator.validate(passwordField.getText())) { // Text is not encrypted
						employee.setPassword(passwordField.getPassword().toString());
					} else {
						userTransaction.rollback();
					}
				}
				employee.setShift((Integer) comboBox.getModel().getSelectedItem());
				employee.setIsActive(chckbxIsEmployeeActive.isSelected());
				employee.setEmployeeRole(EmployeeRole.valueOf(comboBox_1.getModel().getSelectedItem().toString()));
				if(isAdd)
				{
					EmployeeDBAO.addUser(employee);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EmployeeManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null,
							"Ensure name, email, and password fields are valid.", 
							"Save Failed", JOptionPane.ERROR_MESSAGE);
					}		
				}
		});
		
		btnSave.setBounds(182, 508, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EmployeeManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(458, 508, 97, 25);
		add(btnCancel);

	}
}
