package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import courierdm.CourierEntityManager;
import courierdm.IntersectionDBAO;
import courierpd.map.CityMap;
import courierpd.map.Intersection;

import java.awt.event.ActionListener;
import java.sql.Date;

import java.awt.event.ActionEvent;

public class MapConfigPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7902532799468135942L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MapConfigPanel(CourierMainFrame currentFrame, CityMap map) {
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
		
		JComboBox<Intersection> comboBox = new JComboBox<Intersection>();
		for(Intersection entry: IntersectionDBAO.listIntersections()){
			if(entry.getIsOpen()){
				comboBox.addItem(entry);
			}
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(296, 122, 108, 20);
		add(comboBox);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(296, 156, 108, 20);
		add(textField);
		textField.setColumns(10);
		
		
		JButton btnClose = new JButton("Close Intersection");
		btnClose.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Intersection intersectionToClose = (Intersection)comboBox.getSelectedItem();
				CourierEntityManager.getEntityManager().refresh(intersectionToClose);
				System.out.println("Intersection to close: "+intersectionToClose.getName());
				
				//close the selected intersection
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				userTransaction.begin();
	
				//set the closing date
				java.util.Date  closingDate = new java.util.Date();
				System.out.println("closingdate: "+closingDate.getMonth());
				intersectionToClose.setClosedDateStart(new Date(closingDate.getYear(), 
						closingDate.getMonth(),closingDate.getDate()));
				
				//set reopening date
				System.out.println("Content of the field: "+textField.getText());
				if(!textField.getText().equals("")){
					String[] reopeningDateComponents = textField.getText().split("/");
					intersectionToClose.setClosedDateEnd(new Date(Integer.parseInt(reopeningDateComponents[2])-1900, 
							Integer.parseInt(reopeningDateComponents[0])-1, Integer.parseInt(reopeningDateComponents[1])));
				}
				map.closeIntersection(intersectionToClose);
				
				userTransaction.commit(); // Finish the transaction with a commit
				
				//Refresh the page
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new MapConfigPanel(currentFrame,map));
				currentFrame.getContentPane().revalidate();

			}
		});
		btnClose.setBounds(184, 197, 218, 23);
		add(btnClose);
		
		DefaultListModel listModel = new DefaultListModel();
		for (Intersection i: IntersectionDBAO.listIntersections()){
			if(!i.getIsOpen()){
				listModel.addElement(i);
			}
		}
		
		JList closedIntList = new JList(listModel);
		closedIntList.setBounds(566, 120, 400, 300);
		add(closedIntList);
		
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
				Intersection intersection = (Intersection)closedIntList.getSelectedValue();
				if(intersection!=null){
					//reopen the selected intersection
					EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
					userTransaction.begin();//begin transaction
					
					IntersectionDBAO.findIntersectionById(intersection.getIntersectionId()).setClosedDateStart(null);
					IntersectionDBAO.findIntersectionById(intersection.getIntersectionId()).setClosedDateEnd(null);
					IntersectionDBAO.findIntersectionById(intersection.getIntersectionId()).setIsOpen(true);
					userTransaction.commit();//commit transaction
					
					//refresh the content pane
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new MapConfigPanel(currentFrame,map));
					currentFrame.getContentPane().revalidate();
				}else{
					System.out.println("You need to select an intersection to reopen");
				}
			}
		});
		btnReopen.setBounds(720, 435, 89, 23);
		add(btnReopen);
		
		JButton btnSave = new JButton("Finish");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id"));
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(420, 470, 89, 30);
		add(btnSave);

	}

}
