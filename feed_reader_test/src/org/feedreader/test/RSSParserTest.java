package org.feedreader.test;

import java.util.List;

import org.feedreader.FeedItem;
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
		sampleURL = this.getClass().getResource(sampleURL).toString();
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
	
	public void testListOfItemsFromFeed()throws Exception{
		parser = new RSSParser(this.sampleURL);
		List<FeedItem> items = parser.getListOfItemsFromFeed();
		FeedItem firstItem = items.get(0);
		FeedItem lastItem = items.get(9);
		System.out.println("First Item "+firstItem);
		System.out.println("Last Item "+lastItem);
		assertEquals(Constants.NUMBER_OF_ITEMS, items.size());
		assertEquals(Constants.TITLE_OF_FIRST_ARTICLE, firstItem.getTitle());
		assertEquals(Constants.TITLE_OF_LAST_ARTICLE, lastItem.getTitle());
		assertNotNull(firstItem.getContent());
		assertNotNull(lastItem.getContent());		
		assertNotNull(firstItem.getContentURL());
		assertNotNull(lastItem.getContentURL());		
	}

}
