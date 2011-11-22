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
		WebView web = (WebView) findViewById(R.id.storyDescription);
		TextView textView = (TextView) findViewById(R.id.storyTitle);		
		String content = intent.getStringExtra("content");
		Log.d("Details activity", "content" + content);
		web.loadDataWithBaseURL(url, content, "text/html", "utf-8", "");
		textView.setText(title);
		Button shareButton = (Button)findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType(getString(R.string.share_message_type));
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title );
				intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.share_message)+" "+url);	
				startActivity(Intent.createChooser(intent, getString(R.string.share_button_label)));
			}
		});
	}
}
