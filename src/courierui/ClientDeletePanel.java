package courierui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import courierpd.core.Client;
import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;

public class ClientDeletePanel extends JPanel {

	/**
	 * Generated serialization unique identifier.
	 * This would be used if the object were streamed
	 * in bit form so that it would be reconstructed correctly.
	 */
	private static final long serialVersionUID = -8967217388785737230L;

	/**
	 * Create the panel.
	 */
	public ClientDeletePanel(CourierMainFrame currentFrame, Client client) {
		setLayout(null);
		
		JLabel lblDeleteEmployee = new JLabel("Delete Client");
		lblDeleteEmployee.setBounds(383, 51, 102, 16);
		add(lblDeleteEmployee);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(287, 116, 38, 16);
		add(lblName);
		
		JLabel lblNameGoesHere = new JLabel(client.getName());
		lblNameGoesHere.setBounds(479, 116, 94, 16);
		add(lblNameGoesHere);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(287, 180, 66, 16);
		add(lblIdNumber);
		
		JLabel lblNumberGoesHere = new JLabel(Integer.valueOf(client.getAccountNumber()).toString());
		lblNumberGoesHere.setBounds(479, 180, 106, 16);
		add(lblNumberGoesHere);
		
		JLabel lblDoYouWant = new JLabel("Do you want to delete this client's information?");
		lblDoYouWant.setBounds(301, 304, 284, 16);
		add(lblDoYouWant);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
					userTransaction.begin();
					
					ClientDBAO.removeClient(client);
					userTransaction.commit();
				} catch(Exception e)
				{
					
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ClientManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setBounds(223, 401, 124, 49);
		add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ClientManagementPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(519, 401, 124, 49);
		add(btnCancel);
	}

}
