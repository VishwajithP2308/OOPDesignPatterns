package edu.umb.cs681.hw08.fs;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
//import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FileTest {
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
	
	
	private String[] fileToStringArray(File f) {
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(),f.getParent().getName(), Integer.toString(f.getSize()),
				f.getCreationTime().toString(), };
		return fileInfo;
	}
	
	@Test
	public void verifyFile() {
		assertTrue(x.isFile());
		assertTrue(a.isFile());
		assertTrue(b.isFile());
		assertTrue(c.isFile());
		assertTrue(d.isFile());
	}
	
	@Test
    public void verifyFileX() {
		String[] expected = { "false", "x", "prjRoot","0", localTime.toString()};
		File actual = x;
//		System.out.println("Directory : " + Arrays.toString(fileToStringArray(actual)));
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    public void verifyFileA() {
		String[] expected = { "false", "a", "src","5", localTime.toString()};
		File actual = a;
//		System.out.println("Directory : " + Arrays.toString(fileToStringArray(actual)));
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    public void verifyFileB() {
		String[] expected = { "false", "b", "src","30", localTime.toString()};
		File actual = b;
//		System.out.println("Directory : " + Arrays.toString(fileToStringArray(actual)));
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    public void verifyFileC() {
		String[] expected = { "false", "c", "lib","15", localTime.toString()};
		File actual = c;
//		System.out.println("Directory : " + Arrays.toString(fileToStringArray(actual)));
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
    public void verifyFileD() {
		String[] expected = { "false", "d", "srcTest","15", localTime.toString()};
		File actual = d;
//		System.out.println("Directory : " + Arrays.toString(fileToStringArray(actual)));
		assertArrayEquals(expected, fileToStringArray(actual));
	}

}
