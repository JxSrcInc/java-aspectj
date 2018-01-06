package jxsource.aspectj.trace.test.aj;

import jxsource.aspectj.trace.*;

public aspect ClassAspect { 

	private ThreadTrace threadTrace = ThreadTraceLocal.get();
	pointcut trace() : execution(void jxsource.aspectj.trace.ThreadTrace.traceMethodEntry(..));
	
	before(): trace() {
	System.out.println("*********** "+thisJoinPointStaticPart.getSignature());
	      threadTrace.traceMethodEntry(thisJoinPointStaticPart.getSignature(),false);
	}
	/*
	after() throwing(): trace() {
		threadTrace.traceThrowable(thisJoinPoint);
	}
	after() returning(Object retVal): trace() {
		threadTrace.traceMethodExit(thisJoinPoint, retVal);
	}
	*/
	// This advice requires exclued struts pointcut
//	after() : execution(* gov.ssa.eforms.actions.TestLogoutAction.executeRequest(..)) {
//		StreamManager.getInstance().remove(Thread.currentThread());
//	}
//	after() : execution(void gov.ssa.eforms.common.EFormsInitPlugin.destroy()) {
//		StreamManager.getInstance().clear();
//	}
} 
