package org.feedreader;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListOfTitlesActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<String> titles = new ArrayList<String>();
		try {
			RSSParser rssParser = new RSSParser(getString(R.string.feed_url));
			titles = rssParser.getListOfTitles();
		} catch (Exception exception) {
			exception.printStackTrace();
		} 	
		setContentView(R.layout.main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,(String[])(titles.toArray(new String[0])));
		this.setListAdapter(adapter);
	}
	
}
