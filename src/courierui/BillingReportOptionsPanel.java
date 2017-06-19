package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import courierdm.ClientDBAO;
import courierpd.core.Client;
import courierpd.map.Intersection;

import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class BillingReportOptionsPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public BillingReportOptionsPanel(CourierMainFrame currentFrame) {
		List<Client> persistedClients = ClientDBAO.listClients();
		
		setLayout(null);
		
		JLabel lblSelectClient = new JLabel("Select Client:");
		lblSelectClient.setBounds(130, 106, 63, 14);
		add(lblSelectClient);
		
		JLabel lblReportStartDate = new JLabel("Report Start Date:");
		lblReportStartDate.setBounds(130, 150, 98, 14);
		add(lblReportStartDate);
		
		JLabel lblReportEndDate = new JLabel("Report End Date:");
		lblReportEndDate.setBounds(130, 195, 98, 14);
		add(lblReportEndDate);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(238, 103, 132, 20);
		DefaultListModel listModel = new DefaultListModel();
		for(Client client: persistedClients)
			comboBox.addItem(client);
		add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(238, 147, 132, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(238, 192, 132, 20);
		add(textField_1);
		
		JCheckBox chckbxSelectAllClients = new JCheckBox("Select All Clients");
		chckbxSelectAllClients.setBounds(376, 102, 115, 23);
		add(chckbxSelectAllClients);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBounds(130, 298, 115, 23);
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new BillingReportPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnGenerateReport);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(306, 298, 115, 23);
		add(btnNewButton);

	}
}
