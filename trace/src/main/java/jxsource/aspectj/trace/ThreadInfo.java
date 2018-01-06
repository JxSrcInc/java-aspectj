package jxsource.aspectj.trace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Stack;

public class ThreadInfo {

//	private PrintStream out;
	private File file;
	private Stack<String> stack = new Stack<String>();
	private RandomAccessFile out;
	private long pos;
	private int intent;
	
	private String getIntent() {
		char[] b = new char[intent];
		Arrays.fill(b, ' ');
		return new String(b);
	}
	public void increase() {
		intent++;
	}
	public void decrease() {
		intent--;
	}
	public ThreadInfo(File file) throws FileNotFoundException
	{
		this.file = file;
		try {
			OutputStream cOut = new FileOutputStream(file);
			cOut.write(new byte[0]);
			cOut.flush();
			cOut.close();
			out = new RandomAccessFile(file, "rw");
			out.writeBytes("<"+Constants.root+">\n");
			pos = out.getFilePointer();
			out.writeBytes("</"+Constants.root+">\n");
			out.seek(pos);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void print(String trans) {
		try {
//			pos += trans.length();
			out.writeBytes(trans+'\n');
			pos = out.getFilePointer();
			out.writeBytes("</"+Constants.root+">\n");
			out.seek(pos);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void print(ThreadStack ts) {
		print(ts.toXML());
	}
	
	public void close() {
		try {
			out.close();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public File getFile()
	{
		return file;
	}
	
	public int getStackSize()
	{
		return stack.size();
	}
	public synchronized boolean isStackEmpty()
	{
		return stack.isEmpty();
	}
	
	public synchronized String pop()
	{
		return stack.pop();
	}
	
	public synchronized void push(String s)
	{
		stack.push(s);
	}
	
	public synchronized String peek()
	{
		return stack.peek();
	}
	
	public String toString()
	{
		return "File="+file.getPath()+", Stack="+stack;
	}
}
