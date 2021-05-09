package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Employee;
import ProjectManagement.Main;
import ProjectManagement.Project;

public class DefaultUserInterface implements UserInterface {

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		if (Main.getCurrentApplication().isSignedIn()) {
			commands.add(new GenericCommand("Project Management", () -> Main.setUserInterface(new ProjectManagementUserInterface(this))));
			commands.add(new GenericCommand("Activity Management", () -> Main.setUserInterface(new ActivityManagementUserInterface(this))));
			commands.add(new LogoutCommand());
		} else {
			commands.add(new LoginCommand());
		}

		commands.add(new QuitCommand());
	}

	@Override
	public String getDescription() {
		String description = new String();
		
		Employee signedInEmployee = Main.getCurrentApplication().getSignedInEmployee();
		description += String.format("Signed in as: %s\n", signedInEmployee != null ? signedInEmployee.getId() : "None");
				
		Project selectedProject = Main.getSelectedProject();
		description += String.format("Selected project: %s", selectedProject != null ? selectedProject.getTitle() : "None");
		
		return description;
	}

	@Override
	public UserInterface getParent() {
		// This is null on purpose, as default should always be root
		return null;
	}
}
