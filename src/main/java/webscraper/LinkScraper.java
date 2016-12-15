package webscraper;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Abstract class for scraping webpages for links
 *
 * @author zane
 */

public abstract class LinkScraper implements ILinkScraper {

	protected Document doc;
	protected ArrayList<String> urls=null;
	
	public LinkScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public abstract ArrayList<String> scrapeLinks();

}
