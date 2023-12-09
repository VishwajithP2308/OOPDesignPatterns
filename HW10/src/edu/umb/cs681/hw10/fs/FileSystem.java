package edu.umb.cs681.hw10.fs;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {

	// Change: Ensure proper initialization of the AtomicReference
	private static final AtomicReference<FileSystem> instance = new AtomicReference<>();
	private static final ReentrantLock staticLock = new ReentrantLock();

	private ReentrantLock reentrantLock = new ReentrantLock();
	protected LinkedList<Directory> rootDirectory = new LinkedList<>();

	private FileSystem() {
	}


	public static FileSystem getFileSystem() {
		staticLock.lock();
		try {
			if (instance.get() == null) {
				instance.set(new FileSystem());
			}
			return instance.get();
		} finally {
			staticLock.unlock();
		}
	}

	public LinkedList<Directory> getRootDirs() {
		reentrantLock.lock();
		try {
			return new LinkedList<>(this.rootDirectory);
		} finally {
			reentrantLock.unlock();
		}
	}

	public void appendRootDir(Directory directory) {
		reentrantLock.lock();
		try {
			rootDirectory.add(directory);
		} finally {
			reentrantLock.unlock();
		}
	}
}
