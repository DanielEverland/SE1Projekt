package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Date;
import ProjectManagement.ErrorMessageHandler;
import ProjectManagement.Main;
import ProjectManagement.Task;

public class EditTaskUserInterface implements UserInterface {

	private UserInterface parent;
	
	public EditTaskUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		return Main.getSelectedActivity().toString();
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Title: ", "titleName", (String newTitle) -> getSelectedTask().editTitle(newTitle)));
		commands.add(new GenericInputCommand("Description: ", "description", (String newDescription) -> getSelectedTask().editTitle(newDescription)));
		commands.add(new GenericInputCommand("Start Date: ", "startDate", (String startDateString) -> getSelectedTask().setStartDate(Date.FromString(startDateString))));
		commands.add(new GenericInputCommand("End Date: ", "endDate", (String endDateString) -> getSelectedTask().setStartDate(Date.FromString(endDateString))));
		commands.add(new GenericInputCommand("Expected Time: ", "timeInHours", (String expectedTimeString) -> setExpectedTime(expectedTimeString)));
		
		commands.add(new ReturnCommand());
	}

	private Task getSelectedTask() {
		return (Task)Main.getSelectedActivity();
	}
	
	private void setExpectedTime(String expectedTimeString) {
		try {
			getSelectedTask().setExpectedTime(Double.parseDouble(expectedTimeString));			
		}
		catch(NumberFormatException e) {
			ErrorMessageHandler.addErrorMessage(String.format("Couldn't convert %s to hours (double)", expectedTimeString));
		}		
	}
}
