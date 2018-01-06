package jxsource.aspectj.trace;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
	public static List<String> toString(Throwable e) {
		List<String> list = new ArrayList<String>();
		list.add(e.getClass()+": "+e.getMessage());
		for(StackTraceElement ele: e.getStackTrace()) {
			list.add(ele.toString());
		}
		Throwable throwable = e.getCause();
		if(throwable != null) {
			list.addAll(toString(throwable));
		}
		return list;
	}
}
