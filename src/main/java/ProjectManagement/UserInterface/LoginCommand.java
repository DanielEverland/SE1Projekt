package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;

public class LoginCommand implements UserCommand {

	@Override
	public String getDisplayName() {
		return "Login";
	}

	@Override
	public void execute(List<String> args) {
		if(args.size() < 1)
		{
			System.out.println("Missing username when logging in");
			return;
		}
		
		Application.Get().signIn(args.get(0));
	}

}
