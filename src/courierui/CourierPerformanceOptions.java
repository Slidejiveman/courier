package courierui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import courierdm.EmployeeDBAO;
import courierpd.core.User;
import courierpd.enums.EmployeeRole;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourierPerformanceOptions extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public CourierPerformanceOptions(CourierMainFrame currentFrame, User activeUser) {
setLayout(null);
		
		JRadioButton rdbtnWeekly = new JRadioButton("Weekly");
		rdbtnWeekly.setBounds(626, 221, 109, 23);
		add(rdbtnWeekly);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBounds(626, 247, 109, 23);
		add(rdbtnMonthly);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Select All Couriers");
		chckbxNewCheckBox.setBounds(626, 158, 155, 23);
		add(chckbxNewCheckBox);
		
		JLabel lblReportStartDate = new JLabel("Report Start Date");
		lblReportStartDate.setBounds(296, 208, 127, 14);
		add(lblReportStartDate);
		
		JLabel lblReportEndDate = new JLabel("Report End Date");
		lblReportEndDate.setBounds(296, 251, 97, 14);
		add(lblReportEndDate);
		
		JLabel lblSelectCourier = new JLabel("Select Courier");
		lblSelectCourier.setBounds(296, 162, 97, 14);
		add(lblSelectCourier);
		
		JComboBox comboBox = new JComboBox();
		for(User user: EmployeeDBAO.listUsers()){
			if(user.getEmployeeRole().equals(EmployeeRole.Courier)){
				comboBox.addItem(user);
			}
		}
		comboBox.setBounds(444, 159, 141, 20);
		add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(444, 205, 141, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(444, 248, 141, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CourierPerformanceReport(currentFrame,activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnGenerate.setBounds(399, 385, 89, 23);
		add(btnGenerate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame,activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(530, 385, 89, 23);
		add(btnCancel);
	}

}
