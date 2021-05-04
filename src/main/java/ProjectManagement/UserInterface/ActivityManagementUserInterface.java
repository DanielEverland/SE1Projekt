package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Main;

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
		
		if(Main.getSelectedProject() != null) {
			commands.add(new GenericCommand("Create new task", () -> Main.setUserInterface(new CreateTaskUserInterface(this))));
		}
		
		commands.add(new GenericCommand("Create new sick leave", () -> Main.setUserInterface(new CreateSickPeriodUserInterface(this))));
		commands.add(new GenericCommand("Create new vacation", () -> Main.setUserInterface(new CreateVacationUserInterface(this))));
		commands.add(new GenericCommand("Create new course", () -> Main.setUserInterface(new CreateCourseUserInterface(this))));
		
		commands.add(new ReturnCommand());
	}

}
