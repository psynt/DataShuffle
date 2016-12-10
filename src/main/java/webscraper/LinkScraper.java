package webscraper;

import java.util.ArrayList;

/**
 * Abstract class for scraping webpages for links
 * @author zane
 *
 */

import org.jsoup.nodes.Document;

public abstract class LinkScraper implements ILinkScraper {

	protected Document doc;
	protected ArrayList<String> urls;
	
	public LinkScraper(Document doc){
		this.doc = doc;
		urls = new ArrayList<String>();
	}
	
	@Override
	public abstract ArrayList<String> scrapeLinks();

}
