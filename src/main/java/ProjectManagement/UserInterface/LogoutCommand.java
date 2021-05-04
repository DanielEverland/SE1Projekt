package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;

public class LogoutCommand implements UserCommand {

	@Override
	public String getDisplayName() {
		return "Logout";
	}

	@Override
	public void execute(List<String> args) {
		Application.Get().signOut();
	}
	
}
