package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserInterface implements UserInterface {
	
	@Override
	public List<UserCommand> GetCommands() {
		List<UserCommand> commands = new ArrayList<UserCommand>();
		
		commands.add(new LoginCommand());
		
		commands.add(new QuitCommand());
		return commands;
	}	
}
