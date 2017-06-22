package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import courierdm.EmployeeDBAO;
import courierpd.core.User;
import courierpd.enums.EmployeeRole;

public class CourierPerformanceOptions extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private boolean invalidEntry = false;
	/**
	 * Create the panel.
	 */
	public CourierPerformanceOptions(CourierMainFrame currentFrame, User activeUser) {
		List<User> persistedUsers = EmployeeDBAO.listUsers();
		List<User> userList = new ArrayList<User>();
		
		setLayout(null);
		
		JRadioButton rdbtnWeekly = new JRadioButton("Weekly");
		rdbtnWeekly.setBounds(626, 221, 109, 23);
		add(rdbtnWeekly);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBounds(626, 247, 109, 23);
		add(rdbtnMonthly);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnWeekly);
	    buttonGroup.add(rdbtnMonthly);
	    buttonGroup.setSelected(rdbtnWeekly.getModel(), true);
	    
		JCheckBox chckbxNewCheckBox = new JCheckBox("Select All Couriers");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				userList.clear();
				
				if(chckbxNewCheckBox.isSelected())
				{
					for(User user: persistedUsers)
					{
						if(user.getEmployeeRole().equals(EmployeeRole.Courier)){
							userList.add(user);
						}
					}
				}
			}
		});
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
				if(!chckbxNewCheckBox.isSelected())
				{
					userList.clear();
					userList.add((User) comboBox.getSelectedItem());
				}
				Date startDate = parseStringDate(textField.getText());
				Date endDate = parseStringDate(textField_1.getText());
				
				if(startDate.before(endDate) && invalidEntry == false)
				{
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new CourierPerformanceReport(currentFrame,activeUser, userList, chckbxNewCheckBox.isSelected(), startDate, endDate));
					currentFrame.getContentPane().revalidate();
				}
				else if(startDate.before(endDate) && invalidEntry == true)
				{
					JOptionPane.showMessageDialog(null,
							"Dates need to be entered in the format of '01/01/2017'", 
							"Generation Failed", JOptionPane.ERROR_MESSAGE);
					invalidEntry = false;
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"End Date is not valid; it needs to be after the start date.", 
							"Generation Failed", JOptionPane.ERROR_MESSAGE);
				}
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
	@SuppressWarnings("deprecation")
	public Date parseStringDate (String timeString){
	
		Date date = new Date();
		Date tempDate = null;
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		try {
			tempDate = dateFormatter.parse(timeString);
			date.setMonth(tempDate.getMonth());
			date.setDate(tempDate.getDate());
			date.setYear(tempDate.getYear());
		} catch (ParseException e1) {
			invalidEntry = true;
		}
		return date;
	}
}
