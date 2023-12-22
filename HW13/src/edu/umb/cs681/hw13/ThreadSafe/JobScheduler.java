package edu.umb.cs681.hw13.ThreadSafe;

import edu.umb.cs681.hw13.ThreadSafe.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class JobScheduler {
    private List<Task> tasks;
    private ReentrantLock lock=new ReentrantLock();

    public JobScheduler() {
        this.tasks = new ArrayList<>();
    }


    public void addTask(String name, String date, int priority) {
        lock.lock();
        try {
            Task task = new Task(name, date, priority);
            tasks.add(task);
            System.out.println("Task added: " + task);
        } catch (ParseException e) {
            System.out.println("Error parsing date for task: " + name);
        }
        finally {
            lock.unlock();
        }
    }


    public void removeTask(String name) {
        lock.lock();
        try{
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
        }
        finally{
            lock.unlock();
        }
    }


    // Method to display all tasks in the scheduler, sorted by date and priority using Stream API
    public void displayTasks() {
        lock.lock();
        try {
            tasks.sort((task1, task2) -> {
                int dateComparison = task1.getDate().compareTo(task2.getDate());
                return (dateComparison != 0) ? dateComparison : Integer.compare(task1.getPriority(), task2.getPriority());
            });

            tasks.forEach(System.out::println);
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

}