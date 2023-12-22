package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class removeTaskRunnable implements Runnable {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private JobScheduler scheduler;
    private String name;
    public void setDone(){
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }
    public removeTaskRunnable(JobScheduler scheduler, String name){
        this.scheduler = scheduler;
        this.name = name;
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
                scheduler.removeTask(name);
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
