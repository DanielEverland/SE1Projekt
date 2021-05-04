package ProjectManagement;

public class SickLeave extends Activity {
	public SickLeave(Date startDate, Date endDate) {
		super(new ActivityConstructorInfo("Sick Leave", startDate, endDate, true));
	}
}