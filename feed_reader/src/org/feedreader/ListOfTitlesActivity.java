package org.feedreader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListOfTitlesActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<String> titles = new ArrayList<String>();
		List<FeedItem> feedItems = new ArrayList<FeedItem>();
		try {
			RSSParser rssParser = new RSSParser(getString(R.string.feed_url));
			feedItems = rssParser.getListOfItemsFromFeed();
			for (FeedItem feedItem : feedItems) {
				titles.add(feedItem.getTitle());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		setContentView(R.layout.main);

		// WebView web = (WebView) findViewById(R.id.storyDescription);
		// String string =
		// "<div>      <a href='http://yourstory.in/2011/11/spruce-up-your-e-image-with-xtreme-digital-solutions/' title='Spruce up your e-image with Xtreme Digital Solutions'>                <img alt='Spruce up your e-image with Xtreme Digital Solutions' height='120' src='http://yourstory.in/wp-content/themes/NewStory2/scripts/timthumb.php?src=http://yourstory.in/wp-content/uploads/2011/11/xtreme_digital.jpg&amp;h=120&amp;w=150&amp;zc=1' width='150' />            </a>        </div><div id='spacetitle'>            <h2 class='title'>                <a href='http://yourstory.in/2011/11/spruce-up-your-e-image-with-xtreme-digital-solutions/' title='Spruce up your e-image with Xtreme Digital Solutions'>Spruce up your e-image with Xtreme Digital Solutions</a>            </h2>        </div><p class='pmar'>Spiderman can sure vouch for how important the web is to business. Evidently, any business can have a website, but if it is lost on the www, then maybe there is no point having one. So, to help your identity be unique online, Delhi-based Xtreme Digital Solutions, an online communication consultancy, crafts strategic content plans, aligning them with the business objectives of their clients.In conversation with Abhilasha Dafria for YourStory.in, Mohit Kukreja, co-founder of XDS tells more about how his e-venture offers their clients  a convenient, complete package of a high quality business...</p>"
		// ;
		// web.loadData(string, "text/html", "UTF-8");

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// R.layout.row,(String[])(titles.toArray(new String[0])));
		FeedDataAdapter feedDataAdapter = new FeedDataAdapter(this, feedItems);
		this.setListAdapter(feedDataAdapter);
	}

	private class FeedDataAdapter extends BaseAdapter {

		private Context activity;
		private List<FeedItem> feedItems;

		FeedDataAdapter(Activity activity, List<FeedItem> feedItems) {
			this.activity = activity;
			this.feedItems = feedItems;
		}

		@Override
		public int getCount() {
			return feedItems.size();
		}

		@Override
		public FeedItem getItem(int position) {
			return feedItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			TitleView titleView = null;
			if (rowView == null) {
				LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
				rowView = inflater.inflate(R.layout.row, null);
				titleView = new TitleView();
				titleView.title = (TextView) rowView.findViewById(R.id.title_row);
				rowView.setTag(titleView);
			} else {
				titleView = (TitleView) rowView.getTag();
			}

			FeedItem currentFeedItem = feedItems.get(position);
			titleView.title.setText(currentFeedItem.getTitle());

			return rowView;
		}

	}
	
	protected static class TitleView {
        protected TextView title;
    }

}
