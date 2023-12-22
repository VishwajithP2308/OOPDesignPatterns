package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class displayTaskRunnable implements Runnable{
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private JobScheduler scheduler;
    public displayTaskRunnable(JobScheduler scheduler){
        this.scheduler = scheduler;
    }
    public void setDone(){
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
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
                scheduler.displayTasks();
            }catch (Exception e){}finally {
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
