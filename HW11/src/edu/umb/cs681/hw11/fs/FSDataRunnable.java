package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class FSDataRunnable implements Runnable{
    private AtomicBoolean done = new AtomicBoolean(false);
    public void setDone(){
        done.getAndSet(true);
    }
    @Override
    public void run() {
        while (true){
            if (done.get()){
                System.out.println("Flag Based termination Triggered");
                break;
            }
            LocalDateTime localTime = LocalDateTime.of(2023, 5, 7, 12, 30, 0);
            FileSystem fs = FileSystem.getFileSystem();
            Directory prjRoot = new Directory(null, "prjRoot", 0, localTime);
            Directory src = new Directory(prjRoot, "src", 0, localTime);
            Directory lib = new Directory(prjRoot, "lib", 0, localTime);
            Directory test = new Directory(prjRoot, "test", 0, localTime);
            File x = new File(prjRoot, "x", 0, localTime);
            File a = new File(src, "a", 5, localTime);
            File b = new File(src, "b", 30, localTime);
            File c = new File(lib, "c", 15, localTime);
            Directory srcTest = new Directory(test, "srcTest", 0, localTime);
            File d = new File(srcTest, "d", 15, localTime);
            Link y = new Link(prjRoot, "y", 0, localTime, srcTest);
            fs.appendRootDir(prjRoot);
            System.out.println(prjRoot.getTotalSize());
            System.out.println(lib.getTotalSize());
            System.out.println(test.getTotalSize());
            System.out.println(src.getTotalSize());
            System.out.println(srcTest.getTotalSize());
            System.out.println(prjRoot.countChildren());
            System.out.println(src.countChildren());
            System.out.println(lib.countChildren());
            System.out.println(test.countChildren());
            System.out.println(srcTest.countChildren());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Interruption based termination triggered");
            }
        }

    }
}
