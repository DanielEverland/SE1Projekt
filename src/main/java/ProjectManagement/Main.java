package ProjectManagement;

import java.util.*;

import ProjectManagement.UserInterface.*;

public class Main {

	private static UserInterface currentUserInterface;
	private static Scanner inputScanner;

	public static void main(String[] arguments) {
		inputScanner = new Scanner(System.in);
		setDefaultUserInterface();
		while (mainLoop()) {
		}
	}

	public static void setUserInterface(UserInterface newUserInterface) {
		assert newUserInterface != null;

		currentUserInterface = newUserInterface;
	}

	public static void setDefaultUserInterface() {
		currentUserInterface = new DefaultUserInterface();
	}

	private static boolean mainLoop() {
		if (Application.Get().getIsQuitting())
			return false;

		printLoggedInUser();
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

	private static void printLoggedInUser() {
		Employee signedInEmployee = Application.Get().getSignedInEmployee();
		if (signedInEmployee == null) {
			System.out.println("Currently not signed in");
		} else {
			System.out.println("Currently signed in as \"" + signedInEmployee.getId() + "\"");
		}
	}

	private static void printAllCommands() {
		List<UserCommand> allCurrentCommands = currentUserInterface.GetCommands();
		for (int i = 0; i < allCurrentCommands.size(); i++) {
			System.out.println(String.format("%d: %s", i + 1, allCurrentCommands.get(i).getDisplayName()));
		}
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
		List<UserCommand> allCurrentCommands = currentUserInterface.GetCommands();
		int idx = commandIndex - 1;

		if (allCurrentCommands.size() <= idx) {
			System.out.println("Index " + idx + " did not match any command");
			return;
		}

		args = removeCommandIndexFromArguments(args);

		// By setting this before executing, we ensure the default behaviour is to
		// return to the default user interface
		// If the command manually sets another user interface, then that will be used
		// instead.
		setDefaultUserInterface();

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
