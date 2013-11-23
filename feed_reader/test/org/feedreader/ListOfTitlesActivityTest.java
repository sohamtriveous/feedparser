package org.feedreader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.app.Activity;
import android.app.ListActivity;
import android.view.View;
import android.widget.ListView;

@RunWith(RobolectricTestRunner.class)
public class ListOfTitlesActivityTest {
	
	String EXPECTED_XML_RESPONSE = "<?xml version=\"1.0\"?>\n" + 
			"<rss version=\"2.0\" xmlns:rss5=\"http://rss5.org/\">\n" + 
			"	<channel>\n" + 
			"		<title>Dave Winer's linkblog feed</title>\n" + 
			"		<link>http://static.scripting.com/myReallySimple/linkblog.html</link>\n" + 
			"		<description>Dave Winer's linkblog. It's my personal contribution to the World Wide River.</description>\n" + 
			"		<language>en-us</language>\n" + 
			"		<pubDate>Sat, 23 Nov 2013 09:38:48 GMT</pubDate>\n" + 
			"		<lastBuildDate>Sat, 23 Nov 2013 09:38:48 GMT</lastBuildDate>\n" + 
			"		<generator>Radio2 v0.87</generator>\n" + 
			"		<docs>http://cyber.law.harvard.edu/rss/rss.html</docs>\n" + 
			"		<webMaster>dave.winer@gmail.com (Dave Winer)</webMaster>\n" + 
			"		<rss5:account service=\"facebook\">dave.winer.12</rss5:account>\n" + 
			"		<rss5:account service=\"twitter\">davewiner</rss5:account>\n" + 
			"		<rss5:avatar>http://static.scripting.com/images/rssMug.gif</rss5:avatar>\n" + 
			"		<cloud domain=\"rpc.rsscloud.org\" port=\"5337\" path=\"/rsscloud/pleaseNotify\" registerProcedure=\"\" protocol=\"http-post\" />\n" + 
			"		<rss5:archive>\n" + 
			"			<rss5:url>http://static.scripting.com/myReallySimple/</rss5:url>\n" + 
			"			<rss5:filename>linkblog.xml</rss5:filename>\n" + 
			"			<rss5:startDay>2010-12-25</rss5:startDay>\n" + 
			"			<rss5:endDay>2013-11-23</rss5:endDay>\n" + 
			"			</rss5:archive>\n" + 
			"		<rss5:localTime>11/23/2013; 4:38:48 AM</rss5:localTime>\n" + 
			"		<item>\n" + 
			"                        <title> sfsdfd </title>\n" + 
			"      			<description>Dropbox CEO: Jobs wanted to \"kill\" the service.</description>\n" + 
			"			<link>http://bit.ly/1eoqkKO</link>\n" + 
			"			<pubDate>Sat, 23 Nov 2013 09:38:44 GMT</pubDate>\n" + 
			"			<guid>http://bit.ly/1eoqkKO?id=20133</guid>\n" + 
			"			<rss5:linkFull>http://www.readability.com/read?url=http%3A%2F%2Fwww.tuaw.com%2F2013%2F11%2F22%2Fdropbox-ceo-jobs-wanted-to-kill-the-service%2F</rss5:linkFull>\n" + 
			"			</item>\n" + 
			"		<item>\n" + 
			"                         <title> sfsdfd </title>\n" + 
			"			<description>I've been finding more applications for BitTorrent Sync, which is a peer-to-peer file sharing utility.</description>\n" + 
			"			<link>http://bit.ly/18X0bOG</link>\n" + 
			"			<pubDate>Sat, 23 Nov 2013 02:01:58 GMT</pubDate>\n" + 
			"			<guid>http://bit.ly/18X0bOG?id=20132</guid>\n" + 
			"			<rss5:linkFull>http://scripting.com/2013/11/22/anExperimentWithBtsync</rss5:linkFull>\n" + 
			"			</item>\n" + 
			"		</channel>\n" + 
			"	</rss>";
	
	private ActivityController activityController;
	
	@Before
	public void setup(){
		 Robolectric.getFakeHttpLayer().interceptHttpRequests(false);
		 activityController = Robolectric.buildActivity(ListOfTitlesActivity.class);
	}

	@Test
	public void onCreate_shouldShowTheFeedItemsInListView() {
		
		///Robolectric.addPendingHttpResponse(404, EXPECTED_XML_RESPONSE);
		activityController.create();
		ListActivity activity = (ListActivity)activityController.get();
		assertEquals(1,activity.getListView().getCount());
	}
	
	@Test
	public void onClickOnListViewItem_shouldShowTheItemDetails() {
//		activityController.create();
//		ListActivity activity = (ListActivity)activityController.get();
//		View view = activity.getListView().getChildAt(0);
//		view.callOnClick();
		//activity.findViewById(id)
		
	}

}
