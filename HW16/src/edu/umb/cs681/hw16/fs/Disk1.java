package edu.umb.cs681.hw16.fs;

import edu.umb.cs681.hw16.fs.util.FileCrawlingVisitor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Disk1 implements Runnable {
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

    public  FileSystem getDisk1(){
         Directory disk1Root = new Directory(null, "C:", 0, LocalDateTime.now());
         Directory programFiles = new Directory(disk1Root, "Program Files", 0, LocalDateTime.now());
         Directory users = new Directory(disk1Root, "Users", 0, LocalDateTime.now());
         File readme = new File(disk1Root, "README.txt", 1024, LocalDateTime.now());
         File application1 = new File(programFiles, "App1.exe", 4096, LocalDateTime.now());
         File document1 = new File(users, "Document1.docx", 2048, LocalDateTime.now());
         Link documentsLink = new Link(users, "DocumentsLink", 0, LocalDateTime.now(), document1);
        FileSystem disk1FileSystem = FileSystem.getFileSystem();
        disk1FileSystem.appendRootDir(disk1Root);
        return disk1FileSystem;
    }

    private ConcurrentLinkedQueue<File> sharedList = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        FileSystem fs = getDisk1();
        Directory root = fs.getRootDirs().get(0);
        Directory users = root.getSubDirectories().get(1);
        ThreadLocal<FileCrawlingVisitor> crawler = new ThreadLocal<>();
        FileCrawlingVisitor crawlingVisitor = new FileCrawlingVisitor();
        crawler.set(crawlingVisitor);
        for (int i = 0; i < 3; i++){
            lock.lock();
            try {
                if(done){
                    System.out.println("Stopping Disk1 Crawling");
                    break;
                }
            }finally {
                lock.unlock();
            }
            users.accept(crawler.get());
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
