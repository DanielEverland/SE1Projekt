package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Date;
import ProjectManagement.ErrorMessageHandler;
import ProjectManagement.Task;

public class EditTaskUserInterface implements UserInterface {

	private UserInterface parent;
	
	public EditTaskUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		return getController().getSelectedActivity().toString();
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		commands.add(new GenericInputCommand("Title: ", "titleName", (String newTitle) -> getSelectedTask().editTitle(newTitle)));
		commands.add(new GenericInputCommand("Description: ", "description", (String newDescription) -> getSelectedTask().editTitle(newDescription)));
		commands.add(new GenericInputCommand("Start Date: ", "startDate", (String startDateString) -> setStartDate(startDateString)));
		commands.add(new GenericInputCommand("End Date: ", "endDate", (String endDateString) -> setEndDate(endDateString)));
		commands.add(new GenericInputCommand("Expected Time: ", "timeInHours", (String expectedTimeString) -> setExpectedTime(expectedTimeString)));
		
		commands.add(new ReturnCommand(getController()));
	}

	private Task getSelectedTask() {
		return (Task)getController().getSelectedActivity();
	}
	
	private void setExpectedTime(String expectedTimeString) {
		try {
			getSelectedTask().setExpectedTime(Double.parseDouble(expectedTimeString));			
		}
		catch(NumberFormatException e) {
			ErrorMessageHandler.addErrorMessage(String.format("Couldn't convert %s to hours (double)", expectedTimeString));
		}		
	}
	
	private void setStartDate(String startDateString) {
		Date startDate = Date.FromString(startDateString);		
		Task selectedTask = getSelectedTask();
		
		if(!areDatesValid(startDate, selectedTask.getEndDate()))
		{
			ErrorMessageHandler.addErrorMessage("Start date must be before or on end date");
			return;
		}
		
		selectedTask.setStartDate(startDate);
	}
	
	private void setEndDate(String endDateString) {
		Date endDate = Date.FromString(endDateString);		
		Task selectedTask = getSelectedTask();
		
		if(!areDatesValid(selectedTask.getStartDate(), endDate))
		{
			ErrorMessageHandler.addErrorMessage("End date must be after or on start date");
			return;
		}
		
		selectedTask.setEndDate(endDate);
	}
	
	private boolean areDatesValid(Date startDate, Date endDate) {
		return endDate.afterOrEqual(startDate);
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
