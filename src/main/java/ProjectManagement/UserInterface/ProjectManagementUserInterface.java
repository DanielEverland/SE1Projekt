package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;

public class ProjectManagementUserInterface implements UserInterface {

	private UserInterface parent;
	
	ProjectManagementUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		if(Application.Get().getProjects().size() > 0) {
			commands.add(new GenericCommand("Select project", () -> Main.setUserInterface(new SelectProjectUserInterface(this))));
		}
		
		commands.add(new CreateProjectCommand());
		commands.add(new ReturnCommand());
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}
}
