package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.ErrorMessageHandler;

public class GenericInputCommand implements UserCommand {

	private String displayName;
	private String inputName;
	private GenericInputFunctional response;	
	
	GenericInputCommand(String displayName, String inputName, GenericInputFunctional response) {
		this.displayName = displayName;
		this.response = response;
		this.inputName = inputName;
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

	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add(inputName);
		}};
	}
}
