package jxsource.aspectj.trace;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;

public class ThreadTrace {

	private static Logger logger = LogManager.getLogger(ThreadTrace.class);
	private AdapterDelegate adapterDelegate = new AdapterDelegate();
	private static DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	private static File dir = new File(
			Constants.traceDir + "_" + timeFormat.format(new Date(System.currentTimeMillis())));
	private boolean outputWithoutIntent;
	private boolean showTime;
	private boolean showParamWhenReturn;
	private boolean showStack;

	public ThreadTrace() {
		outputWithoutIntent = System.getProperty("jxsource.aspectj.trace.without_intent") != null;
	}

	public boolean isShowStack() {
		return showStack;
	}

	public ThreadTrace setShowStack(boolean showStack) {
		this.showStack = showStack;
		return this;
	}

	public boolean isOutputWithoutIntent() {
		return outputWithoutIntent;
	}

	public ThreadTrace setOutputWithoutIntent(boolean outputWithoutIntent) {
		this.outputWithoutIntent = outputWithoutIntent;
		return this;
	}

	public boolean isShowTime() {
		return showTime;
	}

	public ThreadTrace setShowTime(boolean showTime) {
		this.showTime = showTime;
		return this;
	}

	public boolean isShowParamWhenReturn() {
		return showParamWhenReturn;
	}

	public ThreadTrace setShowParamWhenReturn(boolean showParamWhenReturn) {
		this.showParamWhenReturn = showParamWhenReturn;
		return this;
	}

	private ThreadInfo getThreadInfo(String preFileName) {
		ThreadInfo info = AspectjThreadLocal.get();
		if (info == null) {
			try {
				String fName = preFileName + '_' + Thread.currentThread().getId() + ".xml";
				if (!dir.exists())
					dir.mkdirs();
				info = new ThreadInfo(new File(dir, fName));
				AspectjThreadLocal.set(info);
				logger.info("log thread -> " + info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return info;
	}

	public Object traceAround(ProceedingJoinPoint pjp) throws Throwable {
		Signature s = pjp.getSignature();
		String className = s.getDeclaringTypeName();
		String methodName = s.getName();
		ThreadInfo info = getThreadInfo(className + "." + methodName);
		try {
			if (showTime) {
				info.print("<method time=\"" + timeFormat.format(new Date(System.currentTimeMillis())) + "\" class=\""
						+ className + "\" name=\"" + methodName + "\">");
			} else {
				info.print("<method class=\"" + className + "\" name=\"" + methodName + "\">");
			}
			info.increase();
			printParam(pjp);
			if (showStack) {
				ThreadStack stack = new ThreadStack();
				info.print("<stack>");
				info.print(stack);
				info.print("</stack>");
			}
			Object retVal = pjp.proceed();
			String sig = s.toString();
			// get return type and default it to null
			int pos = sig.indexOf(' ');
			String retName = sig.substring(0, pos);

			if (!retName.toLowerCase().equals("void")) {
				if (retVal != null) {
					print(info, "ret", retVal.getClass(), retVal);
				} else {
					info.print("<ret name=\"unknownClass\" value=\"null\"/>");
				}
			}
			if (showParamWhenReturn) {
				printParam(pjp);
			}
			info.decrease();
			info.print("</method>");
			return retVal;
		} catch (Throwable e) {
			info.print("<exception>");
			info.increase();
			info.print(ExceptionHandler.toString(e));
			info.decrease();
			info.print("</exception>");
			info.decrease();
			info.print("</method>");
			throw e;
		}

	}

	public void traceMethodEntry(JoinPoint jp, boolean showStack) {
		Signature s = jp.getSignature();
		String className = s.getDeclaringTypeName();
		String methodName = s.getName();
		ThreadInfo info = getThreadInfo(className + "." + methodName);
		ThreadStack stack = new ThreadStack();
		if (showTime) {
			info.print("<method time=\"" + timeFormat.format(new Date(System.currentTimeMillis())) + "\" class=\""
					+ className + "\" name=\"" + methodName + "\">");
		} else {
			info.print("<method class=\"" + className + "\" name=\"" + methodName + "\">");
		}
		info.increase();
		printParam(jp);
		if (showStack) {
			info.print("<stack>");
			info.print(stack);
			info.print("</stack>");
		}
	}

	public void traceMethodEntry(JoinPoint jp) {
		traceMethodEntry(jp, false);
	}

	private void printParam(JoinPoint jp) {
		ThreadInfo info = AspectjThreadLocal.get();
		Class<?>[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		Object[] args = jp.getArgs();
		for (int i = 0; i < types.length; i++) {
			print(info, "arg", types[i], args[i]);
		}
	}

	public void traceThrowable(JoinPoint jp, Throwable e) {
		ThreadInfo info = AspectjThreadLocal.get();
		printParam(jp);
		info.print("<exception>");
		info.increase();
		info.print(ExceptionHandler.toString(e));
		info.decrease();
		info.print("</exception>");
		info.decrease();
		info.print("</method>");
	}

	public void traceMethodExit(JoinPoint jp, Object retVal) {
		ThreadInfo info = AspectjThreadLocal.get();// cleanStack(jp);
		String sig = jp.getSignature().toString();
		// get return type and default it to null
		int pos = sig.indexOf(' ');
		String retName = sig.substring(0, pos);

		if (!retName.toLowerCase().equals("void")) {
			if (retVal != null) {
				print(info, "ret", retVal.getClass(), retVal);
			} else {
				info.print("<ret name=\"unknownClass\" value=\"null\"/>");
			}
		}
		if (showParamWhenReturn) {
			printParam(jp);
		}
		info.decrease();
		info.print("</method>");
	}

	private void print(ThreadInfo info, String tag, Class<?> type, Object obj) {
		String value = adapterDelegate.toString(obj);
		if (outputWithoutIntent) {
			info.print("<" + tag + " name=\"" + type.getName() + "\">" + value + "</" + tag + ">");
		} else {
			info.print("<" + tag + " name=\"" + type.getName() + "\">");
			info.increase();
			info.print(value);
			info.decrease();
			info.print("</" + tag + ">");
		}
	}

}
