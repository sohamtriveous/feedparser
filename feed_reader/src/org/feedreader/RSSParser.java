package org.feedreader;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class RSSParser {

	private static final String LINK_FIELD = "contentURL";
	private static final String CONTENT_FIELD = "content";
	private static final String CHANNEL = "channel";
	private static final String ROOT_ELEMENT = "rss";
	private static final String TITLE = "title";
	private static final String ITEM = "item";
	private static final String CONTENT_URI = "http://purl.org/rss/1.0/modules/content/";
	private static final String CONTENT = "encoded";
	private static final String LINK_URI = "http://rssnamespace.org/feedburner/ext/1.0";
	private static final String LINK = "origLink";

	private URL feedURL = null;

	public RSSParser(String urlString) throws Exception {
		Log.d("RSSParser", "urlString " + urlString);
		try {
			feedURL = new URL(urlString);
			Log.d("RSSParser", "Actual URL " + feedURL.toString());
		} catch (MalformedURLException exception) {
			throw new Exception("Invalid URL Exception");
		}
	}

	public List<FeedItem> getListOfItemsFromFeed() throws Exception {
		final List<FeedItem> listOfItems = new ArrayList<FeedItem>();
		final FeedItem feedItem = new FeedItem();
		RootElement root = new RootElement(ROOT_ELEMENT);
		Element itemElement = root.getChild(CHANNEL).getChild(ITEM);
		itemElement.setEndElementListener(new EndElementListener() {
			public void end() {
				listOfItems.add(feedItem.copy());
			}
		});

		itemElement.getChild(TITLE).setEndTextElementListener(
				new RSSParserEndTextElementListener(feedItem, TITLE));
		itemElement.getChild(CONTENT_URI, CONTENT).setEndTextElementListener(
				new RSSParserEndTextElementListener(feedItem, CONTENT_FIELD));
		itemElement.getChild(LINK_URI, LINK).setEndTextElementListener(
				new RSSParserEndTextElementListener(feedItem, LINK_FIELD));
		Xml.parse(feedURL.openStream(), Xml.Encoding.UTF_8, root
				.getContentHandler());
		return listOfItems;
	}

	private class RSSParserEndTextElementListener implements
			EndTextElementListener {

		private String setterMethodName = "set";
		private FeedItem feedItem;

		RSSParserEndTextElementListener(FeedItem feedItem, String field) {
			String firstLetter = new String("" + field.charAt(0));
			this.setterMethodName += field.replaceFirst(firstLetter,
					firstLetter.toUpperCase());
			this.feedItem = feedItem;
		}

		@Override
		public void end(String body) {
			try {
				Method method = feedItem.getClass().getMethod(setterMethodName,
						String.class);
				method.invoke(feedItem, body);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}
}
