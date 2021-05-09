package ProjectManagement.UserInterface;

import java.util.Collection;
import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Employee;
import ProjectManagement.Main;

public class ShowAssignedActivitiesCommand implements UserCommand {

	@Override
	public String getDisplayName() {
		return "Show assigned activities";
	}

	@Override
	public void execute(List<String> args) {
		assert Main.getCurrentApplication().isSignedIn();
		
		Employee signedInEmployee = Main.getCurrentApplication().getSignedInEmployee();
		Collection<Activity> assignedActivities = signedInEmployee.getAssignedActivities();
		
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