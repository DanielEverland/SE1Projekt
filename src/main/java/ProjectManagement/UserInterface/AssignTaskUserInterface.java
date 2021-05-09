package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Employee;
import ProjectManagement.Main;
import ProjectManagement.Task;

public class AssignTaskUserInterface implements UserInterface {

	private UserInterface parent;
	private List<Employee> availableEmployees;
	private List<Employee> assignedEmployees;
	
	public AssignTaskUserInterface(UserInterface parent) {
		this.parent = parent;
		createAssignedEmployeesList();
		createAvailableEmployeesList();
	}
	
	@Override
	public String getDescription() {
		return String.format("There are %d available employees \nThe activity currently has %d employee(s) assigned to it", availableEmployees.size(), assignedEmployees.size());
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		for(int i = 0; i < availableEmployees.size(); i++) {
			commands.add(new GenericIndexedCommand(availableEmployees.get(i).getId(), i, (int index) -> selectedEmployee(index)));
		}
		
		commands.add(new ReturnCommand(getController()));
	}
	
	private void selectedEmployee(int index) {
		Employee selectedEmployee = availableEmployees.get(index);
		getController().getSelectedProject().assignTaskToEmployee(selectedEmployee, getSelectedTask());
		createAssignedEmployeesList();
		createAvailableEmployeesList();
	}
	
	private void createAssignedEmployeesList() {
		assignedEmployees = getController().getSelectedProject().getAssignedEmployees(getSelectedTask());
	}
	
	private void createAvailableEmployeesList() {
		Activity currentActivity = getController().getSelectedActivity();
		availableEmployees = getController().getCurrentApplication().getAvailableEmployees(currentActivity.getStartDate(), currentActivity.getEndDate());		
		availableEmployees.removeAll(assignedEmployees);
	}
	
	private Task getSelectedTask() {
		return (Task)getController().getSelectedActivity();
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
