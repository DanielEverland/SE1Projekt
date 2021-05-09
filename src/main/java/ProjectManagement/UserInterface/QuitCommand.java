package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Main;

public class QuitCommand implements UserCommand {

	@Override
	public void execute(List<String> args) {
		Main.getCurrentApplication().quit();
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
