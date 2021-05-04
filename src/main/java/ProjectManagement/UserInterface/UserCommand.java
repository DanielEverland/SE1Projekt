package ProjectManagement.UserInterface;

import java.util.List;

public interface UserCommand {
	List<String> getParameterNames();
	String getDisplayName();
	void execute(List<String> args);
}
