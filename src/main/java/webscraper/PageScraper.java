package webscraper;

/**
 * Abstract class for scraping webpages for data
 * @author zane
 *
 */

import org.jsoup.nodes.Document;

import content.Item;

public abstract class PageScraper implements IPageScraper{

	protected Document doc;
	protected Item item = null;
	
	public PageScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public abstract Item scrapeDocument();

}