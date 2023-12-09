package edu.umb.cs681.hw11.fs.util;

import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

public class CountingVisitor implements FSVisitor {
	protected int dirNum = 0; 
	protected int fileNum = 0;
	protected int linkNum = 0;

	@Override
	public void visit(Link link) {
		this.linkNum += 1;
		
	}

	@Override
	public void visit(Directory dir) {
		this.dirNum += 1;
		
	}

	@Override
	public void visit(File file) {
		this.fileNum += 1;
		
	}
	
	public int getDirNum() {
		return dirNum;
	}
	
	public int getFileNum() {
		return fileNum;
	}
	
	public int getLinkNum() {
		return linkNum;
	}

}
