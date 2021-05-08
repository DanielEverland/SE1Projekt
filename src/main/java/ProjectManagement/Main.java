package ProjectManagement;

import java.util.*;

import ProjectManagement.UserInterface.*;

public class Main {

	private static UserInterface currentUserInterface;
	private static Scanner inputScanner;
	private static Project selectedProject;
	private static Application currentApplication;
	private static Activity selectedActivity;

	public static void main(String[] arguments) {
		inputScanner = new Scanner(System.in);
		setDefaultUserInterface();
		currentApplication = new Application();
		while (mainLoop()) {
		}
	}
	
	public static void setSelectedActivity(Activity activity) {
		selectedActivity = activity;
	}
	
	public static Activity getSelectedActivity() {
		return selectedActivity;
	}
	
	public static void setUserInterface(UserInterface newUserInterface) {
		assert newUserInterface != null;

		currentUserInterface = newUserInterface;
	}
	
	public static Application getCurrentApplication() {
		return currentApplication;
	}

	public static void setPreviousUserInterface() {
		setUserInterface(currentUserInterface.getParent());		
	}
	
	public static void setDefaultUserInterface() {
		currentUserInterface = new DefaultUserInterface();
	}
	
	public static void selectProject(Project toSelect) {
		assert toSelect != null;
		
		selectedProject = toSelect;
	}
	
	public static Project getSelectedProject() {
		return selectedProject;
	}

	private static boolean mainLoop() {
		if (currentApplication.getIsQuitting())
			return false;
		
		System.out.println(currentUserInterface.getDescription());
		printAllCommands();

		String userInput = inputScanner.nextLine();
		List<String> arguments = stringToArguments(userInput);

		if (arguments.size() <= 0) {
			System.out.println("Couldn't understand input \"" + userInput + "\"");
			return true;
		}

		int selectedCommand;
		try {
			selectedCommand = tryParseCommandSelection(arguments);
		} catch (NumberFormatException e) {
			System.out.println("Couldn't parse input " + arguments.get(0) + " as an integer");
			return true;
		}

		executeCommand(selectedCommand, arguments);

		return true;
	}

	private static void printAllCommands() {
		List<UserCommand> allCurrentCommands = new ArrayList<UserCommand>();
		currentUserInterface.PopulateCommands(allCurrentCommands);
		for (int i = 0; i < allCurrentCommands.size(); i++) {
			System.out.println(String.format("[%d] %s %s", i + 1, allCurrentCommands.get(i).getDisplayName(), getArgumentsString(allCurrentCommands.get(i))));
		}
	}
	
	private static String getArgumentsString(UserCommand command) {
		String argumentsString = new String();
		List<String> allArguments = command.getParameterNames();
		
		if(allArguments == null)
			return "";
		
		for(int i = 0; i < allArguments.size(); i++) {
			argumentsString += String.format("[%s]", allArguments.get(i));
			
			if(i < allArguments.size() - 1) {
				argumentsString += " ";
			}
		}
		
		return argumentsString;
	}

	private static List<String> stringToArguments(String inputString) {
		ArrayList<String> arguments = new ArrayList<String>();
		for (String arg : inputString.split("\\s+")) {
			arguments.add(arg);
		}
		return arguments;
	}

	private static int tryParseCommandSelection(List<String> args) {
		return Integer.parseInt(args.get(0));
	}

	private static void executeCommand(int commandIndex, List<String> args) {
		List<UserCommand> allCurrentCommands = new ArrayList<UserCommand>();
		currentUserInterface.PopulateCommands(allCurrentCommands);
		int idx = commandIndex - 1;

		if (allCurrentCommands.size() <= idx) {
			System.out.println("Index " + idx + " did not match any command");
			return;
		}

		args = removeCommandIndexFromArguments(args);

		allCurrentCommands.get(idx).execute(args);
		System.out.println();
	}

	private static List<String> removeCommandIndexFromArguments(List<String> args) {
		// Remove the index used to call the command
		if (args.size() > 1) {
			args = args.subList(1, args.size());
		} else {
			args.clear();
		}

		return args;
	}
}
