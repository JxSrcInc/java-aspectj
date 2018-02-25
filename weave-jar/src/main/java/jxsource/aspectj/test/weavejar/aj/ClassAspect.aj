package jxsource.aspectj.test.weavejar.aj;

public aspect ClassAspect { 

	pointcut trace() : execution(* jxsource.aspectj.testcode..*(..));
	
	before(): trace() {
	System.out.println("*********** "+thisJoinPointStaticPart.getSignature());
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
