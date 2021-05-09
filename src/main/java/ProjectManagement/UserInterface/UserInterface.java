package ProjectManagement.UserInterface;

import java.util.*;

public interface UserInterface {
	String getDescription();
	UserInterface getParent();
	Controller getController();
	void PopulateCommands(List<UserCommand> commands);
}