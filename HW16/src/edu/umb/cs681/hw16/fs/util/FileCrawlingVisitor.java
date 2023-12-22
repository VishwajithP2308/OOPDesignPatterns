package edu.umb.cs681.hw16.fs.util;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;


import edu.umb.cs681.hw16.fs.Directory;
import edu.umb.cs681.hw16.fs.FSVisitor;
import edu.umb.cs681.hw16.fs.File;
import edu.umb.cs681.hw16.fs.Link;

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
		files.add(file);
		}
		

	public LinkedList<File> getFile(){
		return files;
		}
	}


