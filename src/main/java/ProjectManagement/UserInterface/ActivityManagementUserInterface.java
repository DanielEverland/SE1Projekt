package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Main;
import ProjectManagement.Project;
import ProjectManagement.Task;

public class ActivityManagementUserInterface implements UserInterface {

	private UserInterface parent;
	
	ActivityManagementUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		Activity selectedActivity = getController().getSelectedActivity();
		return String.format("Selected activity: %s", selectedActivity != null ? selectedActivity.getTitle() : "None");
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		commands.add(new ShowAssignedActivitiesCommand(getController()));
		
		commands.add(new GenericCommand("Select activity", () -> getController().setUserInterface(new SelectActivityUserInterface(this))));
		
		Project selectedProject = getController().getSelectedProject();
		if(selectedProject != null && selectedProject.getProjectLeader() == getController().getCurrentApplication().getSignedInEmployee()) {
			commands.add(new GenericCommand("Create new task", () -> getController().setUserInterface(new CreateTaskUserInterface(this))));

			if(getController().getSelectedActivity() != null && getController().getSelectedActivity() instanceof Task) {
				commands.add(new GenericCommand("Assign employee to selected task", () -> getController().setUserInterface(new AssignTaskUserInterface(this))));
				commands.add(new GenericCommand("Edit current activity", () -> getController().setUserInterface(new EditTaskUserInterface(this))));
			}
		}
		
		if(selectedProject != null && getController().getCurrentApplication().isSignedIn()) {
			commands.add(new GenericCommand("Log hours worked", () -> getController().setUserInterface(new LogHoursWorkedUserInterface(this))));
		}
		
		commands.add(new GenericCommand("Create new sick leave", () -> getController().setUserInterface(new CreateSickPeriodUserInterface(this))));
		commands.add(new GenericCommand("Create new vacation", () -> getController().setUserInterface(new CreateVacationUserInterface(this))));
		commands.add(new GenericCommand("Create new course", () -> getController().setUserInterface(new CreateCourseUserInterface(this))));
		
		commands.add(new ReturnCommand(getController()));
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
