package ProjectManagement;

// Abstraction layer to simplify dealing with durations
public class Duration {
	
	private long MinutesPassed;
	
	public void AddTime(int hours, int minutes) {
		AddHours(hours);
		AddMinutes(minutes);
	}
	
	public void AddMinutes(int minutes) {
		MinutesPassed += minutes;
	}
	
	public void AddHours(int hours) {
		MinutesPassed += hours * 60;
	}
	
	public long GetMinutesPassed() {
		return MinutesPassed;
	}
	
	public long GetHoursPassed() {
		return MinutesPassed / 60;
	}
	
	public boolean HaveHoursPassed(int hours) {
		return MinutesPassed >= hours * 60;
	}
	
	public boolean HaveMinutesPassed(long minutes) {
		return MinutesPassed >= minutes;
	}
}