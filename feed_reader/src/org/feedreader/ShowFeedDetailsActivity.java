package org.feedreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ShowFeedDetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_view);
		Intent intent = this.getIntent();
		final String title = intent.getStringExtra("title");
		final String url = intent.getStringExtra("contentURL");
		// new ShortenURL(LOGIN, APIKEY)
		WebView web = (WebView) findViewById(R.id.storyDescription);
		TextView textView = (TextView) findViewById(R.id.storyTitle);
		String content = intent.getStringExtra("content");
		Log.d("Details activity", "content" + content);
		web.loadDataWithBaseURL(url, content, "text/html", "utf-8", "");
		textView.setText(title);
		Button shareButton = (Button) findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType(getString(R.string.share_message_type));
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
				String shortenedURL = url;
				try {
					shortenedURL = new ShortenURL(
							getString(R.string.bitly_login),
							getString(R.string.bitly_api_key)).getShortUrl(url);
				} catch (Exception ex) {
					Log.w("", "Exception while shortening the URL " + ex);
					ex.printStackTrace();
				}
				intent.putExtra(android.content.Intent.EXTRA_TEXT,
						getString(R.string.share_message) + " " + shortenedURL);
				startActivity(Intent.createChooser(intent,
						getString(R.string.share_button_label)));
			}
		});
	}
}
