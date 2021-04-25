package ProjectManagement;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    // The number of digits in the stringified id of projects
    private static final int serialDigits = 4;

    private int year;
    private int id;
    private String title;
    private ArrayList<Task> tasks;
    private Employee projectLead;

    public Project(int id, String title) {
        year = Calendar.getInstance().get(Calendar.YEAR);
        this.id = id;
        this.title = title;
        tasks = new ArrayList<Task>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFullTitle() {
        return year + "|" + idToString() + "|" + title;
    }

    private String idToString() {
        return String.format("%0" + serialDigits + "d", id);
    }

    public void assignProjectLeader(Employee newProjectLeader) {
    	projectLead = newProjectLeader;
    }

    public void createTask(TaskConstructorInfo info) {
    	tasks.add(new Task(info));
    }

    public boolean containsTask(String title, String description, Integer startDateUnix, Integer endDateUnix) {
    	for(Task task : tasks)
    	{
    		if(task.getTile().equals(title) &&
				task.getDescription().equals(description) &&
				task.getStartDate() == startDateUnix &&
				task.getEndDate() == endDateUnix)
    		{
    			return true;
    		}
    	}

    	return false;
    }
}
