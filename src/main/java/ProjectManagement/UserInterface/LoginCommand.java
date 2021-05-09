package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;

public class LoginCommand implements UserCommand {

	private Controller controller;
	
	public LoginCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Login";
	}

	@Override
	public void execute(List<String> args) {
		if (args.size() < 1) {
			System.out.println("Missing username when logging in");
			return;
		}

		controller.getCurrentApplication().signIn(args.get(0));
	}

	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add("userName");
		}};
	}
}
