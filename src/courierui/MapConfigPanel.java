package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapConfigPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MapConfigPanel(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JLabel lblUpdateMapInformation = new JLabel("Update Map Information");
		lblUpdateMapInformation.setBounds(450, 30, 178, 14);
		add(lblUpdateMapInformation);
		
		JLabel lblSelectIntersection = new JLabel("Select Intersection:");
		lblSelectIntersection.setBounds(50, 125, 150, 14);
		add(lblSelectIntersection);
		
		JLabel lblEstimatedReopeningDate = new JLabel("Estimated Re-opening Date:");
		lblEstimatedReopeningDate.setBounds(50, 159, 250, 14);
		add(lblEstimatedReopeningDate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClose.setBounds(296, 197, 89, 23);
		add(btnClose);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(296, 122, 200, 20);
		add(comboBox);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(296, 156, 200, 20);
		add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(566, 120, 400, 300);
		add(textArea);
		
		JLabel lblCurrentlyClosedIntersections = new JLabel("Currently Closed Intersections");
		lblCurrentlyClosedIntersections.setBounds(667, 65, 200, 14);
		add(lblCurrentlyClosedIntersections);
		
		JLabel lblIntersection = new JLabel("Intersection");
		lblIntersection.setBounds(616, 105, 100, 14);
		add(lblIntersection);
		
		JLabel lblClosingDate = new JLabel("Closing Date");
		lblClosingDate.setBounds(730, 105, 100, 14);
		add(lblClosingDate);
		
		JLabel lblReopeningDate = new JLabel("Re-opening Date");
		lblReopeningDate.setBounds(845, 105, 100, 14);
		add(lblReopeningDate);
		
		JButton btnReopen = new JButton("Re-open");
		btnReopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReopen.setBounds(720, 435, 89, 23);
		add(btnReopen);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(600, 470, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(840, 470, 89, 23);
		add(btnCancel);

	}

}
