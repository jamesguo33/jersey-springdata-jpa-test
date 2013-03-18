package sample.op.eastblue.shellstown.rest;


import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleTest extends BaseTestClient{
	
	@Test
	public void testInsertPeople() {
		String result = resource().get(String.class);
		assertEquals("Jersey rolling", result);
	}
	
	protected String getResourcePath() {
		return "/test/get";
	}
}
