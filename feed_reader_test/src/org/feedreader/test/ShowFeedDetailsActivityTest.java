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
	private String sampleContent = "Sample content";
	
	public ShowFeedDetailsActivityTest() {
		super(ShowFeedDetailsActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent(Intent.ACTION_MAIN);
		intent.putExtra("title", sampleTitle);		
		intent.putExtra("content", sampleContent);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPrecondition(){
		feedDetailsActivity = startActivity(intent, null, null);
		assertNotNull(getActivity());
	}
	
	public void testShowDetails(){
		feedDetailsActivity = startActivity(intent, null, null);
		TextView titleView = (TextView)feedDetailsActivity.findViewById(R.id.storyTitle);
		assertEquals(sampleTitle, titleView.getText());
		WebView webView = (WebView)feedDetailsActivity.findViewById(R.id.storyDescription);
		assertNotNull(webView);
	}
	
}
