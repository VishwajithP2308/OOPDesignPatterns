package edu.umb.cs681.hw08.fs.util;

import static org.junit.Assert.assertArrayEquals;
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

class FileSearchVisitorTest {
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
	public void verifyFileSize() {
		FileSearchVisitor visitor = new FileSearchVisitor();
		visitor.setFileName("a");
		prjRoot.accept(visitor);
		
		visitor.setFileName("b");
		prjRoot.accept(visitor);
		
		int actual = visitor.getFoundFiles().size();
		assertSame(2,actual);
	}
	
	
	@Test
	public void verifyMultipleFiles() {
		LinkedList<File> expected = new LinkedList<File>();
		expected.add(a);
		expected.add(b);
		expected.add(c);
		
		FileSearchVisitor visitor = new FileSearchVisitor();
		visitor.setFileName("a");
		prjRoot.accept(visitor);
		
		visitor.setFileName("b");
		prjRoot.accept(visitor);
		
		visitor.setFileName("c");
		prjRoot.accept(visitor);
		
		LinkedList<File> actual = visitor.getFoundFiles();
		assertArrayEquals(expected.toArray(),actual.toArray());
	}

}
