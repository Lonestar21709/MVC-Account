package simpleAccount.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import simpleAccount.controller.SimpleAccountController;
import simpleAccount.model.SimpleAccountModel;
import simpleAccount.model.Accounts;
import simpleAccount.model.ModelEvent;


/*
Cesar Garza
Homework 3 - Simple Account
4/16/2017
 */
public class SimpleAccountView extends JFrameView {
	public static final String USD = "Edit in USD";
	public static final String EURO = "Edit in EURO";
	public static final String YUAN = "Edit in YUAN";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static String fileName;
	//combo box that will hold the accountss
	public JComboBox<String> hozComboBox;
	public int index = 0;
	//array that will contain the accountss
	public static ArrayList<Accounts> accountss = new ArrayList<Accounts>();

	//Constructor that initializes window along with buttons and listeners
	public SimpleAccountView(SimpleAccountModel model, SimpleAccountController controller) {
		super(model, controller);

		//
		hozComboBox = new JComboBox<>();
		//add accountss to the combo box
		for(Accounts accounts : accountss)
			hozComboBox.addItem(accounts.getID() + " " + accounts.getName());
		hozComboBox.setPreferredSize(new Dimension(150,20));
//		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
//		panel.add(Box.createHorizontalGlue());
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		panel.add(hozComboBox);
		this.getContentPane().add(hozComboBox, BorderLayout.SOUTH);

		//set the size and title  of the main menu
		this.setTitle("Main Menu");
		this.setPreferredSize(new Dimension(350, 250));
		this.setBackground(Color.black);
		this.setVisible(true);
		this.setAlwaysOnTop( true );
		//program terminates when window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//

		//panel for usd, euro, and yuan buttons
		int	maxGap = 20;
		JPanel buttonPanel = new JPanel();
		Dimension buttonsize = buttonPanel.getPreferredSize();
		buttonPanel.setPreferredSize(new Dimension((int)(buttonsize.getWidth() * 12.5)+maxGap,
				(int)(buttonsize.getHeight() * 5.5)+ maxGap * 2));
		Handler handler = new Handler();
		hozComboBox.addActionListener(handler);
		//currency buttons and listeners
		JButton setUSD = new JButton(USD);
		setUSD.setBackground(Color.white);
		setUSD.addActionListener(handler);
		JButton setEuro = new JButton(EURO);
		setEuro.setBackground(Color.white);
		setEuro.addActionListener(handler);
		JButton setYuan = new JButton(YUAN);
		setYuan.setBackground(Color.white);
		setYuan.addActionListener(handler);
		//set the layout of the buttons
		buttonPanel.setLayout(new GridLayout(3, 1));
		this.getContentPane().add(buttonPanel, BorderLayout.WEST);
		buttonPanel.add(setUSD, null);
		buttonPanel.add(setEuro, null);
		buttonPanel.add(setYuan, null);

		//panel for save and exit buttons, along with listeners
		JPanel savePanel = new JPanel();
		savePanel.setPreferredSize(new Dimension((int)(buttonsize.getWidth() * 12.5)+maxGap,
				(int)(buttonsize.getHeight() * 2.5)+ maxGap * 2));
		//save and exit buttons and listeners
		JButton exit = new JButton(EXIT);
		exit.setBackground(Color.white);
		exit.addActionListener(handler);
		JButton save = new JButton(SAVE);
		save.setBackground(Color.white);
		save.addActionListener(handler);
		//set the layout
		savePanel.setLayout(new GridLayout(2,1));
		this.getContentPane().add(savePanel, BorderLayout.EAST);
		savePanel.add(save,  null);
		savePanel.add(exit, null);

		pack();
	}

	//implement the necessary event handling code
	public void modelChanged(ModelEvent event) {			//function to update model
		accountss.get(index).setBalance(event.getAmount());
	}
	//inner classes for event handling
	public class Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == hozComboBox)			//updates index of accounts selected, when a selection is made
			{
				index = hozComboBox.getSelectedIndex();
			}
			else
				((SimpleAccountController) getController()).operation(e.getActionCommand(),
						accountss.get(index), 0);
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter \"input.txt\" to start the program. \n" +
				">> ");

		Scanner sc = new Scanner(System.in);
		fileName = sc.nextLine();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("./input/" + fileName));
			String name;
			int accountNumber;
			double balance;
			try
			{
				//reads each line
				while((name = br.readLine()) != null)
				{
					accountNumber = Integer.parseInt(br.readLine());
					balance = Double.parseDouble(br.readLine());
					br.readLine();
					accountss.add(new Accounts(name, accountNumber, balance));
				}
				new SimpleAccountController();
			}
			catch(NumberFormatException e)
			{
				System.err.println("Error, cannot import accountss " + e);
			}
			finally
			{
				br.close();
			}
		}
		catch(IOException e)
		{
			System.err.println("Error wrong file:  " + e);
		}
		sc.close();	//close scanner
	}
}