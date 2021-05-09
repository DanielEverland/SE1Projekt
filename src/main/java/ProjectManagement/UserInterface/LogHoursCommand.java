package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Duration;
import ProjectManagement.ErrorMessageHandler;
import ProjectManagement.Main;
import ProjectManagement.Task;

public class LogHoursCommand implements UserCommand {

	private Task task;
	private Controller controller;
	
	LogHoursCommand(Task task, Controller controller) {
		this.task = task;
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return task.getTitle();
	}

	@Override
	public void execute(List<String> args) {
		if(args.size() < 1) {
			ErrorMessageHandler.addErrorMessage("No arguments passed as input");
			return;
		}
		
		String input = args.get(0);
		
		try {
			double hoursToLog = Double.parseDouble(input);
			task.logWorkHours(controller.getCurrentApplication().getSignedInEmployee(), hoursToLog);
			
			Duration timeWorked = task.getDurationWorked().get(controller.getCurrentApplication().getSignedInEmployee());
			System.out.println(String.format("Logged %f additional hours. Total hours worked: %f", hoursToLog, timeWorked.getHoursPassed()));
		}
		catch(NumberFormatException e) {
			ErrorMessageHandler.addErrorMessage(String.format("Couldn't format %s as double", input));
		}
	}

	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add("hoursToLog");
		}};
	}
}
