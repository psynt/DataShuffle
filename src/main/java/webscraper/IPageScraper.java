package webscraper;

import org.jsoup.nodes.Document;

public interface IPageScraper {

	public DataDoc scrapeDocument(Document doc);
	
}
