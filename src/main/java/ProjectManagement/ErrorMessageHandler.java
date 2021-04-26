package ProjectManagement;

public class ErrorMessageHandler {

	private String PreviousErrorMessage;
	
	public String getPreviousErrorMessage() {
		return PreviousErrorMessage;
	}
	
	public void addErrorMessage(String errorMessage) {
		System.out.println("Error: " + errorMessage);
		PreviousErrorMessage = errorMessage;
	}
}
