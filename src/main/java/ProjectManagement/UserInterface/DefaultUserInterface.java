package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Application;

public class DefaultUserInterface implements UserInterface {

	@Override
	public List<UserCommand> GetCommands() {
		List<UserCommand> commands = new ArrayList<UserCommand>();

		if (Application.Get().isSignedIn()) {
			commands.add(new LogoutCommand());
		} else {
			commands.add(new LoginCommand());
		}

		commands.add(new QuitCommand());
		return commands;
	}
}
