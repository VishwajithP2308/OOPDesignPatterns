package edu.umb.cs681.hw16.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	protected Directory parent;
	protected ReentrantLock reentrantLock = new ReentrantLock();
	
	
	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime ) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}
	
	public void setParent(Directory parent) {
		reentrantLock.lock();
		try {
		this.parent = parent;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public Directory getParent() {
		reentrantLock.lock();
		try {
		return this.parent;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public LocalDateTime getCreationTime() {
		reentrantLock.lock();
		try {
		return this.creationTime;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public int getSize() {
		reentrantLock.lock();
		try {
		return this.size;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public String getName() {
		reentrantLock.lock();
		try {
		return this.name;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public abstract boolean isDirectory();

	public abstract boolean isFile();
	
	public abstract boolean isLink();
	
	public abstract void accept(FSVisitor v);
}
