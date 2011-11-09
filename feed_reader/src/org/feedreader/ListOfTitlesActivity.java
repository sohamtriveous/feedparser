package org.feedreader;

import java.net.URL;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListOfTitlesActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RSSParser rssParser = new RSSParser();
		List titles = rssParser.getListOfTitles(new URL(""));		
		setContentView(R.layout.main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,new String[10]);
		this.setListAdapter(adapter);
	}
	
}
