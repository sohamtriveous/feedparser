package org.feedreader.test;

import junit.framework.TestCase;

import org.feedreader.ShortenURL;
import org.feedreader.ShortenURL.BitlyReply;
import org.feedreader.ShortenURL.BitlyService;

public class ShortenURLTest extends TestCase {

	// Register a login a http://bit.ly
	private static String LOGIN = "multunus";
	private static String APIKEY = "R_5648b4e6eca872e60ea39e3ee3d533a9";

	private ShortenURL bitly;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		bitly = new ShortenURL(LOGIN, APIKEY);
	}

	/**
	 * Tests to use the class as it should be used by users.
	 */
	public void testGetShortUrlAsShouldBeUsed() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		String shortUrl = bitly.getShortUrl(urlToShorten);
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}

	/**
	 * Tests bit.ly result codes and messages.
	 */
	public void testGetResultMessages() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertEquals(new Integer(0), reply.errorCode);
		assertEquals("", reply.errorMessage);
		assertEquals("OK", reply.statusCode);
	}

	/**
	 * Tests bit.ly shortUrl returned.
	 */
	public void testGetShortUrl() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}

	/**
	 * Tests j.mp shortUrl returned.
	 */
	public void testGetShortUrlFromJMP() throws Exception {
		ShortenURL.service = BitlyService.JMP;
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://j.mp/"));
	}

	/**
	 * Test to confirm different chars work.
	 */
	public void testGetShortUrlFromDifferentChars() throws Exception {
		String urlToShorten = "https://mail.google.com/mail/?shva=1#search/bit.ly";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}
}