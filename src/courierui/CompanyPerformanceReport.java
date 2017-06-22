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
import courierpd.core.DeliveryTicket;
import courierpd.core.User;
import courierpd.other.DateParser;

public class CompanyPerformanceReport extends JPanel {

	/**
	 * Create the panel.
	 */
	public CompanyPerformanceReport(CourierMainFrame currentFrame, User activeUser, List<User> userList, Date startDate, Date endDate) {
		List<DeliveryTicket> persistedDeliveryTickets = DeliveryTicketDBAO.listDeliveryTickets();
		String reportFinalString = "";
		String newline = "\n";
		setLayout(null);

		//DefaultListModel listModel = new DefaultListModel();
		for(User user: userList)
		{
			for(DeliveryTicket deliveryTicket: persistedDeliveryTickets)
			{
				if ((deliveryTicket.getCourier().getNumber() == user.getNumber()) && (deliveryTicket.getOrderDate().after(startDate) && deliveryTicket.getOrderDate().before(endDate))) 
				{ 
					Date today = new Date();
					reportFinalString = reportFinalString + deliveryTicket.getCourier().getNumber() + "        " + deliveryTicket.getPackageID() + "       " + DateParser.printDate(deliveryTicket.getOrderDate()) + "     " + DateParser.printDate(today) +"      "+ DateParser.printTime(deliveryTicket.getEstDeliveryTime()) + "      " + DateParser.printTime(deliveryTicket.getActualDeliveryTime()) + newline;
				}
			}
		}
		
		JTextArea textArea = new JTextArea(reportFinalString);
		textArea.setBounds(38, 135, 778, 282);
		add(textArea);
		
		JLabel lblCouriersId = new JLabel("Couriers ID");
		lblCouriersId.setBounds(56, 110, 78, 14);
		add(lblCouriersId);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setBounds(144, 110, 70, 14);
		add(lblPackageId);
		
		JLabel lblDateOfThe = new JLabel("Date of the Delivery");
		lblDateOfThe.setBounds(247, 110, 123, 14);
		add(lblDateOfThe);
		
		JLabel lblDateOfThe_1 = new JLabel("Date of the Report");
		lblDateOfThe_1.setBounds(380, 110, 108, 14);
		add(lblDateOfThe_1);
		
		JLabel lblReportedDeliveryTime = new JLabel("Reported Delivery Time");
		lblReportedDeliveryTime.setBounds(522, 110, 137, 14);
		add(lblReportedDeliveryTime);
		
		JLabel lblActualDeliveryTime = new JLabel("Actual Delivery Time");
		lblActualDeliveryTime.setBounds(689, 110, 127, 14);
		add(lblActualDeliveryTime);
		
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
				
					PdfWriter.getInstance(document, new FileOutputStream(folderName+"/CompanyPerformanceReport" + 
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
				currentFrame.getContentPane().add(new CompanyPerformanceReport(currentFrame, activeUser, userList, startDate, endDate));
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
		
		JLabel lblCompanyPerformanceReport = new JLabel("Company Performance Report");
		lblCompanyPerformanceReport.setBounds(337, 40, 216, 14);
		add(lblCompanyPerformanceReport);
		
		

	}
}
