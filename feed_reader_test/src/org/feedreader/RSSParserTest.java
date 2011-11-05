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
	
	public void testNumberOfItemsInRSSFeed() {
		Document xmlDoc;
		try {
			List listOfItemsInFeed = parser.parseRSSFeed(this.sampleURL);
			assertTrue(listOfItemsInFeed.size() == 10);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
	
}
