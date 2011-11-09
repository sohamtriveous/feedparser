package org.feedreader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.feedreader.ListOfTitlesActivity;
import org.feedreader.RSSParser;
import org.xml.sax.SAXException;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

public class ListActivityTest extends ActivityUnitTestCase<ListOfTitlesActivity> {

	ListOfTitlesActivity listOfTitlesActivity;
	private Intent intent;
	
	public ListActivityTest() {
		super(ListOfTitlesActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent(Intent.ACTION_MAIN);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPrecondition(){
		
		this.listOfTitlesActivity = startActivity(intent, null, null);
		assertNotNull(this.listOfTitlesActivity);
	}
	
	public void testListActivityContainsAllTitles() throws ParserConfigurationException, SAXException, IOException {
		this.listOfTitlesActivity = startActivity(intent, null, null);
		RSSParser rssParser = new RSSParser();
		List titles = rssParser.getListOfTitles(this.getClass().getResource("Story.xml"));
		assertEquals(listOfTitlesActivity.getListAdapter().getItem(0),titles.get(0));
		assertEquals(listOfTitlesActivity.getListAdapter().getCount(), titles.size());
		assertEquals(listOfTitlesActivity.getListAdapter().getItem(9),titles.get(9));
	}

}
