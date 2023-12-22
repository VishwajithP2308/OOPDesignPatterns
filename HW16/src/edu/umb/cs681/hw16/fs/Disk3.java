package edu.umb.cs681.hw16.fs;

import edu.umb.cs681.hw16.fs.util.FileCrawlingVisitor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Disk3 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;
    public void setDone(){
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }

    public  FileSystem getDisk3(){
         Directory disk3Root = new Directory(null, "E:", 0, LocalDateTime.now());
         Directory photos = new Directory(disk3Root, "Photos", 0, LocalDateTime.now());
         Directory music = new Directory(disk3Root, "Music", 0, LocalDateTime.now());
         File song1 = new File(disk3Root, "Song1.mp3", 5120, LocalDateTime.now());
         File song2 = new File(music, "Song2.mp3", 4096, LocalDateTime.now());
         Link musicLink = new Link(disk3Root, "MyMusic", 0, LocalDateTime.now(), music);
         Directory users = new Directory(disk3Root, "Users", 0, LocalDateTime.now());
         Link picturesLink = new Link(users, "PicturesLink", 0, LocalDateTime.now(), photos);
        FileSystem disk3FileSystem = FileSystem.getFileSystem();
        disk3FileSystem.appendRootDir(disk3Root);
        return disk3FileSystem;

    }
    private ConcurrentLinkedQueue<File> sharedList = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        FileSystem fs = getDisk3();
        Directory disk3Root = fs.getRootDirs().get(0);
        ThreadLocal<FileCrawlingVisitor> crawler = new ThreadLocal<>();
        FileCrawlingVisitor crawlingVisitor = new FileCrawlingVisitor();
        crawler.set(crawlingVisitor);
        for (int i = 0; i < 3; i++){
            lock.lock();
            try {
                if(done){
                    System.out.println("Stopping Disk3 Crawling");
                    break;
                }
            }finally {
                lock.unlock();
            }
            disk3Root.accept(crawler.get());
            sharedList.addAll(crawler.get().getFile());
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Interruption based exception triggered");
        }
    }

    public ConcurrentLinkedQueue<File> getSharedList() {
        return sharedList;
    }
}
