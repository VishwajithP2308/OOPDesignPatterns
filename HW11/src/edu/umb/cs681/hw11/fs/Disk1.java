package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;

public class Disk1 {
    public static FileSystem getDisk1(){
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
}
