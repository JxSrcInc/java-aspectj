package jxsource.aspectj.trace.adapter;

import java.util.Collection;

import jxsource.aspectj.trace.Adapter;

public class PrimeAdapter implements Adapter{

//	public String[] toString(Object obj) {
//		String[] ret = new String[1];
//		if(obj == null) {
//			ret[0] = "null";
//		} else
//		if(obj instanceof Number) {
//			ret[0] = obj.toString();
//		} else 
//		if(obj instanceof String) {
//			ret[0] = obj.toString();
//		} else 
//		if(obj instanceof Character) {
//			ret[0] = obj.toString();
//		} else 
//		if(obj instanceof Boolean) {
//			ret[0] = obj.toString();
//		} else 
//		if(obj instanceof Collection) {
//			ret[0] = obj.toString();
//		} else 
//		if(obj.getClass().getName().charAt(0) == '[') {
//			// array
//			ret[0] = obj.toString();
//		} else {
//			ret = null;
//		}
//		return ret;
//	}
	public String toString(Object obj) {
		if(obj == null) {
			return "null";
		} else
		if(obj instanceof Number) {
			return obj.toString();
		} else 
		if(obj instanceof String) {
			return obj.toString();
		} else 
		if(obj instanceof Character) {
			return obj.toString();
		} else 
		if(obj instanceof Boolean) {
			return obj.toString();
		} else 
		if(obj instanceof Collection) {
			return obj.toString();
		} else 
		if(obj.getClass().getName().charAt(0) == '[') {
			// array
			return obj.toString();
		} else {
			return null;
		}
	}

}
