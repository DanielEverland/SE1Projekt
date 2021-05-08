package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;

public class LogoutCommand implements UserCommand {

	@Override
	public String getDisplayName() {
		return "Logout";
	}

	@Override
	public void execute(List<String> args) {
		Main.getCurrentApplication().signOut();
	}

	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
