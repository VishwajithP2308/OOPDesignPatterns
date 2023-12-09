package edu.umb.cs681.hw11.fs;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
        public static void main(String[] args) {
            LinkedList<File> sharedFiles = new LinkedList<>();
            ReentrantLock reentrantLock = new ReentrantLock();
            Disk1Runnable disk1Runnable = new Disk1Runnable(reentrantLock);
            Disk2Runnable disk2Runnable = new Disk2Runnable(reentrantLock);
            Disk3Runnable disk3Runnable = new Disk3Runnable(reentrantLock);
            Thread thread1 = new Thread(disk1Runnable);
            Thread thread2 = new Thread(disk2Runnable);
            Thread thread3 = new Thread(disk3Runnable);

            // Start threads
            thread1.start();
            thread2.start();
            thread3.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            disk1Runnable.setDone();
            thread1.interrupt();
            disk2Runnable.setDone();
            thread2.interrupt();
            disk3Runnable.setDone();
            thread3.interrupt();

            try {
                // Wait for threads to finish
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Log or handle the InterruptedException
                e.printStackTrace();
            }
            reentrantLock.lock();
            try {
                sharedFiles.addAll(disk1Runnable.getFilesFromDisk1());
                sharedFiles.addAll(disk2Runnable.getFilesFromDisk2());
                sharedFiles.addAll(disk3Runnable.getFilesFromDisk3());
            } finally {
                reentrantLock.unlock();
            }

            for (File f : sharedFiles) {
                System.out.println(f.getName());
            }
        }
}
