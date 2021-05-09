package ProjectManagement.UserInterface;

import java.util.Collection;
import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Application;
import ProjectManagement.Employee;
import ProjectManagement.Main;

public class ShowAssignedActivitiesCommand implements UserCommand {

	private Controller controller;
	
	public ShowAssignedActivitiesCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Show assigned activities";
	}

	@Override
	public void execute(List<String> args) {
		assert controller.getCurrentApplication().isSignedIn();
		
		Employee signedInEmployee = controller.getCurrentApplication().getSignedInEmployee();
		Collection<Activity> assignedActivities = signedInEmployee.getAssignedActivites();
		
		System.out.println(String.format("The user \"%s\" have %d activities assigned to them\n", signedInEmployee.getId(), assignedActivities.size()));
		
		for(Activity assignedActivity : assignedActivities) {
			System.out.println(assignedActivity.toString());
			System.out.println();
		}
	}

	@Override
	public List<String> getParameterNames() {
		return null;
	}
}