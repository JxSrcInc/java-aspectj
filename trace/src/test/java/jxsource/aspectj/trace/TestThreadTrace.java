package jxsource.aspectj.trace;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class TestThreadTrace extends ThreadTrace{

	@Override
	public void traceMethodEntry(Signature s, boolean showStack) {
		// TODO Auto-generated method stub
		super.traceMethodEntry(s, showStack);
	}

	@Override
	public void traceMethodEntry(Signature s) {
		// TODO Auto-generated method stub
		super.traceMethodEntry(s);
	}

	@Override
	public void traceThrowable(JoinPoint jp) {
		// TODO Auto-generated method stub
		super.traceThrowable(jp);
	}

	@Override
	public void traceMethodExit(JoinPoint jp, Object retVal) {
		// TODO Auto-generated method stub
		super.traceMethodExit(jp, retVal);
	}

}
