package org.feedreader.test;

import org.feedreader.FeedItem;
import org.feedreader.ListOfTitlesActivity;
import org.feedreader.R;
import org.feedreader.ShortenURL;
import org.feedreader.ShowFeedDetailsActivity;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class ListActivityTest extends
		ActivityInstrumentationTestCase2<ListOfTitlesActivity> {

	private Solo solo;

	public ListActivityTest() {
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
		assertEquals(true, solo.searchText(getActivity().getString(
				R.string.app_name)));
		solo.assertCurrentActivity("", ListOfTitlesActivity.class);
		assertEquals(Constants.NUMBER_OF_ITEMS, getActivity().getListAdapter()
				.getCount());
		solo.sleep(100);
	}

	public void testItemClick() throws Exception {
		solo.clickInList(0);
		String firstTitle = ((FeedItem) getActivity().getListAdapter().getItem(
				0)).getTitle();
		assertEquals(firstTitle, ((TextView) solo.getView(R.id.storyTitle))
				.getText());
		solo.assertCurrentActivity("", ShowFeedDetailsActivity.class);
		solo.goBack();
		solo.scrollDown();
	}

	public void testShareButtonClick() throws Exception{
		FeedItem feedItem = (FeedItem) getActivity().getListAdapter()
				.getItem(0);
		Activity currentActivity = getActivity();
		solo.clickInList(0);
		solo.clickOnButton(currentActivity
				.getString(R.string.share_button_label));
		String url = new ShortenURL(currentActivity
				.getString(R.string.bitly_login), currentActivity
				.getString(R.string.bitly_api_key)).getShortUrl(feedItem
				.getContentURL());
		Intent shareIntent = (Intent) solo.getCurrentActivity().getIntent()
				.getExtras().get(Intent.EXTRA_INTENT);
		
		assertEquals(currentActivity.getString(R.string.share_message_type),
				shareIntent.getType());
		assertEquals(Intent.ACTION_SEND, shareIntent.getAction());
		assertEquals(feedItem.getTitle(), shareIntent
				.getStringExtra(Intent.EXTRA_SUBJECT));
		assertTrue(shareIntent.getStringExtra(Intent.EXTRA_TEXT).contains(
				currentActivity.getString(R.string.share_message)));
		assertTrue(shareIntent.getStringExtra(Intent.EXTRA_TEXT).contains(url));
	}
}
