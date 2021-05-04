package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Main;
import ProjectManagement.TaskConstructorInfo;

public class CreateTaskUserInterface extends CreateActivityUserInterface {

	private TaskConstructorInfo information;
	
	CreateTaskUserInterface(UserInterface parent) {
		super(parent);
		
		information = new TaskConstructorInfo();
	}
	
	@Override
	public String getDescription() {
		return super.getDescription() + String.format("Description: %s\n", information.Description);
	}

	@Override
	protected void populateAdditionalCommands(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Description", (String input) -> information.Description = input));
	}

	@Override
	protected ActivityConstructorInfo getConstructorInfo() {
		return information;
	}

	@Override
	protected void create() {
		Main.getSelectedProject().createTask(information);
	}
}