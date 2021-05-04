package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;

public class QuitCommand implements UserCommand {

	@Override
	public void execute(List<String> args) {
		Application.Get().quit();
	}

	@Override
	public String getDisplayName() {
		return "Quit";
	}
}
