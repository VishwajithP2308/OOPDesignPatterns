package edu.umb.cs681.hw12;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().threadId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			System.out.println(exception.getMessage());
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().threadId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().threadId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().threadId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			System.out.println(exception.getMessage());
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }

	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable1 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable2 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable3 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable4 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable5 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable6 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable7 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable8 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable9 = new DepositRunnable(bankAccount);
			DepositRunnable depositRunnable10 = new DepositRunnable(bankAccount);
			Thread depositThread = new Thread(depositRunnable);
			Thread depositThread1 = new Thread(depositRunnable1);
			Thread depositThread2 = new Thread(depositRunnable2);
			Thread depositThread3 = new Thread(depositRunnable3);
			Thread depositThread4 = new Thread(depositRunnable4);
			Thread depositThread5 = new Thread(depositRunnable5);
			Thread depositThread6 = new Thread(depositRunnable6);
			Thread depositThread7 = new Thread(depositRunnable7);
			Thread depositThread8 = new Thread(depositRunnable8);
			Thread depositThread9 = new Thread(depositRunnable9);
			Thread depositThread10 = new Thread(depositRunnable10);

			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable1 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable2 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable3 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable4 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable5 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable6 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable7 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable8 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable9 = new WithdrawRunnable(bankAccount);
			WithdrawRunnable withdrawRunnable10 = new WithdrawRunnable(bankAccount);
			Thread withdrawThread = new Thread(withdrawRunnable);
			Thread withdrawThread1 = new Thread(withdrawRunnable1);
			Thread withdrawThread2 = new Thread(withdrawRunnable2);
			Thread withdrawThread3 = new Thread(withdrawRunnable3);
			Thread withdrawThread4 = new Thread(withdrawRunnable4);
			Thread withdrawThread5 = new Thread(withdrawRunnable5);
			Thread withdrawThread6 = new Thread(withdrawRunnable6);
			Thread withdrawThread7 = new Thread(withdrawRunnable7);
			Thread withdrawThread8 = new Thread(withdrawRunnable8);
			Thread withdrawThread9 = new Thread(withdrawRunnable9);
			Thread withdrawThread10 = new Thread(withdrawRunnable10);

			List<Thread> DepositThreads = new LinkedList<>(List.of(depositThread, depositThread1, depositThread2, depositThread3, depositThread4, depositThread5, depositThread6, depositThread7, depositThread8, depositThread9, depositThread10));
			List<DepositRunnable> DepositRunnables = new LinkedList<>(List.of(depositRunnable,depositRunnable1, depositRunnable2, depositRunnable3, depositRunnable4, depositRunnable5, depositRunnable6, depositRunnable7, depositRunnable8, depositRunnable9,depositRunnable10));
			List<Thread> withdrawThreads = new LinkedList<>(List.of(withdrawThread, withdrawThread1, withdrawThread2, withdrawThread3, withdrawThread4, withdrawThread5, withdrawThread6, withdrawThread7, withdrawThread8, withdrawThread9, withdrawThread10));
			List<WithdrawRunnable> withdrawRunnables = new LinkedList<>(List.of(withdrawRunnable, withdrawRunnable1, withdrawRunnable2, withdrawRunnable3, withdrawRunnable4, withdrawRunnable5, withdrawRunnable6, withdrawRunnable7, withdrawRunnable8, withdrawRunnable9, withdrawRunnable10));

			DepositThreads.forEach((d)->d.start());
			withdrawThreads.forEach((w)->w.start());
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e){}
			DepositRunnables.forEach((dr)->dr.setDone());
			DepositThreads.forEach((thread -> thread.interrupt()));
			withdrawRunnables.forEach((wr)->wr.setDone());
			withdrawThreads.forEach((thread -> thread.interrupt()));
		}


}
