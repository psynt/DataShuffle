package webscraper;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class that scrapes a list of links from an ebay search results page
 * @author zane
 *
 */

public class EbayResultScraper extends LinkScraper {

	public EbayResultScraper(Document doc) {
		super(doc);
	}

	@Override
	public ArrayList<String> scrapeLinks() {
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