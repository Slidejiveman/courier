package courierui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import courierpd.core.User;
import courierpd.enums.EmployeeRole;
import courierpd.map.CityMap;

@SuppressWarnings("unused")
public class CourierMainFrame extends JFrame {

	/**
	 * Generated serialization unique identifier.
	 * This would be used if the object were streamed
	 * in bit form so that it would be reconstructed correctly.
	 */
	private static final long serialVersionUID = -2964820041018829006L;
	
	private JPanel contentPane; 
	private User authorizedUser = null;

	/**
	 * 
	 * We'll probably want to create the login screen as a separate frame
	 * since there will be different menu items depending on which user
	 * logs in. We could also control the display of the menu based on
	 * whether the login panel was currently displayed and the employee role
	 * of the current authorized user.
	 */
	public static void startGUI(User activeUser, Login loginFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourierMainFrame frame = new CourierMainFrame(activeUser, loginFrame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourierMainFrame(User activeUser, Login loginFrame) {
		CourierMainFrame currentFrame = this;
		CityMap map = new CityMap();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 602);
		
		JMenuBar menuBar = null;
		menuBar = new JMenuBar();
		//if (authorizedUser != null)
			setJMenuBar(menuBar);	
		
		JMenuItem mntmDeliveryTickets = new JMenuItem("Delivery Tickets");
		mntmDeliveryTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				// In the add, place the constructor for your panel
				//getContentPane().add();
				getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id"));
				getContentPane().revalidate();
			}
		});
		menuBar.add(mntmDeliveryTickets);
		
		JMenuItem mntmEmployees = new JMenuItem("Employees");
		mntmEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new EmployeeManagementPanel(currentFrame));
				getContentPane().revalidate();
			}
		});
		menuBar.add(mntmEmployees);
		
		JMenuItem mntmClients = new JMenuItem("Clients");
		mntmClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ClientManagementPanel(currentFrame));
				getContentPane().revalidate();
			}
		});
		menuBar.add(mntmClients);
		
		JMenuItem mntmBusinessParameters = new JMenuItem("Business Parameters");
		mntmBusinessParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpdateBusinessParametersPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
				getContentPane().revalidate();
			}
		});
        if (activeUser.getEmployeeRole().equals(EmployeeRole.Owner)){ 
        	menuBar.add(mntmBusinessParameters);
		}		
		
		JMenuItem mntmMapConfiguration = new JMenuItem("Map Configuration");
		mntmMapConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				// In the add, place the constructor for your panel
				//getContentPane().add();
				getContentPane().add(new MapConfigPanel(currentFrame, map));
				getContentPane().revalidate();
			}
		});
		menuBar.add(mntmMapConfiguration);
		
		JMenuItem mntmReports = new JMenuItem("Reports");
		mntmReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportsMainPanel(currentFrame));
				// In the add, place the constructor for your panel
				//getContentPane().add();
				getContentPane().revalidate();
			}
		});
		menuBar.add(mntmReports);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				currentFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		});
		menuBar.add(mntmLogout);
		
		// The default panel to display should be added here.
		// This will eventually be the login screen.
		currentFrame.getContentPane().removeAll();
		currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id"));
		currentFrame.getContentPane().revalidate();
		
	}

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(User authorizedUser) {
		this.authorizedUser = authorizedUser;
	}
    
	
}
