package org.feedreader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSSParser {

	private static final String ITEM = "item";

	public List parseRSSFeed(URL feedURL) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xmlDoc = builder.parse(feedURL.openStream());
		NodeList itemNodes = xmlDoc.getElementsByTagName(ITEM);
		List listOfItems = new ArrayList();
		for (int i = 0; i < itemNodes.getLength(); i++) {
			listOfItems.add(itemNodes.item(i));
		}
		return listOfItems;
	}
}
