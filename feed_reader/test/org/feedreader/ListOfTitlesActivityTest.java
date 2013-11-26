package org.feedreader;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowTextView;
import org.robolectric.util.ActivityController;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
		 Robolectric.addPendingHttpResponse(200, EXPECTED_XML_RESPONSE);
		  activityController.create();
		  
		  ListActivity activity = (ListActivity)activityController.get();
		  ListView view = activity.getListView();
		  view.layout(0, 0, 100, 1000);
		  view.performItemClick(view.getChildAt(0), 0, 0);
		  
		  ShadowActivity shadowActiviy = Robolectric.shadowOf(activity);
		  Intent startedIntent = shadowActiviy.getNextStartedActivity();
		  ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
		  Assert.assertEquals(shadowIntent.getComponent().getClassName(), ShowFeedDetailsActivity.class.getName());
		  
		  

	}

}
