package jxsource.aspectj.trace;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.stream.XMLInputFactory;
import com.ctc.wstx.stax.*;

@SuppressWarnings("restriction")
public class AdapterDelegate {
	public static final String ContentFormat = "jxsource.aspect-trace.output-format";
	private static Logger log = LogManager.getLogger(AdapterDelegate.class);
	private static Properties properties;
	private boolean xml = true;
	static {
		properties = new Properties();
		try {
			InputStream in = AdapterDelegate.class.getClassLoader().getResourceAsStream("adapter.properties");
			properties.load(in);
		} catch (NullPointerException e) {
			String msg = "No adapter.properties file found in classpath.";
			log.warn(msg);
		} catch (Exception e) {
			String msg = "Error when loading adapter.properties.";
			log.error(msg, e);
		}
	}

	ObjectMapper mapper;
	XmlMapper xmlMapper;
	XMLInputFactory inputFactory;

	public AdapterDelegate() {
		String outputFormat = System.getProperty(ContentFormat);
		if(outputFormat != null) {
			xml = outputFormat.toLowerCase().equals("json") ? false : true;
		}
		mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		// use specified InputFactory to guarantee XmlMapper works in both Spring
		// framework and WAS
		inputFactory = new WstxInputFactory();
		xmlMapper = new XmlMapper(inputFactory);
		xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		xmlMapper.setSerializationInclusion(Include.NON_NULL);
	}

	public String toString(Object obj) {
		if (obj == null) {
			return "null";
		}
		Object repObj = getLogObjectByAdapter(obj);
		if (repObj == null) {
			repObj = obj;
		}
		if (xml) {
			return toXml(repObj);
		} else {
			return toJson(repObj);
		}
	}

	private String toXml(Object obj) {
		String s;
		try {
			s = xmlMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			s = obj.toString();
			log.warn("-> Warning when converting to XML: " + obj +
					", exception: "+e.getClass()+" - "+e.getMessage());
		}
		return s;
	}

	private String toJson(Object obj) {
		String s;
		try {
			s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			s = obj.toString();
			log.warn("-> Warning when converting to JSON: " + obj +
					", exception: "+e.getClass()+" - "+e.getMessage());
		}
		return "<![CDATA[" + s + "]]>";
	}

	private String getAdapterClassName(Object obj) {
		Class<?> cl = obj.getClass();
		String adapterClassName = properties.getProperty(cl.getName());
		if (adapterClassName == null) {
			// super class
			for (Class<?> declaredClass : cl.getDeclaredClasses()) {
				adapterClassName = properties.getProperty(declaredClass.getName());
				if (adapterClassName != null) {
					return adapterClassName;
				}
			}
			// interface
			for (Class<?> implementedInterface : cl.getInterfaces()) {
				adapterClassName = properties.getProperty(implementedInterface.getName());
				if (adapterClassName != null) {
					return adapterClassName;
				}
			}
			return null;
		} else {
			return adapterClassName;
		}
	}

	public Object getLogObjectByAdapter(Object obj) {
		String adapterClassName = getAdapterClassName(obj);
		if (adapterClassName != null) {
			try {
				// Adapter implementation must have no-args constructor
				Class<?> adapterClass = Class.forName(adapterClassName);
				Adapter adapter = (Adapter) adapterClass.newInstance();
				return adapter.convert(obj);
			} catch (Exception e) {
				log.error("Fail to create object: " + adapterClassName, e);
			}
		}
		// let Delegate to use default process
		return null;
	}

}
