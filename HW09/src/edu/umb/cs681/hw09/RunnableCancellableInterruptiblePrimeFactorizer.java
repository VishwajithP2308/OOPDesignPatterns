package edu.umb.cs681.hw09;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnablePrimeFactorizer{
    private final AtomicBoolean done = new AtomicBoolean(false);
    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
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
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            System.out.println("Interruption based interruption triggered");
        }
    }

    public static void main(String[] args){
        System.out.println("Factorization of 36");
        RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(runnable);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
        thread.start();
        runnable.setDone();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");

        List<RunnableCancellableInterruptiblePrimeFactorizer> runnables = new LinkedList<>();
        List<Thread> threads = new LinkedList<>();

        // Factorization of 2489 with two threads
        System.out.println("Factorization of 2489");
        runnables.add( new RunnableCancellableInterruptiblePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 ));
        runnables.add( new RunnableCancellableInterruptiblePrimeFactorizer(2489, 1+(long)Math.sqrt(2489)/2, (long)Math.sqrt(2489) ));

        thread = new Thread(runnables.get(0));
        threads.add(thread);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

        thread = new Thread(runnables.get(1));
        threads.add(thread);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

        threads.forEach( (t)->t.start() );
        runnables.get(0).setDone();
        runnables.get(1).setDone();
        threads.forEach(t->t.interrupt());
        threads.forEach( (t)->{	try{t.join();}
        catch(InterruptedException e){e.printStackTrace(); }} );

        LinkedList<Long> factors2 = new LinkedList<Long>();
        runnables.forEach( (factorizer) -> factors2.addAll(factorizer.getPrimeFactors()) );
        System.out.println("Final result: " + factors2);
    }
}
