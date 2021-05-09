package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Date;
import ProjectManagement.ErrorMessageHandler;
import ProjectManagement.Main;

public abstract class CreateActivityUserInterface implements UserInterface {

	private UserInterface parent;
	
	CreateActivityUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		ActivityConstructorInfo constructorInfo = getConstructorInfo();		
		String description = new String();
		
		if(shouldDisplayTitleDescription())
			description += String.format("Title: %s\n", getConstructorInfo().title);
		
		description += String.format("Start Date: %s\n", constructorInfo.startDate.toString());
		description += String.format("End Date: %s\n", constructorInfo.endDate.toString());
		
		return description;
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		addTitleCommand(commands);
		commands.add(new GenericInputCommand("Start Date", "date", (String input) -> getConstructorInfo().startDate = Date.FromString(input)));
		commands.add(new GenericInputCommand("End Date", "date", (String input) -> getConstructorInfo().endDate = Date.FromString(input)));
		
		populateAdditionalCommands(commands);
		
		commands.add(new GenericCommand("Create", () ->
		{
			create();
			Main.setPreviousUserInterface();
		}));
		commands.add(new ReturnCommand());
	}
	
	protected boolean shouldDisplayTitleDescription() {
		return true;
	}
	
	protected void addTitleCommand(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Title", "title", (String input) -> getConstructorInfo().title = input));
	}
	
	protected boolean isConstructorInfoInvalid() {
		if(!getConstructorInfo().datesValid()) {
			ErrorMessageHandler.addErrorMessage("Dates are not valid. Start date must be before or on end date.");
			return true;
		}
		
		if(!getConstructorInfo().isValid()) {
			ErrorMessageHandler.addErrorMessage("Information is invalid for an unspecified reason");
			return true;
		}
		
		return false;
	}
	
	protected abstract void populateAdditionalCommands(List<UserCommand> commands);
	protected abstract ActivityConstructorInfo getConstructorInfo();
	protected abstract void create();
}
