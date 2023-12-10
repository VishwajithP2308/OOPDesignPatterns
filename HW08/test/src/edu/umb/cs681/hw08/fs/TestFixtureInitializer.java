package edu.umb.cs681.hw08.fs;

import java.time.LocalDateTime;

public class TestFixtureInitializer {

    public static FileSystem createFS(LocalDateTime localTime) {
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
        
        return fs;
    }
}
