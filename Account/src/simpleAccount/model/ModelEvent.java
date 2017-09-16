package simpleAccount.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private double balance;
	public ModelEvent(Object obj, int id, String message, double balance){
		super(obj, id, message);
		this.balance = balance;
	}
	public double getAmount(){return balance;}
}