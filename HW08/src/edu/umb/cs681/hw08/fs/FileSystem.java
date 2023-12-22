package edu.umb.cs681.hw08.fs;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	private static final ReentrantLock staticLock = new ReentrantLock();
	
	private static FileSystem instance = null;
	protected LinkedList<Directory> rootDirectory = new LinkedList<Directory>();
	
	private FileSystem(){
		
	}

	public static FileSystem getFileSystem() {
		staticLock.lock();
		try {
			if (instance == null)
				instance = new FileSystem();
			return instance;
		}finally {
			staticLock.unlock();
		}

	}

	public LinkedList<Directory> getRootDirs() {
		return this.rootDirectory;
	}

	public void appendRootDir(Directory directory) {
		rootDirectory.add(directory);
	}
}