package courierui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import courierdm.DeliveryTicketDBAO;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.enums.TicketStatus;
import courierpd.other.DateParser;

public class CourierPerformanceReport extends JPanel {

	/**
	 * Create the panel.
	 */
	public CourierPerformanceReport(CourierMainFrame currentFrame, User activeUser, List<User> userList, boolean allCouriers, Date startDate, Date endDate) {
		List<DeliveryTicket> persistedDeliveryTickets = DeliveryTicketDBAO.listDeliveryTickets();
		String reportFinalString = "";
		String newline = "\n";
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
		reportFinalString = reportFinalString + reportFinalString.format("%-7s %s", "Date: ", DateParser.printDate(startDate) + "-" + DateParser.printDate(endDate)) + newline;
		reportFinalString = reportFinalString + reportFinalString.format("%-9s %s", "Courier: ", name2) + newline + newline;
		reportFinalString = reportFinalString + reportFinalString.format("%-1s %-9s %-11s %-16s %-12s %-14s %14s %8s", "", "Date", 
				"Courier ID","Est Pickup Time", 
				"Pickup Time", "Est Delivery Time", "Delivery Time", "Bonus") + newline;
		setLayout(null);

		for(User user: userList)
		{
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if ((deliveryTicket.getCourier().getNumber() == user.getNumber()) && ((deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate))) && (deliveryTicket.getStatus() == TicketStatus.Closed))
				{ 
					String bonusstr;
					if(deliveryTicket.getIsBonusEarned()){
						bonusstr = "Yes";
					}
					else {
						bonusstr = "No";
					}
					reportFinalString = reportFinalString + reportFinalString.format("%-1s %-13s %-10s %-14s %-15s %-16s %-15s %s", "", DateParser.printDate(deliveryTicket.getOrderDate()), 
							deliveryTicket.getCourier().getNumber(),DateParser.printTime(deliveryTicket.getRequestedPickUpTime()) , 
							DateParser.printTime(deliveryTicket.getActualPickUpTime()) , DateParser.printTime(deliveryTicket.getEstDeliveryTime()), DateParser.printTime(deliveryTicket.getActualDeliveryTime()), bonusstr) + newline;
				}
			}
		}
		
		JTextArea textArea = new JTextArea(reportFinalString);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		textArea.setBounds(73, 89, 872, 345);
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
					BaseFont bf = null;
					try {
						bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					document.open();
					Paragraph paragraph = new Paragraph();
					paragraph.setFont(new com.itextpdf.text.Font(bf, 8));
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
	}
}
