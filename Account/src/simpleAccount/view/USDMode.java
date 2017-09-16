package simpleAccount.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;

import simpleAccount.controller.SimpleAccountController;
import simpleAccount.model.Accounts;
import simpleAccount.model.ModelEvent;
import simpleAccount.model.SimpleAccountModel;

//class for window for editing in USD
public class USDMode extends JFrameView {
	DecimalFormat decFormat = new DecimalFormat("##.00");
	public static final String DISMISS = "Dismiss";
	public static final String  WITHDRAW_USD = "Withdraw USD";
	public static final String DEPOSIT_USD = "Deposit USD";
	JFormattedTextField amountField;
	Accounts accounts;
	JLabel jl,j2;
	public double amount = 0;

	public USDMode(SimpleAccountModel model, SimpleAccountController controller, Accounts accounts) {
		super(model, controller);
		this.accounts = accounts;
		this.setTitle(accounts.getID() + " " + accounts.getName() + " {USD}");
		this.setPreferredSize(new Dimension(500,150));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Handler handler = new Handler();
		NumberFormat format = NumberFormat.getNumberInstance();

		amountField = new JFormattedTextField(format);
		amountField.setValue(0.00);
		amountField.setColumns(30);
		amountField.addActionListener(handler);
		jl = new JLabel("Available funds: $ " + decFormat.format(accounts.getBalance()));
		j2 = new JLabel("Enter amount in  USDs: ");

		JPanel textPanel = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton dismiss = new JButton(DISMISS);
		dismiss.setBackground(Color.white);
		dismiss.addActionListener(handler);
		JButton withdraw = new JButton(WITHDRAW_USD);
		withdraw.setBackground(Color.white);
		withdraw.addActionListener(handler);
		JButton deposit = new JButton(DEPOSIT_USD);
		deposit.setBackground(Color.white);
		deposit.addActionListener(handler);

		textPanel.add(jl, null);
		textPanel.add(j2, null);
		entryPanel.add((amountField), null);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(deposit, null);
		buttonPanel.add(withdraw, null);
		buttonPanel.add(dismiss, null);
		this.getContentPane().add(textPanel, BorderLayout.NORTH);
		this.getContentPane().add(entryPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		this.setAlwaysOnTop(true);
		pack();
	}

	public void modelChanged(ModelEvent event) {
		jl.setText("Balance: " + decFormat.format(event.getAmount()));
		amountField.setValue(0.00);
	}

	public class Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == DISMISS)
				dispose();
			else
			{
				amount = Double.parseDouble(amountField.getValue().toString());
				((SimpleAccountController) getController()).operation(e.getActionCommand(), accounts, amount);
			}
		}
	}
}