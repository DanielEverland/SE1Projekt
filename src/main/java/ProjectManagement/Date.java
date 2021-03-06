package ProjectManagement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date implements Comparable<Date> {

	private java.util.Date InternalDate;

	public static Date FromString(String dateString) {
		Date newDate = new Date();

		try {
			newDate.InternalDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
		} catch (ParseException e) {
			ErrorMessageHandler.addErrorMessage("Couldn't parse date " + dateString);
		}

		return newDate;
	}

	@Override
	public String toString() {
		if(InternalDate == null)
			return "N/A";
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(InternalDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((InternalDate == null) ? 0 : InternalDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (InternalDate == null) {
			if (other.InternalDate != null)
				return false;
		} else if (!InternalDate.equals(other.InternalDate))
			return false;
		return true;
	}

	@Override
	public int compareTo(Date date) {
		return InternalDate.compareTo(date.InternalDate);
	}
	
	public boolean hasSpecifiedDate() {
		return InternalDate != null;
	}

	public boolean before(Date date) {
		return InternalDate.before(date.InternalDate);
	}
	
	public boolean beforeOrEqual(Date date) {
		return InternalDate.before(date.InternalDate) || InternalDate.equals(date.InternalDate);
	}

	public boolean after(Date date) {
		return InternalDate.after(date.InternalDate);
	}
	
	public boolean afterOrEqual(Date date) {
		return InternalDate.after(date.InternalDate) || InternalDate.equals(date.InternalDate);
	}
}
