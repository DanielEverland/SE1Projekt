package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Course;

public class CreateCourseUserInterface extends CreateEventUserInterface {

	private String description;
	
	CreateCourseUserInterface(UserInterface parent) {
		super(parent);
		
		description = "N/A";
	}
	
	@Override
	public String getDescription() {
		return super.getDescription() + String.format("Description: %s\n", description);
	}

	@Override
	protected Activity createActivity() {
		ActivityConstructorInfo info = getConstructorInfo();
		return new Course(description, info.startDate, info.endDate);
	}
	
	@Override
	protected void populateAdditionalCommands(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Description", "description", (String input) -> description = input));
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
