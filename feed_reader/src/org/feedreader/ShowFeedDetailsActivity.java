package org.feedreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class ShowFeedDetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_view);
		WebView web = (WebView) findViewById(R.id.storyDescription);
		TextView textView = (TextView) findViewById(R.id.storyTitle);
		Intent intent = this.getIntent();
		String content = intent.getStringExtra("content");
		Log.d("Details activity", "content" + content);
		web.loadDataWithBaseURL(intent.getStringExtra("contentURL"), content, "text/html", "utf-8", "");
		textView.setText(intent.getStringExtra("title"));
	}
}
