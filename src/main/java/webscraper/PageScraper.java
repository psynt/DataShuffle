package webscraper;

/**
 * Abstract class for scraping webpages for data
 * @author zane
 *
 */

import org.jsoup.nodes.Document;

public abstract class PageScraper implements IPageScraper{

	protected Document doc;
	
	public PageScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public abstract DataDoc scrapeDocument();

}