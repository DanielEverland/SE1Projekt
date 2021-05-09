package ProjectManagement.UserInterface;

import java.util.List;

public class ReturnCommand implements UserCommand {

	private Controller controller;
	
	public ReturnCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Return";
	}

	@Override
	public void execute(List<String> args) {
		controller.setPreviousUserInterface();
	}
	
	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
