package courierui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import courierdm.BusinessParametersDBAO;
import courierdm.ClientDBAO;
import courierdm.CourierEntityManager;
import courierpd.core.BusinessParameters;
import courierpd.core.Client;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateBusinessParametersPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public UpdateBusinessParametersPanel(CourierMainFrame currentFrame) {
		List<BusinessParameters> persistedBusinessParameters = BusinessParametersDBAO.listBusinessParameterss();
		BusinessParameters businessParameters = BusinessParametersDBAO.listBusinessParameterss().get(0);
		
		setLayout(null);
		
		JLabel lblBonusParameters = new JLabel("Bonus Parameters");
		lblBonusParameters.setBounds(154, 120, 105, 14);
		add(lblBonusParameters);
		
		JLabel lblBonusWindow = new JLabel("Bonus Window:");
		lblBonusWindow.setBounds(110, 153, 86, 14);
		add(lblBonusWindow);
		
		JLabel lblBonusAmount = new JLabel("Bonus Amount:");
		lblBonusAmount.setBounds(109, 194, 87, 14);
		add(lblBonusAmount);
		
		JLabel lblDeliveryTime = new JLabel("Delivery Time");
		lblDeliveryTime.setBounds(154, 306, 86, 14);
		add(lblDeliveryTime);
		
		JLabel lblPickupDelay = new JLabel("Pick-up Delay:");
		lblPickupDelay.setBounds(110, 341, 86, 14);
		add(lblPickupDelay);
		
		JLabel lblDeliveryDelay = new JLabel("Delivery Delay:");
		lblDeliveryDelay.setBounds(110, 387, 86, 14);
		add(lblDeliveryDelay);
		
		JLabel lblBillingParameters = new JLabel("Billing Parameters");
		lblBillingParameters.setBounds(550, 120, 105, 14);
		add(lblBillingParameters);
		
		JLabel lblBase = new JLabel("Base:");
		lblBase.setBounds(525, 153, 46, 14);
		add(lblBase);
		
		JLabel lblPerBlock = new JLabel("Per Block:");
		lblPerBlock.setBounds(592, 153, 63, 14);
		add(lblPerBlock);
		
		JLabel lblCourierInfo = new JLabel("Courier Info");
		lblCourierInfo.setBounds(550, 306, 77, 14);
		add(lblCourierInfo);
		
		JLabel lblAverageSpeed = new JLabel("Average Speed:");
		lblAverageSpeed.setBounds(491, 362, 86, 14);
		add(lblAverageSpeed);
		
		textField = new JTextField(Integer.toString(businessParameters.getBonusWindow())); //Bonus Window
		textField.setBounds(200, 150, 93, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(Double.toString(businessParameters.getBonusPaymentAmt())); //Bonus Amount
		textField_1.setBounds(200, 191, 93, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(Integer.toString(businessParameters.getPickUpDelay())); //Pick-up Delay
		textField_2.setColumns(10);
		textField_2.setBounds(200, 338, 93, 20);
		add(textField_2);
		
		textField_3 = new JTextField(Integer.toString(businessParameters.getDeliveryDelay())); //Delivery Delay
		textField_3.setColumns(10);
		textField_3.setBounds(200, 384, 93, 20);
		add(textField_3);
		
		textField_4 = new JTextField(Double.toString(businessParameters.getAvgCourierSpeed())); //Average Speed
		textField_4.setColumns(10);
		textField_4.setBounds(587, 359, 93, 20);
		add(textField_4);
		
		textField_5 = new JTextField(Double.toString(businessParameters.getBillingBase())); //Billing Base
		textField_5.setColumns(10);
		textField_5.setBounds(525, 191, 46, 20);
		add(textField_5);
		
		textField_6 = new JTextField(Double.toString(businessParameters.getBillingRate())); //Billing Per Blocks
		textField_6.setColumns(10);
		textField_6.setBounds(592, 191, 46, 20);
		add(textField_6);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityTransaction userTransaction = CourierEntityManager.getEntityManager().getTransaction();
				userTransaction.begin();
				if(textField.getText() != null && Integer.parseInt(textField.getText()) >= 0) {
					businessParameters.setBonusWindow(Integer.parseInt(textField.getText()));
				}
				if(textField_1.getText() != null && Double.parseDouble(textField_1.getText()) >= 0) {
					businessParameters.setBonusPaymentAmt(Double.parseDouble(textField_1.getText()));
				}
				if(textField_2.getText() != null && Integer.parseInt(textField_2.getText()) >= 0) {
					businessParameters.setPickUpDelay(Integer.parseInt(textField_2.getText()));
				}
				if(textField_3.getText() != null && Integer.parseInt(textField_3.getText()) >= 0) {
					businessParameters.setDeliveryDelay(Integer.parseInt(textField_3.getText()));
				}
				if(textField_4.getText() != null && Double.parseDouble(textField_4.getText()) >= 0) {
					businessParameters.setAvgCourierSpeed(Double.parseDouble(textField_4.getText()));
				}
				if(textField_5.getText() != null && Double.parseDouble(textField_5.getText()) >= 0) {
					businessParameters.setBillingBase(Double.parseDouble(textField_5.getText()));
				}
				if(textField_6.getText() != null && Double.parseDouble(textField_6.getText()) >= 0) {
					businessParameters.setBillingRate(Double.parseDouble(textField_6.getText()));
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpdateBusinessParametersPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(262, 467, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpdateBusinessParametersPanel(currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(432, 467, 89, 23);
		add(btnCancel);

	}

}
