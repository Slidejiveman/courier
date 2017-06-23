package courierui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import courierdm.DeliveryTicketDBAO;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.other.DateParser;

public class CourierPerformanceReport extends JPanel {

	/**
	 * Create the panel.
	 */
	public CourierPerformanceReport(CourierMainFrame currentFrame, User activeUser, List<User> userList, boolean allCouriers, Date startDate, Date endDate) {
		List<DeliveryTicket> persistedDeliveryTickets = DeliveryTicketDBAO.listDeliveryTickets();
		String reportFinalString = "";
		String newline = "\n";
		reportFinalString = reportFinalString + reportFinalString.format("%-1s %-12s %-13s %-22s %-22s %-25s %s", "", "Date", 
				"Courier ID","Estimated Pickup Time", 
				"Actual Pickup Time", "Estimated Delivery Time", "Actual Delivery Time") + newline;
		setLayout(null);

		for(User user: userList)
		{
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if ((deliveryTicket.getCourier().getNumber() == user.getNumber()) && ((deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate))))
				{ 
					String bonusstr;
					if(deliveryTicket.getIsBonusEarned()){
						bonusstr = "Yes";
					}
					else {
						bonusstr = "No";
					}
					reportFinalString = reportFinalString + reportFinalString.format("%-1s %-17s %-13s %-22s %-22s %-25s %s", "", DateParser.printDate(deliveryTicket.getOrderDate()), 
							deliveryTicket.getCourier().getNumber(),DateParser.printTime(deliveryTicket.getRequestedPickUpTime()) , 
							DateParser.printTime(deliveryTicket.getActualPickUpTime()) , DateParser.printTime(deliveryTicket.getEstDeliveryTime()), DateParser.printTime(deliveryTicket.getActualDeliveryTime())) + newline;
				}
			}
		}
		
		String name = "";
		if(allCouriers)
		{
			name = "All Couriers";
		}
		else
		{
			for(User user: userList)
			{
				name = user.getName();
			}
		}
		String name2 = name;
		
		JTextArea textArea = new JTextArea(reportFinalString);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		textArea.setBounds(73, 110, 872, 324);
		add(textArea);
		
		JButton btnSaveAsPdf = new JButton("Save As PDF");
		btnSaveAsPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document document = new Document();
				try {
					final JFileChooser destinationChooser = new JFileChooser();
					destinationChooser.setDialogTitle("Choose the File Destination Folder");
					destinationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					destinationChooser.setAcceptAllFileFilterUsed(false); //disable the accept all files option
					destinationChooser.showOpenDialog(null);
					File destinationFolder = destinationChooser.getCurrentDirectory();
					String folderName = destinationFolder.getAbsolutePath();
					System.out.println("The selected path: "+folderName);
				
					PdfWriter.getInstance(document, new FileOutputStream(folderName+"/CourierPerformanceReportFor" + name2 + 
					                         ".pdf"));
					document.open();
					Paragraph paragraph = new Paragraph();
					paragraph.add(textArea.getText());
					document.add(paragraph);
					document.close();
				} catch (FileNotFoundException | DocumentException e) {
					e.printStackTrace();
				}
				
				//Add code to save the instructions as on a PDF file
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CourierPerformanceReport(currentFrame, activeUser, userList, allCouriers, startDate, endDate));
				currentFrame.revalidate();
			}
		});
		btnSaveAsPdf.setBounds(320, 445, 108, 23);
		add(btnSaveAsPdf);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ReportsMainPanel(currentFrame,activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(494, 445, 100, 23);
		add(btnCancel);
		
		JLabel lblCompanyPerformanceReport = new JLabel("Courier Performance Report");
		lblCompanyPerformanceReport.setBounds(337, 40, 216, 14);
		add(lblCompanyPerformanceReport);
		
		JLabel lblDate = new JLabel("Date: " + DateParser.printDate(startDate) + "-" + DateParser.printDate(endDate));
		lblDate.setBounds(64, 58, 344, 14);
		add(lblDate);
		
		JLabel lblCourier = new JLabel("Courier: " + name );
		lblCourier.setBounds(64, 85, 364, 14);
		add(lblCourier);
	}
}
