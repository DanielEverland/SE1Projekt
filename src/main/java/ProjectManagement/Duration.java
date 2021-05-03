package ProjectManagement;

// Abstraction layer to simplify dealing with durations
public class Duration {
	private static final double EPSILON = 0.00001; 
	private long MinutesPassed;
	
	public void AddTime(Double hours, int minutes) {
		AddHours(hours);
		AddMinutes(minutes);
	}
	
	public void AddMinutes(int minutes) {
		assert minutes > 0;
		
		MinutesPassed += minutes;
	}
	
	public void AddHours(double hours) {
		assert isAtleastOneMinute(hours);
		MinutesPassed += (int) (hours*60);
	}

	private boolean isAtleastOneMinute(double hours) {
		return hours-1/60.0 >= getEpsilon();
	}
	
	public long GetMinutesPassed() {
		return MinutesPassed;
	}
	
	public double GetHoursPassed() {
		return MinutesPassed / 60.0;
	}

	public static double getEpsilon() {
		return EPSILON;
	}
	
}