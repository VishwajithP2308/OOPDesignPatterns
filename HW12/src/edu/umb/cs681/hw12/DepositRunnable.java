package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable{
    private BankAccount bankAccount;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private AtomicBoolean done = new AtomicBoolean(false);
    public void setDone(){
        done.getAndSet(true);
    }
    public DepositRunnable(BankAccount bankAccount){
        this.bankAccount = bankAccount;

    }

    @Override
    public void run() {
        while (true){
            if (done.get()){
                System.out.println("Triggering flag based interruption");
                break;
            }
            reentrantLock.lock();
            try {
            bankAccount.deposit(2000);
            }finally {
                reentrantLock.unlock();
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println("Interruption based termination");
            }
        }

    }
}
