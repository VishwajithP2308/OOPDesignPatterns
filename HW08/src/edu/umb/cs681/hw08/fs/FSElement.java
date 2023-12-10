package edu.umb.cs681.hw08.fs;

import java.time.LocalDateTime;

public abstract class FSElement {
	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	protected Directory parent;
	
	
	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime ) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}
	
	public void setParent(Directory parent) {
		this.parent = parent;
	}
	
	public Directory getParent() {
		return this.parent;
	}
	
	public LocalDateTime getCreationTime() {
		return this.creationTime;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract boolean isDirectory();

	public abstract boolean isFile();
	
	public abstract boolean isLink();
	
	public abstract void accept(FSVisitor v);
}
