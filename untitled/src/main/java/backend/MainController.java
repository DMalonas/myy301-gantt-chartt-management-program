package main.java.backend;

import main.java.dom2app.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController implements IMainController{
    @Override
    public SimpleTableModel load(String fileName, String delimiter) {

        List<String> fileLines = readGraphFromFile(fileName);

        List<String[]> segragatedLines =  new ArrayList<>();// {
                                                             // ["100", "Prepare", "Fry", "0"],
                                             //                 ["abc", de"]
        //                                                     }

        ComplexTasksManager complexTasksManager = new ComplexTasksManager();
        for (int i = 0; i < fileLines.size(); i++) {
            String[] splitLine = fileLines.get(i).split("\t");
            segragatedLines.add(splitLine);
        }
        addComplexTasks(segragatedLines, complexTasksManager);
        addSimpleTasks(segragatedLines, complexTasksManager);
        calculateStartEndCostForComplexTasks(complexTasksManager);
        sortTopLvlTasksByStartTime(complexTasksManager);
//        sortTasksWithSameStartTimeById(complexTasksManager);
//        for (int i = 0; i < segragatedLines.size(); i++) {
//            int length = segragatedLines.get(i).length;
//            String[] data = segragatedLines.get(i);
//            int taskId = Integer.parseInt(data[0]);
//            String taskText = data[1];
//            int mamaId = Integer.parseInt(data[2]);
//            Task task = null;
//            if (length == 3) {
//                task = new ComplexTask(taskId, taskText, mamaId, 0, 0, 0);
//            } else if (length == 6) {
//                int start = Integer.parseInt(data[3]);
//                int end = Integer.parseInt(data[4]);
//                int cost = Integer.parseInt(data[5]);
//                task = new SimpleTask(taskId, taskText, mamaId, start, end, cost);
//            }
//            if (task.getMamaId() == 0) { //New complex task discovered
//                List<Task> complexTasks = complexTasksManager.getComplexTasks();
//                if (!complexTasks.contains(task)) {
//                    complexTasksManager.addComplexTask(task);
//                }
//            } else { //simple task discovered
//                List<Task> complexTasks = complexTasksManager.getComplexTasks();
//                boolean complexTaskSimpleTaskBelongsToFound = false;
//                for (int j = 0; j < complexTasks.size(); j++) { //Check If we have already found the complex task in which simple task falls under
//                    ComplexTask complexTask = (ComplexTask) complexTasks.get(j);
//                    if (complexTask.getTaskId() == task.getMamaId()) {
//                        complexTaskSimpleTaskBelongsToFound = true;
//                        complexTask.addSimpleTask(task);
//                        complexTasksManager.addComplexTask(complexTask);
//                    }
//                }
//                if (complexTaskSimpleTaskBelongsToFound == false) {
//                    Task updatedOrNewComplexTask = new ComplexTask();
//
//                    complexTasksManager.addComplexTask(updatedOrNewComplexTask);
//                }

//            }
//        }
        int a = 5;
        return null;
    }

//    private void sortTasksWithSameStartTimeById(ComplexTasksManager complexTasksManager) {
////        complexTasksManager.getComplexTasks().forEach(complexTask -> {
////            complexTask.setTasks(complexTask.getTasks().stream()
////                    .sorted(Comparator.comparingInt(Task::getStart)).collect(Collectors.toList()));
////        });
//
//        List<ComplexTask> complexTasks = complexTasksManager.getComplexTasks();
//        for (ComplexTask complexTask : complexTasks) {
//            List<Task> tasks = complexTask.getTasks();
//            for (int i = 0; i < tasks.size(); i++) {
//                Task taskToCheckAgainstAllOtherTasks = tasks.get(i);
//                List<Task> tasksWithStartTimeEqualWithTaskUnderCheck = new ArrayList<>();
//                for (int j = i + 1; j < tasks.size(); j++) {
//                    Task task = tasks.get(j);
//                    if (taskToCheckAgainstAllOtherTasks.getStart() == task.getStart()) {
//                        tasksWithStartTimeEqualWithTaskUnderCheck.add(task);
//                    }
//                }
//                if (tasksWithStartTimeEqualWithTaskUnderCheck.size() != 0) {
//                    tasksWithStartTimeEqualWithTaskUnderCheck.add(taskToCheckAgainstAllOtherTasks);
//                    tasksWithStartTimeEqualWithTaskUnderCheck.sort(Comparator.comparingInt(Task::getStart));
//                    for (int l = 0; l < tasks.size(); l++) {
//                        if (tasks.get(l).getStart() == tasks.get)
//                    }
//                }
//            }
//
//        }
//    }

    private void calculateStartEndCostForComplexTasks(ComplexTasksManager complexTasksManager) {
        List<ComplexTask> complexTasks = complexTasksManager.getComplexTasks();
        List<Task> complexTasksCopy = new ArrayList<>(complexTasks);
        for (int i = 0; i < complexTasks.size(); i++) {
            Task task = complexTasksCopy.get(i);
            if (task.getMamaId() == 0) {
                ComplexTask complexTask = (ComplexTask) task;
                List<Task> tasks = complexTask.getTasks();
                int startTime = calculateStartTime(tasks);
                int endTime = calculateEndTime(tasks);
                int cost = calculateCost(tasks);
                complexTask.setCost(cost);
                complexTask.setStart(startTime);
                complexTask.setEnd(endTime);
                complexTasksManager.addOrUpdateComplexTask(complexTask);
            }
        }
    }

    private int calculateCost(List<Task> tasks) {
        int cost = 0;
        for (int i = 0; i < tasks.size(); i++) {
            cost += tasks.get(i).getCost();
        }
        return cost;
    }

    private int calculateEndTime(List<Task> tasks) {
        int maxEndTime = Integer.MIN_VALUE;
        for (int j = 0; j < tasks.size(); j++) {
            int end = tasks.get(j).getEnd();
            if (maxEndTime < end) {
                maxEndTime = end;
            }
        }
        return maxEndTime;
    }

    private int calculateStartTime(List<Task> tasks) {
        int minTime = Integer.MAX_VALUE;
        for (int j = 0; j < tasks.size(); j++) {
            int start = tasks.get(j).getStart();
            if (minTime > start) {
                minTime = start;
            }
        }
        return minTime;
    }


    private static Comparator<Task> taskByStartComparator = new Comparator<Task>() {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getStart() - task2.getStart();
        }
    };

    private void sortTopLvlTasksByStartTime(ComplexTasksManager complexTasksManager) {
        complexTasksManager.sortByStartTime(complexTasksManager);


        List<ComplexTask> complexTasks = complexTasksManager.getComplexTasks();
//        for (int i = 0; i < complexTasks.size(); i++) {
//            ComplexTask complexTask = complexTasks.get(i);
//            List<Task> tasks = complexTask.getTasks();
//            Collections.sort(tasks, taskByStartComparator);
//            complexTask.setTasks(tasks);
//            complexTasksManager.addOrUpdateComplexTask(complexTask);
//        }


//        for (int i = 0; i < complexTasks.size(); i++) {
//            ComplexTask complexTask = complexTasks.get(i);
//            List<Task> tasks = complexTask.getTasks();
//            tasks.sort(Comparator.comparing(Task::getStart));
//            complexTask.setTasks(tasks);
//            complexTasksManager.addOrUpdateComplexTask(complexTask);
//            int a = 5;
//        }

//        for (ComplexTask complexTask : complexTasksManager.getComplexTasks()) {
//            complexTask.getTasks().stream()
//                    .sorted(Comparator.comparing(Task::getStart))
//                    .collect(Collectors.toList());
//        }
//        int a = 5;

        Comparator<Task> compareTaskByStartTime = Comparator.comparingInt(Task::getStart);
        Comparator<Task> compareTaskByTaskId = Comparator.comparingInt(Task::getTaskId);

//        complexTasksManager.getComplexTasks().forEach(complexTask -> {
//            complexTask.setTasks(complexTask.getTasks().stream()
//                    .sorted(compareTaskByStartTime.thenComparing(compareTaskByTaskId))
//                    .collect(Collectors.toList()));
//        });

        complexTasksManager.getComplexTasks().forEach(complexTask ->  {
            complexTask.getTasks().sort(compareTaskByStartTime.thenComparing(compareTaskByTaskId));
        });
    }

    private void addSimpleTasks(List<String[]> segragatedLines, ComplexTasksManager complexTasksManager) {
        for (int i = 0; i < segragatedLines.size(); i++) {
            String[] splitLine = segragatedLines.get(i);
            if (splitLine.length == 6) {
                int taskId = Integer.parseInt(splitLine[0]);
                String taskText = splitLine[1];
                int mamaId = Integer.parseInt(splitLine[2]);
                int start = Integer.parseInt(splitLine[3]);
                int end = Integer.parseInt(splitLine[4]);
                int cost = Integer.parseInt(splitLine[5]);
                List<ComplexTask> complexTasks = complexTasksManager.getComplexTasks();
                for (int j = 0; j < complexTasks.size(); j++) {
                    ComplexTask complexTask = (ComplexTask) complexTasks.get(j);
                    if (complexTask.getTaskId() == mamaId
                            && complexTask.getTasks()
                            .stream()
                            .noneMatch(task -> task.getTaskId() == taskId)) {
                        Task task = new SimpleTask(taskId, taskText, mamaId, start, end, cost);
                        complexTask.addSimpleTask(task);
                        complexTasksManager.addOrUpdateComplexTask(complexTask);
                    }
                }
            }
        }
    }

    private void addComplexTasks(List<String[]> segragatedLines, ComplexTasksManager complexTasksManager) {
        for (int i = 0; i < segragatedLines.size(); i++) {
            String[] splitLine = segragatedLines.get(i);
            if (splitLine.length == 3) {
                int taskId = Integer.parseInt(splitLine[0]);
                String taskText = splitLine[1];
                int mamaId = Integer.parseInt(splitLine[2]);
                if (mamaId == 0) {
                    ComplexTask task = new ComplexTask(taskId, taskText, mamaId);
                    complexTasksManager.addOrUpdateComplexTask(task);
                }
            }
        }
    }

    @Override
    public SimpleTableModel getTasksByPrefix(String prefix) {
        return null;
    }

    @Override
    public SimpleTableModel getTaskById(int id) {
        return null;
    }

    @Override
    public SimpleTableModel getTopLevelTasks() {
        return null;
    }

    @Override
    public int createReport(String path, ReportType type) {
        return 0;
    }


    public List<String> readGraphFromFile(String file) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
