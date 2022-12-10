package main.java.dom2app;

import java.util.ArrayList;
import java.util.List;

public class ComplexTask extends Task {
    private List<Task> tasks;


    public ComplexTask(int taskId, String taskText, int mamaId) {
        super(taskId, taskText, mamaId, 0, 0, 0, true);
        tasks = new ArrayList<>();
    }

    public ComplexTask(int taskId, String taskText, int mamaId, int start, int end, int cost) {
        super(taskId, taskText, mamaId, start, end, cost, true);
        tasks = new ArrayList<>();
    }

    public ComplexTask() {
        super(-1, "-1", 0, 0, 0, 0, true);
        tasks = new ArrayList<>();
    }

    public void addSimpleTask(Task task) {
        if (task.getMamaId() == this.getTaskId()) {
            tasks.add(task);
        }
    }



    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "ComplexTask{" +
                "tasks=" + tasks +
                '}';
    }
}
