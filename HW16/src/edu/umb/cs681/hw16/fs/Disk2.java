package edu.umb.cs681.hw16.fs;

import edu.umb.cs681.hw16.fs.util.FileCrawlingVisitor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Disk2 implements Runnable{
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


    public  FileSystem getDisk2(){
         Directory disk2Root = new Directory(null, "D:", 0, LocalDateTime.now());
        Directory documents = new Directory(disk2Root, "Documents", 0, LocalDateTime.now());
         Directory downloads = new Directory(disk2Root, "Downloads", 0, LocalDateTime.now());
        File importantDoc = new File(documents, "Important.doc", 2048, LocalDateTime.now());
        Directory photos = new Directory(disk2Root, "Photos", 0, LocalDateTime.now());
         File picture1 = new File(photos, "Pic1.jpg", 3072, LocalDateTime.now());
        Directory users = new Directory(disk2Root, "Users", 0, LocalDateTime.now());
         Link downloadsLink = new Link(users, "DownloadsLink", 0, LocalDateTime.now(), downloads);
        FileSystem Disk2FileSystem = FileSystem.getFileSystem();
        Disk2FileSystem.appendRootDir(disk2Root);
        return Disk2FileSystem;

    }
    private ConcurrentLinkedQueue<File> sharedList = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        FileSystem fs = getDisk2();
        Directory root = fs.getRootDirs().get(0);
        Directory documents = root.getSubDirectories().get(0);
        ThreadLocal<FileCrawlingVisitor> crawler = new ThreadLocal<>();
        FileCrawlingVisitor crawlingVisitor = new FileCrawlingVisitor();
        crawler.set(crawlingVisitor);
        for (int i = 0; i < 3; i++){
            lock.lock();
            try {
                if(done){
                    System.out.println("Stopping Disk2 Crawling");
                    break;
                }
            }finally {
                lock.unlock();
            }
            documents.accept(crawler.get());
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
