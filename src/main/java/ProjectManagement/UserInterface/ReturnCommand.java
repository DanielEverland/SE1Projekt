package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Main;

public class ReturnCommand implements UserCommand {

	@Override
	public String getDisplayName() {
		return "Return";
	}

	@Override
	public void execute(List<String> args) {
		Main.setPreviousUserInterface();
	}
	
	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
