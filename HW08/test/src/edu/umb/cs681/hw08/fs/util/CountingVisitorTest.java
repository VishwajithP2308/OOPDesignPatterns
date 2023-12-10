package edu.umb.cs681.hw08.fs.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw08.fs.Directory;
import edu.umb.cs681.hw08.fs.File;
import edu.umb.cs681.hw08.fs.FileSystem;
import edu.umb.cs681.hw08.fs.Link;
import edu.umb.cs681.hw08.fs.TestFixtureInitializer;

class CountingVisitorTest {
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
	public void verifyVisitorRoot() {
	    CountingVisitor visitor = new CountingVisitor();
	    prjRoot.accept(visitor);
	    assertEquals(5, visitor.getDirNum());
	    assertEquals(5, visitor.getFileNum());
	    assertEquals(1, visitor.getLinkNum());
	}

	@Test
	public void verifyVisitorSrc() {
	    CountingVisitor visitor = new CountingVisitor();
	    src.accept(visitor);
	    assertEquals(1, visitor.getDirNum());
	    assertEquals(2, visitor.getFileNum());
	    assertEquals(0, visitor.getLinkNum());
	}

	@Test
	public void verifyVisitorlib() {
	    CountingVisitor visitor = new CountingVisitor();
	    lib.accept(visitor);
	    assertEquals(1, visitor.getDirNum());
	    assertEquals(1, visitor.getFileNum());
	    assertEquals(0, visitor.getLinkNum());
	}

	@Test
	public void verifyVisitorTest() {
	    CountingVisitor visitor = new CountingVisitor();
	    test.accept(visitor);
	    assertEquals(2, visitor.getDirNum());
	    assertEquals(1, visitor.getFileNum());
	    assertEquals(0, visitor.getLinkNum());
	}

	@Test
	public void verifyVisitorSrcTest() {
	    CountingVisitor visitor = new CountingVisitor();
	    srcTest.accept(visitor);
	    assertEquals(1, visitor.getDirNum());
	    assertEquals(1, visitor.getFileNum());
	    assertEquals(0, visitor.getLinkNum());
	}
}
