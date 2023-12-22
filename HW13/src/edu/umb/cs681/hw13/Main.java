package edu.umb.cs681.hw13;

public class Main {
    public static void main(String[] args){
        JobScheduler scheduler = new JobScheduler();

        // Creating Runnable objects for additional random tasks
        addTaskRunnable addTaskRunnable1 = new addTaskRunnable(scheduler, "RandomTask1", "2023-05-20", 2);
        removeTaskRunnable removeTaskRunnable2 = new removeTaskRunnable(scheduler, "Task2");
        displayTaskRunnable displayTasksRunnable3 = new displayTaskRunnable(scheduler);
        addTaskRunnable addTaskRunnable4 = new addTaskRunnable(scheduler, "RandomTask2", "2023-06-10", 1);
        removeTaskRunnable removeTaskRunnable5 = new removeTaskRunnable(scheduler, "RandomTask1");
        addTaskRunnable addTaskRunnable6 = new addTaskRunnable(scheduler, "RandomTask3", "2023-07-15", 3);
        displayTaskRunnable displayTasksRunnable7 = new displayTaskRunnable(scheduler);
        removeTaskRunnable removeTaskRunnable8 = new removeTaskRunnable(scheduler, "RandomTask2");

        // Creating threads for additional random tasks using the Runnable objects
        Thread randomTaskThread1 = new Thread(addTaskRunnable1);
        Thread randomTaskThread2 = new Thread(removeTaskRunnable2);
        Thread randomTaskThread3 = new Thread(displayTasksRunnable3);
        Thread randomTaskThread4 = new Thread(addTaskRunnable4);
        Thread randomTaskThread5 = new Thread(removeTaskRunnable5);
        Thread randomTaskThread6 = new Thread(addTaskRunnable6);
        Thread randomTaskThread7 = new Thread(displayTasksRunnable7);
        Thread randomTaskThread8 = new Thread(removeTaskRunnable8);

        // Start the threads for additional random tasks
        randomTaskThread1.start();
        randomTaskThread2.start();
        randomTaskThread3.start();
        randomTaskThread4.start();
        randomTaskThread5.start();
        randomTaskThread6.start();
        randomTaskThread7.start();
        randomTaskThread8.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        addTaskRunnable1.setDone();
        removeTaskRunnable2.setDone();
        displayTasksRunnable3.setDone();
        addTaskRunnable4.setDone();
        removeTaskRunnable5.setDone();
        addTaskRunnable6.setDone();
        displayTasksRunnable7.setDone();
        removeTaskRunnable8.setDone();

        randomTaskThread1.interrupt();
        randomTaskThread2.interrupt();
        randomTaskThread3.interrupt();
        randomTaskThread4.interrupt();
        randomTaskThread5.interrupt();
        randomTaskThread6.interrupt();
        randomTaskThread7.interrupt();
        randomTaskThread8.interrupt();
    }
}
