package ProjectManagement.UserInterface;

import java.util.List;

public class GenericArgumentsCommand implements UserCommand {

	private String displayName;
	private GenericArgumentsFunctional response;
	
	GenericArgumentsCommand(String displayName, GenericArgumentsFunctional response) {
		this.displayName = displayName;
		this.response = response;
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void execute(List<String> args) {
		response.Invoke(args);
	}

}
