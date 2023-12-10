package edu.umb.cs681.hw08.fs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
	private static FileSystem fs;
	private LinkedList<FileSystem> fileSystems = new LinkedList<>();
	private static LocalDateTime localTime;
	private static Directory prjRoot;
	private static Directory src;
	private static Directory lib;
	private static Directory test;
	private static Directory srcTest;
	private static File x, a, b, c, d;
	private static Link y;
	private ReentrantLock lock = new ReentrantLock();

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

	private String[] toStringArray(Directory d) {
		String[] dirInfo = {null, d.getName(), Integer.toString(d.getSize()), d.getCreationTime().toString()};
		return dirInfo;
	}

	@Test
	void verifySimilarity() {
		FileSystem fileOne = FileSystem.getFileSystem();
		FileSystem fileTwo = FileSystem.getFileSystem();
		assertSame(fileOne, fileTwo);
	}

	@Test
	void verifyDirectory() {
		String[] expected = {null, "prjRoot", "0", localTime.toString()};
		Directory actual = prjRoot;
		assertArrayEquals(expected, toStringArray(actual));
	}
	@Test
	public void multiple_ThreadsTest() {
		List<Thread> threads = new ArrayList<>();


		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(() -> {
				lock.lock();
				try {
					fileSystems.add(FileSystem.getFileSystem());
				} finally {
					lock.unlock();
				}
			});
			threads.add(thread);
		}
		// Start all threads
		threads.forEach(Thread::start);

		// Wait for all threads to finish
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		for (int i = 0; i <= 98; i++){
			assertSame(fileSystems.get(i), fileSystems.get(i + 1));
		}
	}

}
