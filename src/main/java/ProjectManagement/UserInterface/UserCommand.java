package ProjectManagement.UserInterface;

import java.util.List;

public interface UserCommand {
	String getDisplayName();
	void execute(List<String> args);
}
