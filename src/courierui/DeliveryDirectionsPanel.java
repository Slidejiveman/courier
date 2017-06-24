package courierui;

import javax.swing.JPanel;


import javax.swing.JLabel;
import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.map.Route;
import courierpd.other.DateParser;

import java.io.*;

public class DeliveryDirectionsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14044467608032477L;

	/**
	 * Create the panel.
	 * @param deliveryRoute 
	 * @param currentFrame 
	 * @param deliveryTicket 
	 * @param deliveryTicket 
	 * @param userTransaction 
	 * @param estimatesTransaction 
	 * @param deliveryRoute2 
	 */
	public DeliveryDirectionsPanel(CourierMainFrame currentFrame, String deliveryRoute, Route route, User activeUser) {
		setLayout(null);
		
		JLabel lblPackageid = new JLabel("PackageId: ");
		lblPackageid.setBounds(44, 23, 92, 14);
		add(lblPackageid);
		
		JLabel lblCourier = new JLabel("Courier:");
		lblCourier.setBounds(44, 48, 74, 14);
		add(lblCourier);
		
		JLabel packageIdValue = new JLabel(Integer.toString(route.getCurrentOrder().getPackageID()));
		packageIdValue.setBounds(118, 23, 92, 14);
		add(packageIdValue);
		
		JLabel courierName = new JLabel(route.getCurrentOrder().getCourier().getName());
		courierName.setBounds(118, 48, 92, 14);
		add(courierName);
		
		JLabel lblSender = new JLabel("Sender:");
		lblSender.setBounds(410, 23, 58, 14);
		add(lblSender);
		
		JLabel senderName = new JLabel(route.getCurrentOrder().getPickUpClient().getName());
		senderName.setBounds(478, 23, 106, 14);
		add(senderName);
		
		JLabel lblReceiver = new JLabel("Receiver:");
		lblReceiver.setBounds(410, 48, 58, 14);
		add(lblReceiver);
		
		JLabel receiverName = new JLabel(route.getCurrentOrder().getDeliveryClient().getName());
		receiverName.setBounds(478, 48, 106, 14);
		add(receiverName);
		
		JLabel lblEstBlocks = new JLabel("Est. Blocks:");
		lblEstBlocks.setBounds(699, 23, 74, 14);
		add(lblEstBlocks);
		
		JLabel lblEstDepartureTime = new JLabel("Est. Departure Time: ");
		lblEstDepartureTime.setBounds(699, 62, 137, 14);
		add(lblEstDepartureTime);
		
		JLabel lblEstDeliveryTime = new JLabel("Est. Delivery Time:");
		lblEstDeliveryTime.setBounds(699, 102, 115, 14);
		add(lblEstDeliveryTime);
		
		JLabel estBlocksValue = new JLabel(Integer.toString(route.estimateBlocks()));
		estBlocksValue.setBounds(846, 23, 103, 14);
		add(estBlocksValue);
		
		JLabel estDepartureTimeValue = new JLabel(DateParser.printTime(route.estimateDepartureTime()));
		estDepartureTimeValue.setBounds(846, 48, 103, 43);
		add(estDepartureTimeValue);
		
		JLabel estDeliveryTimeValue = new JLabel(DateParser.printTime(route.estimateDeliveryTime()));
		estDeliveryTimeValue.setBounds(846, 102, 103, 14);
		add(estDeliveryTimeValue);
		
		JLabel lblSpecialInstructions = new JLabel("Special Instructions: ");
		lblSpecialInstructions.setBounds(44, 102, 184, 14);
		add(lblSpecialInstructions);
		
		JTextArea directionstextArea = new JTextArea(deliveryRoute.toString());
		directionstextArea.setAutoscrolls(true);
		directionstextArea.setBounds(44, 127, 905, 333);
		directionstextArea.setEditable(false);
		add(directionstextArea);
		
		JScrollBar verticalScrollBar = new JScrollBar();
		verticalScrollBar.setBounds(932, 127, 17, 333);
		add(verticalScrollBar);
		
		JButton btnSave = new JButton("Save As Pdf");
		btnSave.addActionListener(new ActionListener() {
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
				
					PdfWriter.getInstance(document, new FileOutputStream(folderName+"/directionsForTicketNo" + 
					                       route.getCurrentOrder().getPackageID() + ".pdf"));
					document.setPageSize(PageSize.A5); 
			        //document.setMargins(36, 36, 36, 36);
			        document.setMarginMirroring(true);
					document.open();
					
					Paragraph titleParagraph = new Paragraph(); // Adding the title paragraph
					titleParagraph.setLeading(1.0f, 1.0f);
					titleParagraph.add("Delivery Directions to "
					+route.getCurrentOrder().getDeliveryClient().getName()+"'s Location: "
							+route.getCurrentOrder().getDeliveryClient().getLocation()+"\n");
					
					Paragraph paragraph = new Paragraph();
					paragraph.setLeading(0.0f, 1.0f);
					paragraph.add(directionstextArea.getText());
					paragraph.setAlignment(Element.ALIGN_LEFT);
					
					paragraph.setIndentationLeft(50);
					titleParagraph.setAlignment(Element.ALIGN_CENTER);
					titleParagraph.setSpacingAfter(0);
					paragraph.setSpacingBefore(0);
					document.add(titleParagraph);
					document.add(paragraph);
					document.close();
				} catch (FileNotFoundException | DocumentException e) {
					e.printStackTrace();
				}
				
				//Add code to save the instructions as on a PDF file
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new DeliveryTicketListPanel(currentFrame, "Package Id", activeUser));
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(672, 496, 164, 23);
		add(btnSave);
		JButton btnBackToTicket = new JButton("Back To Ticket");
		btnBackToTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new AddUpdateDeliveryTicketPanel(currentFrame, route.getCurrentOrder(),activeUser, false));
				currentFrame.revalidate();
			}
		});
		btnBackToTicket.setBounds(187, 496, 129, 23);
		add(btnBackToTicket);

	}
}
