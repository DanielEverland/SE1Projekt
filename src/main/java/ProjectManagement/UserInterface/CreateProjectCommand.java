package ProjectManagement.UserInterface;

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
		 
		 int createdId = Application.Get().createProject(args.get(0));
		 
		 if(Main.getSelectedProject() == null) {
			 Main.selectProject(Application.Get().getProject(createdId));
		 }
		 
		 Main.setPreviousUserInterface();
	}
}
