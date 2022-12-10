package main.java.dom2app;

public class Task {
    private int taskId;
    private String taskText;
    private int mamaId;
    private int start;
    private int end;
    private int cost;

    private boolean isTopLvl;

    public Task() {
    }


    public Task(int taskId, String taskText, int mamaId, int start, int end, int cost, boolean isTopLvl) {
        this.taskId = taskId;
        this.taskText = taskText;
        this.mamaId = mamaId;
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.isTopLvl = isTopLvl;
    }



    public Task(int taskId, String taskText, int mamaId) {
        this.taskId = taskId;
        this.taskText = taskText;
        this.mamaId = mamaId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public int getMamaId() {
        return mamaId;
    }

    public void setMamaId(int mamaId) {
        this.mamaId = mamaId;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskText='" + taskText + '\'' +
                ", mamaId=" + mamaId +
                ", start=" + start +
                ", end=" + end +
                ", cost=" + cost +
                '}';
    }
}
