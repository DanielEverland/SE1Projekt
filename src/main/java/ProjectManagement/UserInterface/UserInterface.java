package ProjectManagement.UserInterface;

import java.util.*;

public interface UserInterface {
	String getDescription();
	UserInterface getParent();
	void PopulateCommands(List<UserCommand> commands);
}