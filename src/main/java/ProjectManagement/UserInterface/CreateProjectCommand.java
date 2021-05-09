package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;

public class CreateProjectCommand implements UserCommand {
	
	private Controller controller;
	
	public CreateProjectCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getDisplayName() {
		return "Create new project";
	}

	@Override
	public void execute(List<String> args) {
		 if(args.size() < 1) {
			 System.out.println("No project name provided");
			 return;
		 }
		 
		 int createdId = controller.getCurrentApplication().createProject(args.get(0));
		 
		 if(controller.getSelectedProject() == null) {
			 controller.selectProject(controller.getCurrentApplication().getProject(createdId));
		 }
		 
		 controller.setPreviousUserInterface();
	}
	
	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add("projectName");
		}};
	}
}
