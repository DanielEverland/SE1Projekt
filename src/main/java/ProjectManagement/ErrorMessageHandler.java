package ProjectManagement;

public class ErrorMessageHandler {

	private static String PreviousErrorMessage;
	
	public static String getPreviousErrorMessage() {
		return PreviousErrorMessage;
	}
	
	public static void addErrorMessage(String errorMessage) {
		System.out.println("Error: " + errorMessage);
		PreviousErrorMessage = errorMessage;
	}
}
