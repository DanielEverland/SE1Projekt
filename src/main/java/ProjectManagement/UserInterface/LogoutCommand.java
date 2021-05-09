package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;

public class LogoutCommand implements UserCommand {

	private Controller controller;
	
	public LogoutCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Logout";
	}

	@Override
	public void execute(List<String> args) {
		controller.getCurrentApplication().signOut();
	}

	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
