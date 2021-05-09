package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Main;

public class QuitCommand implements UserCommand {

	private Controller controller;
	
	public QuitCommand(Controller controller) {
		this.controller = controller;
	}
	
 	@Override
	public void execute(List<String> args) {
		controller.getCurrentApplication().quit();
	}

	@Override
	public String getDisplayName() {
		return "Quit";
	}
	
	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
