package jxsource.aspectj.trace;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdapterDelegateXmlTest {
	AdapterDelegate ad;
	
	@BeforeClass
	public static void setup() {
		System.setProperty(AdapterDelegate.ContentFormat, "xml");
	}

	@Before
	public void init() {
		ad = new AdapterDelegate();
	}
	
	@Test
	public void testInteger() {
		String result = ad.toString(new Integer(1));
		assertThat(result, is("<Integer>1</Integer>"));
	}
	@Test
	public void testFloat() {
		String result = ad.toString(new Float(1.2));
		assertThat(result, is("<Float>1.2</Float>"));
	}
	@Test
	public void testString() {
		String result = ad.toString("Test String");
		assertThat(result, is("<String>Test String</String>"));
	}
	@Test
	public void testObject() {
		String result = ad.toString(new TestObject());
		assertThat(result, is("<TestObject><name>name</name><id>100</id></TestObject>"));
		
	}

}
