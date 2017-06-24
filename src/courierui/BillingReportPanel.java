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
import courierpd.core.Client;
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.other.DateParser;

public class BillingReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BillingReportPanel(CourierMainFrame currentFrame, User activeUser, List<Client> clientList, boolean allClients, Date startDate, Date endDate) {
		List<DeliveryTicket> persistedDeliveryTickets = DeliveryTicketDBAO.listDeliveryTickets();
		String newline = "\n";
		String reportFinalString = "";
		
		String name = "";
		if(allClients)
		{
			name = "All Clients";
		}
		else
		{
			for(Client client: clientList)
			{
				name = client.getName();
			}
		}
		String name2 = name;
		
		reportFinalString = reportFinalString + reportFinalString.format("%-9s %s ", "Client: ", name2) + newline;
		reportFinalString = reportFinalString + reportFinalString.format("%-10s %-11s %-12s %-14s %s", "Date", 
				"Package ID","Pickup Time", 
				"Delivery Time","Price") + newline;
		
		setLayout(null);
		
		for(Client client: clientList)
		{ 
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if(((deliveryTicket.getPickUpClient() == client && deliveryTicket.getIsBillPickUp()) || (deliveryTicket.getDeliveryClient() == client && !deliveryTicket.getIsBillPickUp())) && (deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate)))
				{
					reportFinalString = reportFinalString + reportFinalString.format("%-13s %-9s %-13s %-13s %s", DateParser.printDate(deliveryTicket.getOrderDate()), 
							deliveryTicket.getPackageID(),DateParser.printTime(deliveryTicket.getActualPickUpTime()), 
							DateParser.printTime(deliveryTicket.getActualDeliveryTime()),deliveryTicket.getEstPrice()) + newline;
				}
			}
		}
		JTextArea textArea = new JTextArea(reportFinalString);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		textArea.setBounds(184, 97, 498, 315);
		add(textArea);
		
		JButton btnSaveAsPdf = new JButton("Save as PDF");
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
				
					PdfWriter.getInstance(document, new FileOutputStream(folderName+"/BillingReportFor" + 
					                       name2 + ".pdf"));
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
				currentFrame.getContentPane().add(new BillingReportPanel(currentFrame, activeUser, clientList, allClients, startDate, endDate));
				currentFrame.revalidate();
			}
		});
		btnSaveAsPdf.setBounds(252, 434, 123, 23);
		add(btnSaveAsPdf);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new BillingReportOptionsPanel(currentFrame, activeUser));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(485, 434, 114, 23);
		add(btnCancel);
	}
}
