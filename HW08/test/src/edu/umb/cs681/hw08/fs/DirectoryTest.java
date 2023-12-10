package edu.umb.cs681.hw08.fs;

import static org.junit.jupiter.api.Assertions.*;

import edu.umb.cs681.hw08.fs.*;
import java.time.LocalDateTime;
//import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DirectoryTest {
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

	
	private String[] dirToStringArray(Directory d) {
		String parentName = null;
		Directory parent = d.getParent();
		if (parent != null) {
			parentName = parent.getName();
		}
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), Integer.toString(d.getSize()),
				d.getCreationTime().toString(),parentName, Integer.toString(d.countChildren()),Integer.toString(d.getTotalSize()) };
		return dirInfo;
	}
	
	@Test
	public void verifyDirectoryEqualityRoot(){
		String[] expected = {"true", "prjRoot", "0", localTime.toString(), null, "5","65" };
		Directory actual = prjRoot; 
//		System.out.println("Directory : " + Arrays.toString(dirToStringArray(actual)));
		assertArrayEquals(expected,dirToStringArray(actual)); 
		}
	
	@Test
	public void verifyDirectoryEqualitySrc(){
		String[] expected = {"true", "src", "0", localTime.toString(), "prjRoot", "2","35" };
		Directory actual = src; 
//		System.out.println("Directory : " + Arrays.toString(dirToStringArray(actual)));
		assertArrayEquals(expected,dirToStringArray(actual)); 
		}
	
	@Test
	public void verifyDirectoryEqualitylib(){
		String[] expected = {"true", "lib", "0", localTime.toString(), "prjRoot", "1","15" };
		Directory actual = lib; 
//		System.out.println("Directory : " + Arrays.toString(dirToStringArray(actual)));
		assertArrayEquals(expected,dirToStringArray(actual)); 
		}
	
	@Test
	public void verifyDirectoryEqualitytest(){
		String[] expected = {"true", "test", "0", localTime.toString(), "prjRoot", "1","15" };
		Directory actual = test; 
//		System.out.println("Directory : " + Arrays.toString(dirToStringArray(actual)));
		assertArrayEquals(expected,dirToStringArray(actual)); 
		}

	@Test
	public void testVerifyDirectory() {
		assertTrue(prjRoot.isDirectory());
		assertTrue(src.isDirectory());
		assertTrue(lib.isDirectory());
		assertTrue(test.isDirectory());
		assertTrue(srcTest.isDirectory());
		assertFalse(x.isDirectory());
		assertFalse(a.isDirectory());
		assertFalse(b.isDirectory());
		assertFalse(c.isDirectory());
		assertFalse(d.isDirectory());	
	}
	
	@Test
	public void verifyFiles() {
		assertTrue(a.isFile());
		assertTrue(c.isFile());
		assertTrue(b.isFile());
		assertTrue(d.isFile());
		assertTrue(x.isFile());
	}
	
	@Test
	public void verifyGetChildren() {
		assertEquals(src, prjRoot.getChildren().get(0));
		assertEquals(lib, prjRoot.getChildren().get(1));
		assertEquals(test,prjRoot.getChildren().get(2));
		assertEquals(srcTest,test.getChildren().get(0));
	}
	
	@Test
	public void verifyFilesByDirectory() {
		assertSame("x", prjRoot.getFiles().get(0).getName());
		assertSame("a", src.getFiles().get(0).getName());
		assertSame("b", src.getFiles().get(1).getName());
		assertSame("c", lib.getFiles().get(0).getName());
		assertSame("d", srcTest.getFiles().get(0).getName());
	}
	
	@Test
	public void verifyCount() {
		assertEquals(5, prjRoot.countChildren());
		assertEquals(2, src.countChildren());
		assertEquals(1, lib.countChildren());
		assertEquals(1, test.countChildren());
		assertEquals(1, srcTest.countChildren());
	}

	@Test
	public void verifySize() {
		assertEquals(65, prjRoot.getTotalSize());
		assertEquals(35, src.getTotalSize());
		assertEquals(15, lib.getTotalSize());
		assertEquals(15, test.getTotalSize());
	}

	@Test
	public void testVerifySubDirectories() {
		assertSame("src", prjRoot.getSubDirectories().get(0).getName());
		assertSame("lib",prjRoot.getSubDirectories().get(1).getName());
		assertSame("test",prjRoot.getSubDirectories().get(2).getName());
		assertSame("srcTest",test.getSubDirectories().get(0).getName());
	}
	

}