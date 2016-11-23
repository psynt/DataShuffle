package webscraper;

import org.jsoup.nodes.Document;

public abstract class PageScraper implements IPageScraper{

	@Override
	public abstract DataDoc scrapeDocument(Document doc);

}
