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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import courierdm.ClientDBAO;
import courierpd.core.Client;
import courierpd.core.User;
import courierpd.map.Intersection;

import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class BillingReportOptionsPanel extends JPanel {
	private JTextField startDateTextField;
	private JTextField endDateTextField;

	/**
	 * Create the panel.
	 */
	public BillingReportOptionsPanel(CourierMainFrame currentFrame, User activeUser) {
		List<Client> persistedClients = ClientDBAO.listClients();
		List<Client> clientList = new ArrayList<Client>();
		
		setLayout(null);
		
		JLabel lblSelectClient = new JLabel("Select Client:");
		lblSelectClient.setBounds(238, 184, 98, 14);
		add(lblSelectClient);
		
		JLabel lblReportStartDate = new JLabel("Report Start Date:");
		lblReportStartDate.setBounds(238, 228, 112, 14);
		add(lblReportStartDate);
		
		JLabel lblReportEndDate = new JLabel("Report End Date:");
		lblReportEndDate.setBounds(238, 273, 112, 14);
		add(lblReportEndDate);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(360, 181, 144, 20);
		DefaultListModel listModel = new DefaultListModel();
		for(Client client: persistedClients)
			comboBox.addItem(client);
		add(comboBox);
		
		startDateTextField = new JTextField();
		startDateTextField.setBounds(360, 225, 144, 20);
		add(startDateTextField);
		startDateTextField.setColumns(10);
		
		endDateTextField = new JTextField();
		endDateTextField.setColumns(10);
		endDateTextField.setBounds(360, 270, 144, 20);
		add(endDateTextField);
		
		JCheckBox chckbxSelectAllClients = new JCheckBox("Select All Clients");
		chckbxSelectAllClients.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				clientList.clear();
				
				if(chckbxSelectAllClients.isSelected())
				{
					for(Client client: persistedClients)
					{
						clientList.add(client);
					}
				}
			}
		});
		chckbxSelectAllClients.setBounds(521, 180, 170, 23);
		add(chckbxSelectAllClients);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBounds(238, 376, 129, 23);
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				if(!chckbxSelectAllClients.isSelected())
				{
					clientList.clear();
					clientList.add((Client) comboBox.getSelectedItem());
				}
				
				Date startDate = parseStringDate(startDateTextField.getText());
				Date endDate = parseStringDate(endDateTextField.getText());
				
				if(startDate.before(endDate))
				{
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new BillingReportPanel(currentFrame, activeUser, clientList, chckbxSelectAllClients.isSelected(), startDate, endDate));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"End Date is not valid; it needs to be after the start date.", 
							"Generation Failed", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		add(btnGenerateReport);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame, activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(428, 376, 115, 23);
		add(btnNewButton);

	}
	
	@SuppressWarnings("deprecation")
	public Date parseStringDate (String timeString){
	
		Date date = new Date();
		Date tempDate = null;
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		try {
			tempDate = dateFormatter.parse(timeString);
			date.setMonth(tempDate.getMonth());
			date.setDate(tempDate.getDate());
			date.setYear(tempDate.getYear());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,
					"Dates need to be entered in the format of 'January 1, 2017'", 
					"Generation Failed", JOptionPane.ERROR_MESSAGE);
		}
		return date;
	}
}
