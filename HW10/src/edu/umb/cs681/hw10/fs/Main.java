package edu.umb.cs681.hw10.fs;
import edu.umb.cs681.hw10.fs.FSDataRunnable;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i <= 11; i++){
            FSDataRunnable fsDataRunnable = new FSDataRunnable();
            Thread thread = new Thread(fsDataRunnable);
            thread.start();
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            fsDataRunnable.setDone();
            thread.interrupt();

        }
    }
}