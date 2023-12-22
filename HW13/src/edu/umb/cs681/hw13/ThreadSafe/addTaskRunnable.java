package edu.umb.cs681.hw13.ThreadSafe;

import edu.umb.cs681.hw13.JobScheduler;

import java.util.concurrent.locks.ReentrantLock;

public class addTaskRunnable implements Runnable{
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private JobScheduler scheduler;
    private String name;
    private String date;
    private int priority;
    public void setDone(){
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }
    public addTaskRunnable(JobScheduler scheduler, String name, String date, int priority){
        this.scheduler = scheduler;
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (done) {
                    System.out.println("Flag based interruption triggered");
                    break;
                }
                scheduler.addTask(name, date, priority);
            }finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Interruption based exception triggered");
            }

        }

    }
}
