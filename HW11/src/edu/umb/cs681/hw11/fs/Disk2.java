package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;

public class Disk2 {
    public static FileSystem getDisk2(){
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
}
