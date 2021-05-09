package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Employee;
import ProjectManagement.Task;

public class LogHoursWorkedUserInterface implements UserInterface {

	private UserInterface parent;
	
	public LogHoursWorkedUserInterface(UserInterface parent) {
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
		Employee signedInEmployee = getController().getCurrentApplication().getSignedInEmployee();
		List<Activity> assignedActivities = signedInEmployee.getAssignedActivities();

		for(int i = 0; i < assignedActivities.size(); i++) {
			Activity currActivity = assignedActivities.get(i);
			if(currActivity instanceof Task) {
				commands.add(new LogHoursCommand((Task)currActivity, getController()));
			}
		}
		
		commands.add(new ReturnCommand(getController()));
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
