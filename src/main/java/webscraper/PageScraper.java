package webscraper;

/**
 * Abstract class for scraping webpages for data
 * @author zane
 *
 */

import org.jsoup.nodes.Document;

public abstract class PageScraper implements IPageScraper{

	protected Document doc;
	protected DataDoc dataDoc;
	
	public PageScraper(Document doc){
		this.doc = doc;
		dataDoc = new DataDoc();
	}
	
	@Override
	public abstract DataDoc scrapeDocument();

}