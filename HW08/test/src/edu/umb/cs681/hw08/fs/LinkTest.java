package edu.umb.cs681.hw08.fs;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LinkTest {
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
	public void verifyLink() {
		assertTrue(y.isLink());
    }

	
	@Test
	public void verifyLinkTarget() {
		assertSame(srcTest, y.getTarget());
	}
	
	@Test
	public void verifyLinkInParent() {
	    assertTrue(prjRoot.getLinks().contains(y));
	}
	
	@Test
	public void verifyLinkIsNotDirectory() {
	    assertFalse(y.isDirectory());
	}

	@Test
	public void verifyLinkIsNotFile() {
	    assertFalse(y.isFile());
	}
	@Test
	public void verifyLinkParent() {
	    assertEquals(prjRoot, y.getParent());
	}

	@Test
	public void verifyLinkName() {
	    assertEquals("y", y.getName());
	}

	@Test
	public void verifyLinkSize() {
	    assertEquals(0, y.getSize());
	}

	@Test
	public void verifyLinkCreationTime() {
	    assertEquals(localTime, y.getCreationTime());
	}



}
