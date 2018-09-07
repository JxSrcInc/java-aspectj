package jxsource.aspectj.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdapterDelegateJsonTest {
	AdapterDelegate ad;

	@BeforeClass
	public static void setup() {
		System.setProperty(AdapterDelegate.ContentFormat, "json");
	}

	@Before
	public void init() {
		ad = new AdapterDelegate();
	}

	@Test
	public void testInteger() {
		String result = ad.toString(new Integer(1));
		assertThat(result, is("<![CDATA[1]]>"));
	}

	@Test
	public void testFloat() {
		String result = ad.toString(new Float(1.2));
		assertThat(result, is("<![CDATA[1.2]]>"));
	}

	@Test
	public void testString() {
		String result = ad.toString("Test String");
		assertThat(result, is("<![CDATA[\"Test String\"]]>"));
	}
	@Test
	public void testObject() {
		String result = ad.toString(new TestObject());
		assertThat(result, is("<![CDATA[{\r\n" + 
				"  \"name\" : \"name\",\r\n" + 
				"  \"id\" : 100\r\n" + 
				"}]]>"));
		
	}

}
