package webscraper;

import org.jsoup.nodes.Document;

public abstract class PageScraper implements IPageScraper{

	protected Document doc;
	
	public PageScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public abstract DataDoc scrapeDocument();

}
