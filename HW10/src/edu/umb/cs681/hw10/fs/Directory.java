package edu.umb.cs681.hw10.fs;

import java.time.LocalDateTime;



import java.util.LinkedList;

public class Directory extends FSElement {
	
	protected LinkedList<FSElement> child = new LinkedList<FSElement>();
	protected LinkedList<File> fileList = new LinkedList<File>();
	protected LinkedList<Directory> directoryList = new LinkedList<Directory>();
	protected LinkedList<Link> linkList = new LinkedList<Link>();
	
	public Directory(Directory parent, String name, int size, LocalDateTime createdTime) {
		super(parent, name, size, createdTime);
		if (parent != null)
			parent.appendChild(this);
	}
	
	public LinkedList<FSElement> getChildren() {
		reentrantLock.lock();
		try {
		return child;
		}finally {
			reentrantLock.unlock();
		}
	}

	public void appendChild(FSElement child) {
		reentrantLock.lock();
		try {
			this.child.add(child);
			child.setParent(this);
		}finally {
			reentrantLock.unlock();
		}

	}
	
	public int countChildren() {
		reentrantLock.lock();
		try {
		return getChildren().size();
		}finally {
			reentrantLock.unlock();
		}
	}
		
	public LinkedList<Directory> getSubDirectories() {
		reentrantLock.lock();
		try {
			for (FSElement element : getChildren()) {
				if (element.isDirectory())
					directoryList.add((Directory) element);
			}
			return directoryList;
		}finally {
			reentrantLock.unlock();
		}

	}
	
	public LinkedList<Link> getLinks() {
		reentrantLock.lock();
		try {
			for (FSElement element : getChildren()) {
				if (element.isLink())
					linkList.add((Link) element);
			}
			return linkList;
		}finally {
			reentrantLock.unlock();
		}

	}
	
	public LinkedList<File> getFiles() {
		reentrantLock.lock();
		try {
			for (FSElement element : child) {
				if (element.isFile()) {
					fileList.add((File) element);
				}
			}
			return fileList;
		}finally {
			reentrantLock.unlock();
		}

	}

	public int getTotalSize() {
		int totalSize = 0;
		for (FSElement element : getChildren()) {
			if (element.isDirectory())
				totalSize += ((Directory) element).getTotalSize();
			else
				totalSize += element.getSize();
		}
		return totalSize;
	}
	
	
	public boolean isDirectory() {
		return true;
	}
	
	public  boolean isFile() {
		return false;
	}
	
	public  boolean isLink() {
		return false;
	}

	@Override
	public void accept(FSVisitor v) {
		reentrantLock.lock();
		try {
			v.visit(this);
			for(FSElement e: child){
				e.accept(v); }
		}finally {
			reentrantLock.unlock();
		}
	}	
}