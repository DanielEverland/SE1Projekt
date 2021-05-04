package ProjectManagement.UserInterface;

import java.util.List;

public class GenericCommand implements UserCommand {

	String displayName;
	GenericFunctional response;
	
	GenericCommand(String displayName, GenericFunctional response) {
		this.displayName = displayName;
		this.response = response;
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void execute(List<String> args) {
		response.Invoke();
	}

}
