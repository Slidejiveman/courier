package courierui;

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
		String reportFinalString = "";
		String newline = "\n";
		
		setLayout(null);
		
		JLabel lblClient = new JLabel("Client: ");
		lblClient.setBounds(69, 35, 46, 14);
		add(lblClient);
		
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
		
		JLabel lblInsertName = new JLabel(name);
		lblInsertName.setBounds(125, 35, 189, 14);
		add(lblInsertName);
		
		for(Client client: clientList)
		{ 
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if(((deliveryTicket.getPickUpClient() == client && deliveryTicket.getIsBillPickUp()) || (deliveryTicket.getDeliveryClient() == client && !deliveryTicket.getIsBillPickUp())) && (deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate)))
				{
					reportFinalString = reportFinalString + DateParser.printDate(deliveryTicket.getOrderDate()) + " " + deliveryTicket.getPackageID() + " " + DateParser.printTime(deliveryTicket.getActualPickUpTime()) + " " + DateParser.printTime(deliveryTicket.getActualDeliveryTime()) + " " + deliveryTicket.getEstPrice() + newline;
				}
			}
		}
		JTextArea textArea = new JTextArea(reportFinalString);
		textArea.setBounds(57, 92, 498, 258);
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
				
					PdfWriter.getInstance(document, new FileOutputStream(folderName+"/billingReportFor" + 
					                       name2 + ".pdf"));
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
				currentFrame.getContentPane().add(new BillingReportPanel(currentFrame, activeUser, clientList, allClients, startDate, endDate));
				currentFrame.revalidate();
			}
		});
		btnSaveAsPdf.setBounds(125, 372, 123, 23);
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
		btnCancel.setBounds(358, 372, 114, 23);
		add(btnCancel);
		
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(57, 67, 46, 14);
		add(lblDate);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setBounds(135, 67, 78, 14);
		add(lblPackageId);
		
		JLabel lblPickupTime = new JLabel("Pick-up Time");
		lblPickupTime.setBounds(235, 67, 100, 14);
		add(lblPickupTime);
		
		JLabel lblDeliveryTime = new JLabel("Delivery Time");
		lblDeliveryTime.setBounds(358, 67, 100, 14);
		add(lblDeliveryTime);
		
		JLabel lblBillingRate = new JLabel("Billing Rate");
		lblBillingRate.setBounds(468, 67, 87, 14);
		add(lblBillingRate);
	}
}
