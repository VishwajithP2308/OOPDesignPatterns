package edu.umb.cs681.hw08.fs.util;

import java.util.LinkedList;

import edu.umb.cs681.hw08.fs.Directory;
import edu.umb.cs681.hw08.fs.FSVisitor;
import edu.umb.cs681.hw08.fs.File;
import edu.umb.cs681.hw08.fs.Link;

public class FileCrawlingVisitor implements FSVisitor{
	
	protected LinkedList<File> files = new LinkedList<File>(); 

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
