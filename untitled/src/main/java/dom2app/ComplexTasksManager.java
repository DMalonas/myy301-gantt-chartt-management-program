package main.java.dom2app;

import java.util.ArrayList;
import java.util.List;

public class ComplexTasksManager {
    private List<ComplexTask> complexTasks;


    public ComplexTasksManager() {
        this.complexTasks = new ArrayList<>();
    }

    public List<ComplexTask> getComplexTasks() {
        return complexTasks;
    }

    public void setComplexTasks(List<ComplexTask> complexTasks) {
        this.complexTasks = complexTasks;
    }

    public void addOrUpdateComplexTask(ComplexTask updatedOrNewComplexTask) {
        for (int i = 0; i < complexTasks.size(); i++) {
            Task complexTask = complexTasks.get(i);
            if (complexTask.getTaskId() == updatedOrNewComplexTask.getTaskId()) {
                int index = complexTasks.indexOf(complexTask);
                complexTasks.set(index, updatedOrNewComplexTask);
                return;
            }
        }
        complexTasks.add(updatedOrNewComplexTask);
    }

    public void sortByStartTime(ComplexTasksManager complexTasksManager) {
        List<Task> placeholderLi = new ArrayList<>();
        for (int i = 0; i < placeholderLi.size(); i++) {
        }
    }
}
