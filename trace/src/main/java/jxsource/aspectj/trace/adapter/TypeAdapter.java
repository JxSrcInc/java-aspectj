package jxsource.aspectj.trace.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxsource.aspectj.trace.Adapter;
import jxsource.aspectj.trace.AdapterDelegate;
import jxsource.aspectj.trace.ExceptionHandler;

public class TypeAdapter implements Adapter{
	private static Logger logger = LoggerFactory.getLogger(TypeAdapter.class);
	private static Properties properties;
	static {
		properties = new Properties();
		try{
			InputStream in = AdapterDelegate.class.getResourceAsStream("adapter.properties");
			properties.load(in);
		} catch(Exception e) {
			String msg = "No adapter.properties loaded.";
			if(logger.isDebugEnabled()) {
				logger.debug(msg, e);
			} else {
				logger.warn(msg);				
			}
		}
	}

	private String getAdapterClassName(Object obj) {
		Class<?> cl = obj.getClass();
		String adapterClassName = properties.getProperty(cl.getName());
		if(adapterClassName == null) {
			for(Class declaredClass: cl.getDeclaredClasses()) {
				adapterClassName = properties.getProperty(declaredClass.getName());
				if(adapterClassName != null) {
					return adapterClassName;
				}
			}
			for(Class implementedInterface: cl.getInterfaces()) {
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
	public String[] toString(Object obj) {
		String adapterClassName = getAdapterClassName(obj);
		if(adapterClassName != null) {
			try {
				Class<?> adapterClass = Class.forName(adapterClassName);
				Adapter adapter = (Adapter)adapterClass.newInstance();
				return adapter.toString(obj);
			} catch(Exception e) {
				List<String> error = ExceptionHandler.toString(e);
				error.add(0, "Cannot load adapter "+adapterClassName);
				return error.toArray(new String[error.size()]);
			}
		}
		return null;
	}

}
