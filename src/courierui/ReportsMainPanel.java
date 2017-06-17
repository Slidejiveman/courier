package courierui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ReportsMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public ReportsMainPanel(CourierMainFrame currentFrame) {
		
		setLayout(null);
		
		JButton btnBilling = new JButton("Billing");
		btnBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBilling.setBounds(425, 123, 187, 23);
		add(btnBilling);
		
		JButton btnCourierPerformance = new JButton("Courier Performance");
		btnCourierPerformance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCourierPerformance.setBounds(425, 193, 187, 23);
		add(btnCourierPerformance);
		
		JButton btnCompanyPerformace = new JButton("Company Performace");
		btnCompanyPerformace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompanyPerformace.setBounds(425, 263, 187, 23);
		add(btnCompanyPerformace);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EmployeeManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(425, 330, 187, 23);
		add(btnCancel);

	}
}
