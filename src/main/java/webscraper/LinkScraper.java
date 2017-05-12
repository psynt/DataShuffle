package webscraper;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for scraping webpages for links
 *
 * @author Zane
 */

public class LinkScraper implements ILinkScraper {

	protected Document doc;
	protected ArrayList<String> urls=null;
	
	public LinkScraper(Document doc){
		this.doc = doc;
	}
	
	/* (non-Javadoc)
	 * @see webscraper.ILinkScraper#scrapeLinks()
	 */
	@Override
	public List<String> scrapeLinks(){
		ArrayList<String> links = new ArrayList<>();

		doc.select("a").parallelStream().map(e -> e.attr("abs:href")).forEach(e -> links.add(e));

		return links;
	}

}
