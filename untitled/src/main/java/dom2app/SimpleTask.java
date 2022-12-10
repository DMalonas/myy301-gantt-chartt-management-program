package main.java.dom2app;

public class SimpleTask extends Task {

    public SimpleTask(int taskId, String taskText, int mamaId, int start, int end, int cost) {
        super(taskId, taskText, mamaId, start, end, cost, false);
    }
}
