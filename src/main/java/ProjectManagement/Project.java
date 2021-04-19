package ProjectManagement;

import java.util.ArrayList;

public class Project {
    private ArrayList<Task> tasks;
    private Employee projectLead;
    private int projectNumber;
    private String title;

    public Project(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
