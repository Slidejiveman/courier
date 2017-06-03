package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmployeeDeletePanel extends JPanel {

	/**
	 * Generated serialization unique identifier.
	 * This would be used if the object were streamed
	 * in bit form so that it would be reconstructed correctly.
	 */
	private static final long serialVersionUID = 5977931941141100650L;

	/**
	 * Create the panel.
	 */
	public EmployeeDeletePanel() {
        setLayout(null);
        
        JLabel lblDeleteEmployee = new JLabel("Delete Employee");
		lblDeleteEmployee.setBounds(383, 51, 102, 16);
		add(lblDeleteEmployee);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(287, 116, 38, 16);
		add(lblName);
		
		JLabel lblNameGoesHere = new JLabel("Name goes here");
		lblNameGoesHere.setBounds(479, 116, 94, 16);
		add(lblNameGoesHere);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(287, 180, 66, 16);
		add(lblIdNumber);
		
		JLabel lblNumberGoesHere = new JLabel("Number goes here");
		lblNumberGoesHere.setBounds(479, 180, 106, 16);
		add(lblNumberGoesHere);
		
		JLabel lblDoYouWant = new JLabel("Do you want to delete this employee's information?");
		lblDoYouWant.setBounds(291, 303, 307, 16);
		add(lblDoYouWant);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.setBounds(223, 401, 124, 49);
		add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(519, 401, 124, 49);
		add(btnCancel);
	}

}