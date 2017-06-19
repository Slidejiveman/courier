package courierui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import courierdm.EmployeeDBAO;

import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListModel;

import courierpd.core.User;

public class EmployeeManagementPanel<Employee> extends JPanel {

	/**
	 * 
	 */
	List<User> persistedEmployees = EmployeeDBAO.listUsers();
	JList<User> list;
	JButton btnAdd;
	JButton btnUpdate;
	JButton btnDelete;
	
	private static final long serialVersionUID = 1L;
	protected Employee Employee;
	/**
	 * Create the panel.
	 */
	public EmployeeManagementPanel(CourierMainFrame currentFrame) {
			setLayout(null);
		DefaultListModel listModel;
	
		listModel = new DefaultListModel();
		for(User employee: persistedEmployees)
			listModel.addElement(employee);
			
		list = new JList(listModel);
		list.setFont(new Font("Courier New", Font.PLAIN, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) 
				{
					
			          btnUpdate.setEnabled(true);
			          btnDelete.setEnabled(true);
				}
			});
		list.setBounds(287, 108, 442, 335);
		add(list);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddUpdateEmployeePanel(currentFrame, new User(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(284, 468, 97, 25);
		add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				// when we update the user, it shouldn't be a new User, but one from the list
				currentFrame.getContentPane().add(new AddUpdateEmployeePanel(currentFrame, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnUpdate.setBounds(454, 468, 97, 25);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EmployeeDeletePanel(currentFrame, list.getSelectedValue()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setBounds(632, 468, 97, 25);
		add(btnDelete);
	// This will disable Update and Delete if there is nothing in the Employee list
		
	
	//Employee List
		
	JList Employeelist = new JList();
		
		
		JLabel lblEmployeeList = new JLabel("Employee List");
		lblEmployeeList.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeList.setBounds(465, 41, 86, 16);
		add(lblEmployeeList);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(320, 79, 118, 16);
		add(lblName);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(620, 79, 97, 16);
		add(lblRole);

	}
}
