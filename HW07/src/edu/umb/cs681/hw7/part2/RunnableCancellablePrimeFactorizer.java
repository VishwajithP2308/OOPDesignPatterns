package edu.umb.cs681.hw7.part2;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer implements Runnable{
    private final AtomicBoolean done = new AtomicBoolean(false);
    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }
    public void setDone(){
        done.getAndSet(true);
    }
    public void generatePrimeFactors() {
        long divisor = from;
        while( dividend != 1 && divisor <= to ){
            if (done.get()){
                System.out.println("Flag based interruption triggered");
                System.out.println("Clearing factors");
                factors.clear();
                break;
            }
            if( divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
            }
            if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            }else {
                if(divisor==2){ divisor++; }
                else{ divisor += 2; }
            }
        }
    }

    @Override
    public void run(){
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().threadId() + " generated " + factors);
    }
    public static void main(String[] args){
        System.out.println("Factorization of 36");
        RunnableCancellablePrimeFactorizer runnable = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(runnable);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
        thread.start();
        runnable.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
    }
}
