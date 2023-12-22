package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {
    private BankAccount bankAccount;
    private ReentrantLock reentrantLock = new ReentrantLock();
    public WithdrawRunnable(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    private AtomicBoolean done = new AtomicBoolean(false);
    public void setDone(){
        done.getAndSet(true);
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
        bankAccount.withdraw(1000);
        }finally {
            reentrantLock.unlock();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Triggering Interruption based termination");
        }
    }
    }
}
