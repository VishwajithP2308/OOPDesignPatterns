package edu.umb.cs681.hw08.fs;

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
		return child;
	}

	public void appendChild(FSElement child) {
		this.child.add(child);
		child.setParent(this);
	}
	
	public int countChildren() {
		return getChildren().size();
	}
		
	public LinkedList<Directory> getSubDirectories() {
		for (FSElement element : getChildren()) {
			if (element.isDirectory())
				directoryList.add((Directory) element);
		}
		return directoryList;
	}
	
	public LinkedList<Link> getLinks() {
		for (FSElement element : getChildren()) {
			if (element.isLink())
				linkList.add((Link) element);
		}
		return linkList;
	}
	
	public LinkedList<File> getFiles() {
		for (FSElement element : child) {
			if (element.isFile()) {
				fileList.add((File) element);
			}
		}
		return fileList;
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
		 v.visit(this);
         for(FSElement e: child){
           e.accept(v); }
		
	}	
}