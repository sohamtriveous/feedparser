package org.feedreader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class RSSParserTest extends TestCase {

	private URL sampleURL;
	private RSSParser parser;
	
	public RSSParserTest () {
	}
	
	public RSSParserTest(String name) {
		super(name);
		parser = new RSSParser();
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
	
	public void testListOfTitles() {
		try {
			List listOfTitles = parser.getListOfTitles(this.sampleURL);
			//System.out.println("lit of titles "+listOfTitles);
			System.out.println("'" + listOfTitles.get(0) + "'");
			assertEquals(listOfTitles.size(),10);
			assertEquals(listOfTitles.get(0),"Wash your sins online at Saranam.com");
			assertEquals(listOfTitles.get(9),"‘I unlock Joy’ program for developers &amp; students – Windows Phones up for grabs");
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
