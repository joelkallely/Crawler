package seleniumCrawler;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class CrawlTest extends Init{
	public static void main(String args[]) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		init();
		goToLandingPage();
		Crawler.crawl("http://192.168.150.45/#/landing");
	}

}
