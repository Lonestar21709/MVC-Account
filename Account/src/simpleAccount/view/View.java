package simpleAccount.view;
import simpleAccount.model.Model;
import simpleAccount.controller.Controller;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}