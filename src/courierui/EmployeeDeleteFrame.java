package courierui;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EmployeeDeleteFrame extends JFrame {

	/**
	 * Generated serialization identifier
	 */
	private static final long serialVersionUID = -5323686099476212761L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDeleteFrame frame = new EmployeeDeleteFrame();
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
	public EmployeeDeleteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 552);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmDeliveryTickets = new JMenuItem("Delivery Tickets");
		mntmDeliveryTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmDeliveryTickets);
		
		JMenuItem mntmEmployees = new JMenuItem("Employees");
		mntmEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmEmployees);
		
		JMenuItem mntmClients = new JMenuItem("Clients");
		mntmClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmClients);
		
		JMenuItem mntmBusinessParameters = new JMenuItem("Business Parameters");
		mntmBusinessParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmBusinessParameters);
		
		JMenuItem mntmMapConfiguration = new JMenuItem("Map Configuration");
		mntmMapConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmMapConfiguration);
		
		JMenuItem mntmReports = new JMenuItem("Reports");
		mntmReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmReports);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteEmployee = new JLabel("Delete Employee");
		lblDeleteEmployee.setBounds(383, 51, 102, 16);
		contentPane.add(lblDeleteEmployee);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(287, 116, 38, 16);
		contentPane.add(lblName);
		
		JLabel lblNameGoesHere = new JLabel("Name goes here");
		lblNameGoesHere.setBounds(479, 116, 94, 16);
		contentPane.add(lblNameGoesHere);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(287, 180, 66, 16);
		contentPane.add(lblIdNumber);
		
		JLabel lblNumberGoesHere = new JLabel("Number goes here");
		lblNumberGoesHere.setBounds(479, 180, 106, 16);
		contentPane.add(lblNumberGoesHere);
		
		JLabel lblDoYouWant = new JLabel("Do you want to delete this employee's information?");
		lblDoYouWant.setBounds(291, 303, 307, 16);
		contentPane.add(lblDoYouWant);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.setBounds(223, 401, 124, 49);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(519, 401, 124, 49);
		contentPane.add(btnCancel);
	}
}
