package ProjectManagement;

public class ActivityConstructorInfo {
	public Date startDate;
	public Date endDate;
	public String title;
	
	private static final int minTitleLength = 3;
	
	public ActivityConstructorInfo(String title, Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}
	
	public boolean isValid() {
		return isASCIIString(title) && title.length() > minTitleLength;
	}
	
	private boolean isASCIIString(String input) {
		// All ASCII characters have a values of less than 128
		return input.chars().allMatch(c -> c < 128);
	}
}
