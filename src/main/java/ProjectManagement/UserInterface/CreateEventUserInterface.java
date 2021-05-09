package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Application;
import ProjectManagement.Main;

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
		if(isConstructorInfoInvalid())
			return;
		
		Main.getCurrentApplication().getSignedInEmployee().assignToActivity(createActivity());
	}
		
	// Disable behaviour as sick leave cannot have titles
	@Override
	protected void addTitleCommand(List<UserCommand> commands) {		
	}	
	@Override
	protected boolean shouldDisplayTitleDescription() {
		return false;
	}
	
	protected abstract Activity createActivity();
}
