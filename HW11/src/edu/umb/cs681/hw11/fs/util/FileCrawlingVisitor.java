package edu.umb.cs681.hw11.fs.util;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

public class FileCrawlingVisitor implements FSVisitor{

	protected LinkedList<File> files = new LinkedList<File>();
	ReentrantLock lock = new ReentrantLock();

	@Override
	public void visit(Link link) {
		return;

	}

	@Override
	public void visit(Directory dir) {
		return;

	}

	@Override
	public void visit(File file) {
		lock.lock();
		try {
		files.add(file);
		}finally {
			lock.unlock();
		}

	}

	public LinkedList<File> getFile(){
		lock.lock();
		try {
		return files;
		}finally {
			lock.unlock();
		}
	}

}
