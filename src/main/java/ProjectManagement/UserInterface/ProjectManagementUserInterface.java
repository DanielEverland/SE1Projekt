package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;
import ProjectManagement.Project;

public class ProjectManagementUserInterface implements UserInterface {

	private UserInterface parent;
	
	ProjectManagementUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		if(Main.getCurrentApplication().getProjects().size() > 0) {
			commands.add(new GenericCommand("Select project", () -> Main.setUserInterface(new SelectProjectUserInterface(this))));
		}
		
		if(Main.getSelectedProject() != null && !Main.getSelectedProject().hasProjectLeader()) {
			commands.add(new AssignProjectLeaderCommand());
		}
		
		commands.add(new CreateProjectCommand());
		commands.add(new ReturnCommand());
	}

	@Override
	public String getDescription() {
		Project selectedProject = Main.getSelectedProject();
		return String.format("Selected project: %s", selectedProject != null ? selectedProject.getTitle() : "None");
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}
}
