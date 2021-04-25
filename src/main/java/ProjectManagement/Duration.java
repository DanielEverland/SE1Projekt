package ProjectManagement;

// Abstraction layer to simplify dealing with durations
public class Duration {
	
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
	
	public long GetMinutesPassed() {
		return MinutesPassed;
	}
	
	public long GetHoursPassed() {
		return MinutesPassed / 60;
	}
	
	public boolean HaveHoursPassed(int hours) {
		assert hours > 0;
		
		return MinutesPassed >= hours * 60;
	}
	
	public boolean HaveMinutesPassed(long minutes) {
		assert minutes > 0;
		
		return MinutesPassed >= minutes;
	}
}