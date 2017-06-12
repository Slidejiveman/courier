package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import courierpd.core.User;
import courierpd.other.Authorizer;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	private User activeUser = null;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login(CourierMainFrame currentFrame) {
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(273, 207, 54, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(273, 249, 46, 14);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(361, 204, 155, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(361, 246, 155, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login to Ubiquity");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(361, 155, 155, 38);
		add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authorizer a = new Authorizer (textField.getText(),textField_1.getText());
			    activeUser = a.getActiveUser();
			    currentFrame.setAuthorizedUser(activeUser); // the valid user or null if bad credentials
			    if (activeUser!= null) {			    	
			    	currentFrame.getContentPane().removeAll();
			    	currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame));
			    	currentFrame.getContentPane().revalidate();
			    }
			}
		});
		//
		btnLogin.setBounds(368, 323, 89, 23);
		add(btnLogin);

	}
}
