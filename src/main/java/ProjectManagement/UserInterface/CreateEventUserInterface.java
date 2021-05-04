package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Application;

public abstract class CreateEventUserInterface extends CreateActivityUserInterface {
	
	ActivityConstructorInfo information;
	
	CreateEventUserInterface(UserInterface parent) {
		super(parent);
		
		information = new ActivityConstructorInfo();
	}

	@Override
	protected void populateAdditionalCommands(List<UserCommand> commands) {
	}

	@Override
	protected ActivityConstructorInfo getConstructorInfo() {
		return information;
	}

	@Override
	protected void create() {
		Application.Get().getSignedInEmployee().assignToActivity(createActivity());
	}
		
	// Disable behaviour as sick leave cannot have titles
	@Override
	protected void addTitleCommand(List<UserCommand> commands) {		
	}
	
	protected abstract Activity createActivity();
}
