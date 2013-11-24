package org.feedreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

public class RSSParser {

	private static final String TITLE = "title";
	private static final String ITEM = "item";
	private static final String CONTENT = "content:encoded";
	private static final String LINK = "link";

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
		List<FeedItem> listOfItems = new ArrayList<FeedItem>();
		FeedItem feedItem = new FeedItem();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(feedURL.openStream());

			NodeList items = doc.getElementsByTagName(ITEM);
			for (int i = 0; i < items.getLength(); i++) {
				feedItem = new FeedItem();
				Element item = (Element) items.item(i);
				feedItem.setTitle(getValue(item, TITLE));
				feedItem.setContent(getValue(item, CONTENT));
				feedItem.setContentURL(getValue(item, LINK));
				listOfItems.add(feedItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfItems;
	}

	private String getValue(Element parent, String nodeName) {
		return parent.getElementsByTagName(nodeName).item(0).getFirstChild()
				.getNodeValue();
	}
}
