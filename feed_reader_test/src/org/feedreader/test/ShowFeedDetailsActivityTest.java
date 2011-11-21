package org.feedreader.test;

import org.feedreader.R;
import org.feedreader.ShowFeedDetailsActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.webkit.WebView;
import android.widget.TextView;

public class ShowFeedDetailsActivityTest extends ActivityUnitTestCase<ShowFeedDetailsActivity> {

	private ShowFeedDetailsActivity feedDetailsActivity;
	private Intent intent;
	private String sampleTitle = "Sample title"; 
	private String sampleContentURL = "http://yourstory.in/2011/11/wash-your-sins-online-at-saranam-com/";
	
	public ShowFeedDetailsActivityTest() {
		super(ShowFeedDetailsActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent(Intent.ACTION_MAIN);
		intent.putExtra("title", sampleTitle);		
		intent.putExtra("contentURL", sampleContentURL);
//		intent.putExtra("content", sampleTitle);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testShowDetails(){
		feedDetailsActivity = startActivity(intent, null, null);
		assertNotNull(getActivity());
		TextView titleView = (TextView)feedDetailsActivity.findViewById(R.id.storyTitle);
		assertEquals(sampleTitle, titleView.getText());
		WebView webView = (WebView)feedDetailsActivity.findViewById(R.id.storyDescription);
		assertNotNull(webView);
	}

}
