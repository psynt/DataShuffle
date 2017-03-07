package webscraper;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for scraping webpages for links
 *
 * @author zane
 */

public class LinkScraper implements ILinkScraper {

	protected Document doc;
	protected ArrayList<String> urls=null;
	
	public LinkScraper(Document doc){
		this.doc = doc;
	}
	
	@Override
	public List<String> scrapeLinks(){
		ArrayList<String> links = new ArrayList<>();

		doc.select("a").parallelStream().map(e -> e.attr("abs:href")).forEach(e -> links.add(e));

		return links;
	}

}
