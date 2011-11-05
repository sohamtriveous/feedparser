package org.feedreader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class RSSParser {

	private static final String ROOT_ELEMENT = "rss";
	private static final String TITLE = "title";
	private static final String ITEM = "item";

	public List getListOfTitles(URL feedURL)
			throws ParserConfigurationException, SAXException, IOException {
		final List listOfItems = new ArrayList();

		RootElement root = new RootElement(ROOT_ELEMENT);
		Element itemElement = root.getChild("channel").getChild(ITEM);
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
