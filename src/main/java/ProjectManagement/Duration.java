package ProjectManagement;

// Abstraction layer to simplify dealing with durations
public class Duration {
	private static final double EPSILON = 0.00001;
	private long minutesPassed;

	public void addTime(Double hours, int minutes) {
		addHours(hours);
		addMinutes(minutes);
	}

	public void addMinutes(int minutes) {
		assert minutes > 0;

		minutesPassed += minutes;
	}

	public void addHours(double hours) {
		assert isAtleastOneMinute(hours);
		minutesPassed += (int) (hours * 60);
	}

	private boolean isAtleastOneMinute(double hours) {
		return hours - 1 / 60.0 >= getEpsilon();
	}

	public long getMinutesPassed() {
		return minutesPassed;
	}

	public double getHoursPassed() {
		return minutesPassed / 60.0;
	}

	public static double getEpsilon() {
		return EPSILON;
	}

}