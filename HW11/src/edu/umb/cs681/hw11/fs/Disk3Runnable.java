package edu.umb.cs681.hw11.fs;

import edu.umb.cs681.hw11.fs.util.FileCrawlingVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Disk3Runnable implements Runnable{
    private AtomicBoolean done = new AtomicBoolean(false);

    private List<File> filesFromDisk3 = new ArrayList<>();
    private ReentrantLock reentrantLock;

    public Disk3Runnable(ReentrantLock reentrantLock){
        this.reentrantLock = reentrantLock;
    }

    public void setDone() {
        done.getAndSet(true);
    }

    @Override
    public void run() {
        while (true){
            if (done.get()){
                System.out.println("Flag Based interruption triggered");
                break;
            }
            FileCrawlingVisitor fileCrawlingVisitor = FileCrawlingVisitor.getInstance();
            FileSystem disk3 = Disk3.getDisk3();
            Directory root = disk3.getRootDirs().get(0);
            root.accept(fileCrawlingVisitor);
            reentrantLock.lock();
            try {
                filesFromDisk3.addAll(fileCrawlingVisitor.getFiles());
            }finally {
                reentrantLock.unlock();
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println("Interruption based termination triggered");
            }
        }
    }

    public List<File> getFilesFromDisk3() {
        return filesFromDisk3;
    }
}
