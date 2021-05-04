package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.ErrorMessageHandler;

public class GenericInputCommand implements UserCommand {

	private String displayName;
	private GenericInputFunctional response;
	
	GenericInputCommand(String displayName, GenericInputFunctional response) {
		this.displayName = displayName;
		this.response = response;
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void execute(List<String> args) {
		if(args.size() < 1) {
			ErrorMessageHandler.addErrorMessage("No arguments passed as input");
			return;
		}
		
		response.Invoke(args.get(0));
	}

}
