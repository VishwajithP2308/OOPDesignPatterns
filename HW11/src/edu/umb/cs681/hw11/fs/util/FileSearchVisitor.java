package edu.umb.cs681.hw11.fs.util;

import java.util.LinkedList;

import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

public class FileSearchVisitor implements FSVisitor{
	
	private String fileName;
	private LinkedList<File> foundFiles = new LinkedList<File>();

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
		if(file.getName().equals(fileName)){
			foundFiles.add(file); 
			}
	}

	public LinkedList<File> getFoundFiles() {
		return foundFiles;
	}

	public void setFoundFiles(LinkedList<File> foundFiles) {
		this.foundFiles = foundFiles;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
