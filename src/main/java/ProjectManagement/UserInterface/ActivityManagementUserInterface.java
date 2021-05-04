package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;
import ProjectManagement.Project;

public class ActivityManagementUserInterface implements UserInterface {

	private UserInterface parent;
	
	ActivityManagementUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		commands.add(new ShowAssignedActivitiesCommand());
		
		Project selectedProject = Main.getSelectedProject();
		if(selectedProject != null && selectedProject.getProjectLeader() == Application.Get().getSignedInEmployee()) {
			commands.add(new GenericCommand("Create new task", () -> Main.setUserInterface(new CreateTaskUserInterface(this))));
		}
		
		commands.add(new GenericCommand("Create new sick leave", () -> Main.setUserInterface(new CreateSickPeriodUserInterface(this))));
		commands.add(new GenericCommand("Create new vacation", () -> Main.setUserInterface(new CreateVacationUserInterface(this))));
		commands.add(new GenericCommand("Create new course", () -> Main.setUserInterface(new CreateCourseUserInterface(this))));
		
		commands.add(new ReturnCommand());
	}

}
