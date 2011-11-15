package org.feedreader.test;

import java.util.List;

import org.feedreader.RSSParser;

import junit.framework.TestCase;

public class RSSParserTest extends TestCase {

	private RSSParser parser;
	private String sampleURL = "Story.xml";

	public RSSParserTest() {
	}

	public RSSParserTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testThrowExceptionForInvalidURL(){
		try{
			parser = new RSSParser("");
			fail("Should throw error if the string is invalid");
		}
		catch(Exception ex){
			assertTrue(true);
		}
	}

	public void testShouldNotThrowExceptionForValidHTTPURL(){
		try{
			parser = new RSSParser("http://feeds.feedburner.com/yourstory/news");
			assertTrue(true);
		}
		catch(Exception ex){
			fail("Should not throw error if the string is invalid");
		}
	}
	
	public void testShouldNotThrowExceptionIfFileIsAvailableAsClasspathResource(){
		try{
			parser = new RSSParser(this.sampleURL);
		}
		catch(Exception exception){
			fail("Should not throw error if the resource is found");
		}
	}
	
	public void testListOfTitles() throws Exception {
		parser = new RSSParser(this.sampleURL);
		List<String> listOfTitles = parser.getListOfTitles();
		// System.out.println("lit of titles "+listOfTitles);
		System.out.println("'" + listOfTitles.get(0) + "'");
		assertEquals(Constants.NUMBER_OF_TITLES, listOfTitles.size());
		assertEquals(Constants.TITLE_OF_FIRST_ARTICLE, listOfTitles.get(0));
		assertEquals(Constants.TITLE_OF_LAST_ARTICLE, listOfTitles.get(9));
	}

}
