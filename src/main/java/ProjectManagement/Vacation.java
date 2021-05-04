package ProjectManagement;

public class Vacation extends Activity {
    public Vacation(Date startDate, Date endDate) {
        super(new ActivityConstructorInfo("Vacation", startDate, endDate, true));
    }
}