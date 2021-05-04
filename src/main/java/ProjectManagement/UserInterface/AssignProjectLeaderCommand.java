package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Employee;
import ProjectManagement.ErrorMessageHandler;
import ProjectManagement.Main;

public class AssignProjectLeaderCommand implements UserCommand {

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
		Employee selectedEmployee = Application.Get().getEmployee(selectedEmployeeId);
		if(selectedEmployee == null) {
			ErrorMessageHandler.addErrorMessage("Couldn't find user " + selectedEmployeeId);
			return;
		}
		
		Main.getSelectedProject().assignProjectLeader(selectedEmployee);
	}

}
