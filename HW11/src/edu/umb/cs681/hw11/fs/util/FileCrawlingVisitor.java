package edu.umb.cs681.hw11.fs.util;
import java.util.LinkedList;
import edu.umb.cs681.hw11.fs.Directory;
import edu.umb.cs681.hw11.fs.FSVisitor;
import edu.umb.cs681.hw11.fs.File;
import edu.umb.cs681.hw11.fs.Link;

public class FileCrawlingVisitor extends ThreadLocal<FileCrawlingVisitor> implements FSVisitor {

	protected LinkedList<File> files = new LinkedList<>();
	private static final ThreadLocal<FileCrawlingVisitor> threadLocal = ThreadLocal.withInitial(() -> new FileCrawlingVisitor());

	@Override
	public void visit(Link link) {
	}

	@Override
	public void visit(Directory dir) {
	}

	@Override
	public void visit(File file) {
		files.add(file);
	}

	public LinkedList<File> getFiles() {
		return files;
	}

	public static FileCrawlingVisitor getInstance() {
		return threadLocal.get();
	}
}
