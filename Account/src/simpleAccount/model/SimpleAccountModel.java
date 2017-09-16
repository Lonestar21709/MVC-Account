package simpleAccount.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import simpleAccount.view.SimpleAccountView;

public class SimpleAccountModel extends AbstractModel {
	DecimalFormat df = new DecimalFormat("#####.00");

	public synchronized void withdrawUSD(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance -= amount;
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void withdrawEURO(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance -= (amount/.88);	
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}	
	}
	
	public synchronized void withdrawYUAN(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance -= (amount/6.47);
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}

	public synchronized void depositUSD(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance += amount;
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void depositEURO(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance += (amount/.88);
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void depositYUAN(Accounts accounts, double amount)
	{
		double balance = accounts.getBalance();
		balance += (amount/6.47);
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public void save()
	{
		try
		{	
			PrintWriter pw = new PrintWriter("./input/"+ SimpleAccountView.fileName);
			for(Accounts accounts : SimpleAccountView.accountss)
			{
				pw.write(accounts.getName() + "\n");
				pw.write(accounts.getID() + "\n");
				pw.write(df.format((accounts.getBalance())) + "\n");
				pw.write("\n");
			}
			pw.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Unable to open file: " + e);
		}
		finally
		{
			System.out.println("File has been saved.");
		}
	}
	
	public void exit()
	{
		save(); //to save to file before exiting program
		System.exit(0);
	}
}
