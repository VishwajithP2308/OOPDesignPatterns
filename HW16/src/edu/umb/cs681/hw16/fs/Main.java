package edu.umb.cs681.hw16.fs;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
        public static void main(String[] args) {
            ConcurrentLinkedQueue<File> sharedFiles = new ConcurrentLinkedQueue<>();
            Disk1 disk1 = new Disk1();
            Disk2 disk2 = new Disk2();
            Disk3 disk3 = new Disk3();
            Thread thread1 = new Thread(disk1);
            Thread thread2 = new Thread(disk2);
            Thread thread3 = new Thread(disk3);
            thread1.start();
            thread2.start();
            thread3.start();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){}
            disk1.setDone();
            thread1.interrupt();
            disk2.setDone();
            thread2.interrupt();
            disk3.setDone();
            thread3.interrupt();
            sharedFiles.addAll(disk1.getSharedList());
            sharedFiles.addAll(disk2.getSharedList());
            sharedFiles.addAll(disk3.getSharedList());
            for(File f: sharedFiles){
                System.out.println(f.getName());
            }


        }
}
