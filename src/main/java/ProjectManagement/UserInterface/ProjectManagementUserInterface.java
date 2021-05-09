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
		if(getController().getCurrentApplication().getProjects().size() > 0) {
			commands.add(new GenericCommand("Select project", () -> getController().setUserInterface(new SelectProjectUserInterface(this))));
		}
		
		if(getController().getSelectedProject() != null && !getController().getSelectedProject().hasProjectLeader()) {
			commands.add(new AssignProjectLeaderCommand(getController()));
		}
		
		commands.add(new CreateProjectCommand(getController()));
		commands.add(new ReturnCommand(getController()));
	}

	@Override
	public String getDescription() {
		Project selectedProject = getController().getSelectedProject();
		return String.format("Selected project: %s", selectedProject != null ? selectedProject.getTitle() : "None");
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
