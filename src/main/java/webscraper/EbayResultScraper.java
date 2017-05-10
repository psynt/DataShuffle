package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Class that scrapes a list of links from an ebay search results page
 * @author Zane
 *
 */

public class EbayResultScraper extends LinkScraper {

	public EbayResultScraper(Document doc) {
		super(doc);
	}

	/* (non-Javadoc)
	 * @see webscraper.LinkScraper#scrapeLinks()
	 */
	@Override
	public ArrayList<String> scrapeLinks() {

		if(this.urls!=null){
			return urls;
		}

		urls = new ArrayList<>();
		Element name = doc.getElementById("ListViewInner");
		Elements listItems = name.getElementsByClass("lvtitle");
		String url;
		for(Element item : listItems){
			Element link = item.select("a").first();
			url = link.attr("href");
			urls.add(url);
		}

		return urls;
	}

}