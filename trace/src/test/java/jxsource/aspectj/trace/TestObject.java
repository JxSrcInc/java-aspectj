package jxsource.aspectj.trace;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestObject {
	private String msg;
	private int id;
	private String[] arr;
	private Map<String, Object> map;
	
	public TestObject() {
		msg = "Message";
		id = 0;
		arr = new String[] {"abc","xyz"};
		map = new HashMap<String, Object>();
		map.put("file", new File("test-file"));
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getArr() {
		return arr;
	}

	public void setArr(String[] arr) {
		this.arr = arr;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
