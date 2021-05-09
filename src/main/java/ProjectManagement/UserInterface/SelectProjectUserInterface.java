package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Project;

public class SelectProjectUserInterface implements UserInterface {

	private UserInterface parent;
	
	SelectProjectUserInterface(UserInterface parent) { 
		this.parent = parent;
	}
	
	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		int projectCount = getController().getCurrentApplication().getProjects().values().size();
		
		for(int i = 0; i < projectCount; i++) {
			Project currentProject = getController().getCurrentApplication().getProject(i);
			commands.add(new GenericIndexedCommand(currentProject.getTitle(), i, (int selectedIndex) -> selectedCommand(selectedIndex)));
		}
		
		commands.add(new ReturnCommand(getController()));
	}
	
	private void selectedCommand(int index) {
		getController().selectProject(getController().getCurrentApplication().getProject(index));
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
