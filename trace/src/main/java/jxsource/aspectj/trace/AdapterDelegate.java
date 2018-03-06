package jxsource.aspectj.trace;

import jxsource.aspectj.trace.adapter.*;

public class AdapterDelegate implements Adapter{
	private PrimeAdapter primeAdapter = new PrimeAdapter();
	private TypeAdapter typeAdapter = new TypeAdapter();
	public String toString(Object obj) {
		String ret = primeAdapter.toString(obj);
		if(ret != null) {
			return ret;
		} else {
			ret = typeAdapter.toString(obj);
			if(ret != null) {
				return ret;
			} else {
				return null;
			}
		}
	}

}
