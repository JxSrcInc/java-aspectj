package jxsource.aspectj.trace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
	public static List<String> toList(Throwable e) {
		List<String> list = new ArrayList<String>();
		list.add(e.getClass()+": "+e.getMessage());
		for(StackTraceElement ele: e.getStackTrace()) {
			list.add(ele.toString());
		}
		Throwable throwable = e.getCause();
		if(throwable != null) {
			list.addAll(toList(throwable));
		}
		return list;
	}
	public static String toString(Throwable e) {
		String s = "<![CDATA[\n";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		PrintStream w = new PrintStream(out);
//		e.printStackTrace(System.err);
		e.printStackTrace(w);
		out.flush();
		out.close();
		} catch(IOException ioe) {ioe.printStackTrace();}
		s += out.toString();
		return s + "]]>";
	}
}
