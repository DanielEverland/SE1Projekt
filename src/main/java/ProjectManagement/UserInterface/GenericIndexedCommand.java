package ProjectManagement.UserInterface;

import java.util.List;

public class GenericIndexedCommand implements UserCommand {

	private String displayName;
	private int index;
	private GenericIndexedFunctional response;
	
	GenericIndexedCommand(String displayName, int index, GenericIndexedFunctional response) {
		this.displayName = displayName;
		this.index = index;
		this.response = response;
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void execute(List<String> args) {
		response.Invoke(index);
	}

	@Override
	public List<String> getParameterNames() {
		return null;
	}
}
