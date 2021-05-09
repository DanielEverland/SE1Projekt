package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class GenericArgumentsCommand implements UserCommand {

	private String displayName;
	private GenericArgumentsFunctional response;
	private List<String> usedArguments;
	
	public GenericArgumentsCommand(String displayName, GenericArgumentsFunctional response, List<String> usedArguments) {
		this.usedArguments = usedArguments;
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

	@Override
	public List<String> getParameterNames() {
		return usedArguments;
	}
}
