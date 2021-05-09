package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.TaskConstructorInfo;

public class CreateTaskUserInterface extends CreateActivityUserInterface {

	private TaskConstructorInfo information;
	
	CreateTaskUserInterface(UserInterface parent) {
		super(parent);
		
		information = new TaskConstructorInfo();
	}
	
	@Override
	public String getDescription() {
		return super.getDescription() + String.format("Description: %s\n", information.description);
	}

	@Override
	protected void populateAdditionalCommands(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Description", "description", (String input) -> information.description = input));
	}

	@Override
	protected ActivityConstructorInfo getConstructorInfo() {
		return information;
	}

	@Override
	protected void create() {
		if(isConstructorInfoInvalid())
			return;
		
		getController().getSelectedProject().createTask(information);
	}
}
