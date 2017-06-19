package courierui;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyPerformanceReport extends JPanel {

	/**
	 * Create the panel.
	 */
	public CompanyPerformanceReport(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JList list = new JList();
		list.setBounds(56, 135, 804, 288);
		add(list);
		
		JLabel lblCouriersId = new JLabel("Couriers ID");
		lblCouriersId.setBounds(56, 110, 78, 14);
		add(lblCouriersId);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setBounds(144, 110, 70, 14);
		add(lblPackageId);
		
		JLabel lblDateOfThe = new JLabel("Date of the Delivery");
		lblDateOfThe.setBounds(247, 110, 123, 14);
		add(lblDateOfThe);
		
		JLabel lblDateOfThe_1 = new JLabel("Date of the Report");
		lblDateOfThe_1.setBounds(380, 110, 108, 14);
		add(lblDateOfThe_1);
		
		JLabel lblReportedDeliveryTime = new JLabel("Reported Delivery Time");
		lblReportedDeliveryTime.setBounds(522, 110, 137, 14);
		add(lblReportedDeliveryTime);
		
		JLabel lblActualDeliveryTime = new JLabel("Actual Delivery Time");
		lblActualDeliveryTime.setBounds(689, 110, 127, 14);
		add(lblActualDeliveryTime);
		
		JButton btnSaveAsPdf = new JButton("Save As PDF");
		btnSaveAsPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSaveAsPdf.setBounds(320, 445, 108, 23);
		add(btnSaveAsPdf);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(494, 445, 100, 23);
		add(btnCancel);
		
		JLabel lblCompanyPerformanceReport = new JLabel("Company Performance Report");
		lblCompanyPerformanceReport.setBounds(337, 40, 216, 14);
		add(lblCompanyPerformanceReport);

	}
}
