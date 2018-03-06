package jxsource.aspectj.trace.adapter;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jxsource.aspectj.trace.Adapter;
import jxsource.aspectj.trace.AdapterDelegate;
import jxsource.aspectj.trace.ExceptionHandler;

public class TypeAdapter implements Adapter{
	private static Logger logger = LogManager.getLogger(TypeAdapter.class);
	private static Properties properties;
	static {
		properties = new Properties();
		try{
			InputStream in = AdapterDelegate.class.getResourceAsStream("adapter.properties");
			properties.load(in);
		} catch(NullPointerException e) {
			String msg = "No adapter.properties loaded.";
			logger.warn(msg);				
		} catch(Exception e) {
			String msg = "No adapter.properties loaded.";
			logger.error(msg, e);				
		}
	}

	private String getAdapterClassName(Object obj) {
		Class<?> cl = obj.getClass();
		String adapterClassName = properties.getProperty(cl.getName());
		if(adapterClassName == null) {
			for(Class<?> declaredClass: cl.getDeclaredClasses()) {
				adapterClassName = properties.getProperty(declaredClass.getName());
				if(adapterClassName != null) {
					return adapterClassName;
				}
			}
			for(Class<?> implementedInterface: cl.getInterfaces()) {
				adapterClassName = properties.getProperty(implementedInterface.getName());
				if(adapterClassName != null) {
					return adapterClassName;
				}				
			}
			return null;
		} else {
			return adapterClassName;
		}
	}
	public String toString(Object obj) {
//		String adapterClassName = getAdapterClassName(obj);
//		if(adapterClassName != null) {
//			try {
//				Class<?> adapterClass = Class.forName(adapterClassName);
//				Adapter adapter = (Adapter)adapterClass.newInstance();
//				return adapter.toString(obj);
//			} catch(Exception e) {
//				List<String> error = ExceptionHandler.toList(e);
//				error.add(0, "Cannot load adapter "+adapterClassName);
//				return error.toArray(new String[error.size()]);
//			}
//		}
		return null;
	}

}
