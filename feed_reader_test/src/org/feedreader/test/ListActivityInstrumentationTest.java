package org.feedreader.test;

import org.feedreader.ListOfTitlesActivity;
import org.feedreader.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;

public class ListActivityInstrumentationTest extends
		ActivityInstrumentationTestCase2<ListOfTitlesActivity> {

	private Solo solo;

	public ListActivityInstrumentationTest() {
		super("org.feedreader.test", ListOfTitlesActivity.class);

	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}


	@Smoke
	public void testTitleListing() throws Exception {
		assertEquals(true,solo.searchText(getActivity().getString(R.string.app_name)));
		assertEquals(Constants.NUMBER_OF_ITEMS,getActivity().getListAdapter().getCount());
	}
}
