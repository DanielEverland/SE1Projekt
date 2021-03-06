package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Employee;
import ProjectManagement.ErrorMessageHandler;

public class AssignProjectLeaderCommand implements UserCommand {

	private Controller controller;
	
	AssignProjectLeaderCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Assign project leader";
	}

	@Override
	public void execute(List<String> args) {
		if(args.size() < 1) {
			ErrorMessageHandler.addErrorMessage("Missing username argument");
			return;
		}
		
		String selectedEmployeeId = args.get(0);		
		Employee selectedEmployee = controller.getCurrentApplication().getEmployee(selectedEmployeeId);
		if(selectedEmployee == null) {
			ErrorMessageHandler.addErrorMessage("Couldn't find user " + selectedEmployeeId);
			return;
		}
		
		controller.getSelectedProject().assignProjectLeader(selectedEmployee);
	}

	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add("userName");
		}};
	}

}
