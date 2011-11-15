package org.feedreader.test;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.feedreader.ListOfTitlesActivity;
import org.xml.sax.SAXException;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

public class ListOfTitlesActivityTest extends ActivityUnitTestCase<ListOfTitlesActivity> {

	ListOfTitlesActivity listOfTitlesActivity;
	private Intent intent;
	
	public ListOfTitlesActivityTest() {
		super(ListOfTitlesActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent(Intent.ACTION_MAIN);
//		setActivityContext(getInstrumentation().getContext());
//		getActivity().getApplicationContext().getAssets().
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
		assertEquals(Constants.NUMBER_OF_TITLES,listOfTitlesActivity.getListAdapter().getCount());
//		assertEquals(Constants.TITLE_OF_FIRST_ARTICLE,listOfTitlesActivity.getListAdapter().getItem(0));
//		assertEquals(Constants.TITLE_OF_LAST_ARTICLE,listOfTitlesActivity.getListAdapter().getItem(9));
	}

}
