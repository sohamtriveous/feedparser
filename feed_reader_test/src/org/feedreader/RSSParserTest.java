package org.feedreader;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

public class RSSParserTest extends TestCase {

	private URL sampleURL;
	private RSSParser parser = new RSSParser();
	
	public RSSParserTest () {
	}
	
	public RSSParserTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.sampleURL = this.getClass().getResource("Story.xml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPreconditions() {
		assertTrue("Did you pass a NULL URL?",  this.sampleURL != null);
	}
	
	public void testListOfTitles() throws Exception{
			List listOfTitles = parser.getListOfTitles(this.sampleURL);
			//System.out.println("lit of titles "+listOfTitles);
			System.out.println("'" + listOfTitles.get(0) + "'");
			assertEquals(10,listOfTitles.size());
			assertEquals("Wash your sins online at Saranam.com", listOfTitles.get(0));
			assertEquals("‘I unlock Joy’ program for developers & students – Windows Phones up for grabs", listOfTitles.get(9));
	}
	
}
