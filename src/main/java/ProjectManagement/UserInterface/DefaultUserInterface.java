package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Employee;
import ProjectManagement.Project;

public class DefaultUserInterface implements UserInterface {

	private Controller controller;
	
	public DefaultUserInterface(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		if (getController().getCurrentApplication().isSignedIn()) {
			commands.add(new GenericCommand("Project Management", () -> getController().setUserInterface(new ProjectManagementUserInterface(this))));
			commands.add(new GenericCommand("Activity Management", () -> getController().setUserInterface(new ActivityManagementUserInterface(this))));
			commands.add(new LogoutCommand(getController()));
		} else {
			commands.add(new LoginCommand(getController()));
		}

		commands.add(new QuitCommand(getController()));
	}

	@Override
	public String getDescription() {
		String description = new String();
		
		Employee signedInEmployee = getController().getCurrentApplication().getSignedInEmployee();
		description += String.format("Signed in as: %s\n", signedInEmployee != null ? signedInEmployee.getId() : "None");
				
		Project selectedProject = getController().getSelectedProject();
		description += String.format("Selected project: %s", selectedProject != null ? selectedProject.getTitle() : "None");
		
		return description;
	}

	@Override
	public UserInterface getParent() {
		// This is null on purpose, as default should always be root
		return null;
	}

	@Override
	public Controller getController() {
		return controller;
	}
}
