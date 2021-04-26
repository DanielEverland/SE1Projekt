package ProjectManagement;

// Abstraction layer to simplify dealing with durations
public class Duration {
	private static final double EPSILON = 0.00001; 
	private long MinutesPassed;
	
	public void AddTime(int hours, int minutes) {
		AddHours(hours);
		AddMinutes(minutes);
	}
	
	public void AddMinutes(int minutes) {
		assert minutes > 0;
		
		MinutesPassed += minutes;
	}
	
	public void AddHours(int hours) {
		assert hours > 0;
		
		MinutesPassed += hours * 60;
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
	
	public boolean HaveHoursPassed(int hours) {
		assert hours > 0;
		
		return MinutesPassed >= hours * 60;
	}
	
	public boolean HaveMinutesPassed(long minutes) {
		assert minutes > 0;
		
		return MinutesPassed >= minutes;
	}

	public static double getEpsilon() {
		return EPSILON;
	}
	
}