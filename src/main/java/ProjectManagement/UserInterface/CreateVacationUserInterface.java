package ProjectManagement.UserInterface;

import ProjectManagement.Activity;
import ProjectManagement.ActivityConstructorInfo;
import ProjectManagement.Vacation;

public class CreateVacationUserInterface extends CreateEventUserInterface {

	CreateVacationUserInterface(UserInterface parent) {
		super(parent);
	}

	@Override
	protected Activity createActivity() {
		ActivityConstructorInfo info = getConstructorInfo();
		return new Vacation(info.startDate, info.endDate);
	}
}
