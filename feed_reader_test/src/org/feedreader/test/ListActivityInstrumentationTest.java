package org.feedreader.test;

import org.feedreader.FeedItem;
import org.feedreader.ListOfTitlesActivity;
import org.feedreader.R;
import org.feedreader.ShowFeedDetailsActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class ListActivityInstrumentationTest extends
		ActivityInstrumentationTestCase2<ListOfTitlesActivity> {

	private Solo solo;

	public ListActivityInstrumentationTest() {
		super("org.feedreader.test", ListOfTitlesActivity.class);

	}

	@Override
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	@Smoke
	public void testTitleListing() throws Exception {
		assertEquals(true,solo.searchText(getActivity().getString(R.string.app_name)));
		solo.assertCurrentActivity("",ListOfTitlesActivity.class);
		assertEquals(Constants.NUMBER_OF_ITEMS,getActivity().getListAdapter().getCount());
		solo.sleep(100);
	}
	
	public void testItemClick() throws Exception{
		solo.sleep(100);		
		solo.clickInList(0);
		String firstTitle = ((FeedItem)getActivity().getListAdapter().getItem(0)).getTitle();
		solo.sleep(100);
		assertEquals(firstTitle, ((TextView)solo.getView(R.id.storyTitle)).getText());
		solo.assertCurrentActivity("",ShowFeedDetailsActivity.class);
		solo.goBack();
		solo.sleep(100);
		solo.scrollDown();
		solo.sleep(100);
		
	}
}
