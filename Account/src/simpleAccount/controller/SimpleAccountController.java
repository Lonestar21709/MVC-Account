package simpleAccount.controller;
import simpleAccount.view.*;
import simpleAccount.model.SimpleAccountModel;
import simpleAccount.model.Accounts;

public class SimpleAccountController extends AbstractController {

	public SimpleAccountController(){
		setModel(new SimpleAccountModel());
		setView(new SimpleAccountView((SimpleAccountModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}
	public void operation(String option, Accounts accounts, double amount){
		if(option.equals(SimpleAccountView.USD))
		{
			setView(new USDMode((SimpleAccountModel)getModel(), this, accounts));
		}
		else if(option.equals(SimpleAccountView.EURO))
		{
			setView(new EUROMode((SimpleAccountModel)getModel(), this, accounts));
		}
		else if(option.equals(SimpleAccountView.YUAN))
		{
			setView(new YUANMode((SimpleAccountModel)getModel(), this, accounts));
		}
		else if(option.equals(USDMode.WITHDRAW_USD))
		{
			((SimpleAccountModel)getModel()).withdrawUSD(accounts, amount);
		}
		else if(option.equals(USDMode.DEPOSIT_USD))
		{
			((SimpleAccountModel)getModel()).depositUSD(accounts, amount);
		}
		else if(option.equals(EUROMode.WITHDRAW_EURO))
		{
			((SimpleAccountModel)getModel()).withdrawEURO(accounts, amount);
		}
		else if(option.equals(EUROMode.DEPOSIT_EURO))
		{
			((SimpleAccountModel)getModel()).depositEURO(accounts, amount);
		}
		else if(option.equals(YUANMode.WITHDRAW_YUAN))
		{
			((SimpleAccountModel)getModel()).withdrawYUAN(accounts, amount);
		}
		else if(option.equals(YUANMode.DEPOSIT_YUAN))
		{
			((SimpleAccountModel)getModel()).depositYUAN(accounts, amount);
		}
		else if(option.equals(SimpleAccountView.SAVE))
		{
			((SimpleAccountModel)getModel()).save();
		}
		else if(option.equals(SimpleAccountView.EXIT))
		{
			((SimpleAccountModel)getModel()).exit();
		}
	}
}