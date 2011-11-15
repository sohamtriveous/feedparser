package org.feedreader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.content.res.Resources;
import android.sax.Element;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class RSSParser {

	private static final String CHANNEL = "channel";
	private static final String ROOT_ELEMENT = "rss";
	private static final String TITLE = "title";
	private static final String ITEM = "item";

	private URL feedURL = null;
	
	public RSSParser(String urlString)throws Exception {
		Log.d("RSSParser", "URL "+urlString);
		try{
			feedURL = new URL(urlString);
		}
		catch(MalformedURLException exception){
			throw new Exception("Invalid URL Exception");
		}
	}

	public List<String> getListOfTitles()
			throws ParserConfigurationException, SAXException, IOException {
		final List<String> listOfItems = new ArrayList<String>();

		RootElement root = new RootElement(ROOT_ELEMENT);
		Element itemElement = root.getChild(CHANNEL).getChild(ITEM);
		itemElement.getChild(TITLE).setEndTextElementListener(
				new EndTextElementListener() {
					@Override
					public void end(String body) {
						listOfItems.add(body);
					}
				});
		Xml.parse(feedURL.openStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		return listOfItems;
	}
}
