package ProjectManagement.UserInterface;

import java.util.List;

import ProjectManagement.Activity;
import ProjectManagement.Main;

public class SelectActivityUserInterface implements UserInterface {

	private UserInterface parent;
	private List<Activity> allActivities; 
	
	SelectActivityUserInterface(UserInterface parent) {
		this.parent = parent;
	}
	
	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public UserInterface getParent() {
		return parent;
	}

	@Override
	public void PopulateCommands(List<UserCommand> commands) {
		allActivities = getController().getCurrentApplication().getAllActivities();
		for(int i = 0; i < allActivities.size(); i++) {
			commands.add(new GenericIndexedCommand(allActivities.get(i).getTitle(), i, (int index) -> selectedActivity(index)));
		}
		
		commands.add(new ReturnCommand(getController()));
	}
	
	private void selectedActivity(int index) {
		getController().setSelectedActivity(allActivities.get(index));
		getController().setPreviousUserInterface();
	}

	@Override
	public Controller getController() {
		return getParent().getController();
	}
}
