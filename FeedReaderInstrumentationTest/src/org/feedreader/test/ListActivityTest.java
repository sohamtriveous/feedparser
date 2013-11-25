package org.feedreader.test;

import org.feedreader.ListOfTitlesActivity;

import android.test.ActivityInstrumentationTestCase2;

public class ListActivityTest extends
		ActivityInstrumentationTestCase2<ListOfTitlesActivity> {


	public ListActivityTest() {
		super("org.feedreader.test", ListOfTitlesActivity.class);

	}

	public void testShowTheFeedItemsInListView() throws Exception{
		assertEquals(getActivity().getListView().getCount(),25);
	}
}
