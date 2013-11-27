package org.feedreader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.content.Intent;
import android.widget.TextView;


@RunWith(RobolectricTestRunner.class)
public class ShowDetailsActivityTest {
	
	private ActivityController activityController;
	
	@Before
	public void setup() {
		Intent intent = new Intent(Robolectric.application.getApplicationContext(), ShowFeedDetailsActivity.class);
		intent.putExtra("title", "dummy title");
		intent.putExtra("content", "<html> <body> <p> test </p> </body> <html>");
		intent.putExtra("contentURL", "http://www.google.com");
		activityController = Robolectric
				.buildActivity(ShowFeedDetailsActivity.class).withIntent(intent);
	}
	
	@Test
	public void onCreate_shouldShowTheFeedDetailsView() {
		activityController.create();
		ShowFeedDetailsActivity activity = (ShowFeedDetailsActivity) activityController.get();
		TextView textView = (TextView) activity.findViewById(R.id.storyTitle);
		Assert.assertEquals("dummy title", textView.getText());
	}
	

}
