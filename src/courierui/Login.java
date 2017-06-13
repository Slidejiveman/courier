package courierui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import courierpd.core.User;
import courierpd.other.Authorizer;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User activeUser = null;
	private JTextField textField;
	private JTextField textField_1;

	public static void startGUI(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	public Login() {
		Login currentFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 602);
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(273, 207, 78, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(273, 249, 78, 14);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(361, 204, 155, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
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
			    if (activeUser!= null) {			    	
			    	CourierMainFrame.startGUI(activeUser, currentFrame);
			    	textField.setText("");
			    	textField_1.setText("");
			    	currentFrame.setVisible(false);
			    }
			}
		});
		//
		btnLogin.setBounds(368, 323, 89, 23);
		add(btnLogin);

	}
}
