package ProjectManagement.UserInterface;

import ProjectManagement.Activity;
import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.SickLeave;

public class CreateSickPeriodUserInterface extends CreateEventUserInterface {

	CreateSickPeriodUserInterface(UserInterface parent) {
		super(parent);
	}

	@Override
	protected Activity createActivity() {
		ActivityConstructorInfo info = getConstructorInfo();
		return new SickLeave(info.startDate, info.endDate);
	}
}
