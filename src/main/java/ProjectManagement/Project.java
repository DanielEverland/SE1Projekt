package ProjectManagement;

import java.util.ArrayList;

public class Project {
    private ArrayList<Task> tasks;
    private Employee projectLead;
    private int projectNumber;
    private String title;

    public Project(String title){
        this.title = title;
        tasks = new ArrayList<Task>();
    }

    public String getTitle() {
        return title;
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