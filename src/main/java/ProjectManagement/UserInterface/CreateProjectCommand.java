package ProjectManagement.UserInterface;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.Application;
import ProjectManagement.Main;

public class CreateProjectCommand implements UserCommand {

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
		 
		 int createdId = Main.getCurrentApplication().createProject(args.get(0));
		 
		 if(Main.getSelectedProject() == null) {
			 Main.selectProject(Main.getCurrentApplication().getProject(createdId));
		 }
		 
		 Main.setPreviousUserInterface();
	}
	
	@Override
	public List<String> getParameterNames() {
		return new ArrayList<String>() {{
			add("projectName");
		}};
	}
}
