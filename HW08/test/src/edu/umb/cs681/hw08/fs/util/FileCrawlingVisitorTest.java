package edu.umb.cs681.hw08.fs.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw08.fs.Directory;
import edu.umb.cs681.hw08.fs.File;
import edu.umb.cs681.hw08.fs.FileSystem;
import edu.umb.cs681.hw08.fs.Link;
import edu.umb.cs681.hw08.fs.TestFixtureInitializer;

class FileCrawlingVisitorTest {
	static FileSystem fs;
    static LocalDateTime localTime;
    static Directory prjRoot;
	static Directory src;
	static Directory lib;
	static Directory test;
	static Directory srcTest;
    static File x, a, b, c, d;
    static Link y;
    
    @BeforeAll
    public static void setUp() {
        localTime = LocalDateTime.of(2023, 5, 7, 12, 30, 0);
        fs = TestFixtureInitializer.createFS(localTime);
        prjRoot = fs.getRootDirs().get(0);
        src = (Directory) prjRoot.getChildren().get(0);
        lib = (Directory) prjRoot.getChildren().get(1);
        test = (Directory) prjRoot.getChildren().get(2);
        x = (File) prjRoot.getChildren().get(3);
        a = (File) src.getChildren().get(0);
        b = (File) src.getChildren().get(1);
        c = (File) lib.getChildren().get(0);
        srcTest = (Directory) test.getChildren().get(0);
        d = (File) srcTest.getChildren().get(0);
        y = prjRoot.getLinks().get(0);
    }

	
	 @Test
	 public void verifyFilesInRoot() {
		 FileCrawlingVisitor visitor = new FileCrawlingVisitor();
		 prjRoot.accept(visitor);
		 
		 LinkedList<File> files = visitor.getFile();

		 assertEquals(5, files.size());
		 assertTrue(files.contains(x));
		 assertTrue(files.contains(a));
		 assertTrue(files.contains(b));
		 assertTrue(files.contains(c));
		 assertTrue(files.contains(d));
	 }
	 
	 @Test
	 public void verifyFilesInSrc() {
		 FileCrawlingVisitor visitor = new FileCrawlingVisitor();
		 src.accept(visitor);
		 
		 LinkedList<File> files = visitor.getFile();

		 assertEquals(2, files.size());
		 assertTrue(files.contains(a));
		 assertTrue(files.contains(b));

	 }
	 
	 @Test
	 public void verifyFilesInLib() {
		 FileCrawlingVisitor visitor = new FileCrawlingVisitor();
		 lib.accept(visitor);
		 
		 LinkedList<File> files = visitor.getFile();

		 assertEquals(1, files.size());
		 assertTrue(files.contains(c));


	 }
	 
	 @Test
	 public void verifyFilesInTest() {
		 FileCrawlingVisitor visitor = new FileCrawlingVisitor();
		 test.accept(visitor);
		 
		 LinkedList<File> files = visitor.getFile();

		 assertEquals(1, files.size());
		 assertTrue(files.contains(d));


	 }


}
