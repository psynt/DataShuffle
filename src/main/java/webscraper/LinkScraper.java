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
	
	public LinkScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public abstract ArrayList<String> scrapeLinks();

}
