package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;

public class Disk3 {
    public static FileSystem getDisk3(){
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
}
