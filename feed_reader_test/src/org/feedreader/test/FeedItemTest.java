package org.feedreader.test;

import org.feedreader.FeedItem;

import junit.framework.TestCase;

public class FeedItemTest extends TestCase {
	private FeedItem feedItem = new FeedItem();

	public FeedItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCopy(){
		feedItem.setContent("content");
		feedItem.setTitle("Title");
		feedItem.setLink("link");
		FeedItem copiedItem = feedItem.copy();
		assertEquals(feedItem,copiedItem);
		assertEquals(feedItem.toString(),copiedItem.toString());
	}
}
