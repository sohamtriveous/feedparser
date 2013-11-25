package org.feedreader;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.app.ListActivity;

@RunWith(RobolectricTestRunner.class)
public class ListOfTitlesActivityTest {

	String EXPECTED_XML_RESPONSE = "<rss><channel>" + 
			"	<title>YourStory.com</title>" + 
			"	<link>http://yourstory.com</link>" + 
			"	<description></description>" + 
			"	<lastBuildDate></lastBuildDate>" + 
			"	<item>" + 
			"		<title>title</title>" + 
			"		<link>link</link>\n" +
			"		<content:encoded>content</content:encoded>"+
			"</item>" + 
			"</channel></rss>"; 

	private ActivityController activityController;

	@Before
	public void setup() {
		activityController = Robolectric
				.buildActivity(ListOfTitlesActivity.class);
	}

	@Test
	public void onCreate_shouldShowTheFeedItemsInListView() {
		Robolectric.addPendingHttpResponse(200, EXPECTED_XML_RESPONSE);
		activityController.create();
		ListActivity activity = (ListActivity) activityController.get();
		assertEquals(1, activity.getListView().getCount());
	}

	@Test
	public void onClickOnListViewItem_shouldShowTheItemDetails() {
		// activityController.create();
		// ListActivity activity = (ListActivity)activityController.get();
		// View view = activity.getListView().getChildAt(0);
		// view.callOnClick();
		// activity.findViewById(id)

	}

}
