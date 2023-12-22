package edu.umb.cs681.hw13;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JobScheduler {
    private List<Task> tasks;

    public JobScheduler() {
        this.tasks = new ArrayList<>();
    }


    public void addTask(String name, String date, int priority) {
        try {
            Task task = new Task(name, date, priority);
            tasks.add(task);
            System.out.println("Task added: " + task);
        } catch (ParseException e) {
            System.out.println("Error parsing date for task: " + name);
        }
    }


    public void removeTask(String name) {
        try {
            Task taskToRemove = null;
            for (Task task : tasks) {
                if (task.getName().equals(name)) {
                    taskToRemove = task;
                    break;
                }
            }
            if (taskToRemove != null) {
                tasks.remove(taskToRemove);
                System.out.println("Task removed: " + taskToRemove);
            } else {
                System.out.println("Task not found: " + name);
            }
        }catch (Exception e){}



    }


    // Method to display all tasks in the scheduler, sorted by date and priority using Stream API
    public void displayTasks() {
        try {
            tasks.sort((task1, task2) -> {
                int dateComparison = task1.getDate().compareTo(task2.getDate());
                return (dateComparison != 0) ? dateComparison : Integer.compare(task1.getPriority(), task2.getPriority());
            });
        tasks.forEach(System.out::println);
        }catch (Exception e){}


    }

}