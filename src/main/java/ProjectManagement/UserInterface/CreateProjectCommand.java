package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Application;

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
		 
		 Application.Get().createProject(args.get(0));
	}

}
