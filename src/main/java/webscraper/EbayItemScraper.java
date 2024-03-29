package webscraper;


import content.Attribute;
import content.Item;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Scraper for Ebay listings.
 * Loads all data into a Item object.
 * @author zane
 *
 */
public class EbayItemScraper extends PageScraper {

	public static final int NUM_FIELDS = 6;
	private static final String NOT_FOUND = "Not mentioned";
	
	public EbayItemScraper(Document doc) {
		super(doc);
	}

	@Override
	public Item scrapeDocument() {

		if(item!=null){
			return item;
		}

		item = new Item();
		
		item.addAttribute(getName());
		item.addAttribute(getCondition());
		item.addAttribute(getPrice());
		item.addAttribute(getRemainingTime());
		item.addAttribute(getShipping());
		item.addAttribute(getImageURL());
		
		return item;
	}

	//functionality for multi-listings to be added later

	private Attribute<String> getName() {
		try {
			Element name = doc.getElementById("itemTitle");
			String itemName = name.text().substring(15);
			return new Attribute<>("name", itemName);
		} catch (NullPointerException e) {
			return new Attribute<>("name", NOT_FOUND);
		}
	}


	private Attribute<String> getCondition() {
		try{
			Element condition = doc.getElementById("vi-itm-cond");
			return new Attribute<>("condition", condition.text());
		} catch (NullPointerException e) {
			return new Attribute<>("condition", NOT_FOUND);
		}
	}
	
	private Attribute<String> getPrice() {
		try{
			Element price = doc.getElementById("vi-mskumap-none");
			return new Attribute<>("price", price.text());
		} catch (NullPointerException e) {
			return new Attribute<>("price", NOT_FOUND);
		}
	}
	
	private Attribute<String> getRemainingTime() {
		try{
			Element remainingTime = doc.getElementById("vi-cdown_timeLeft");
			return new Attribute<>("remaining time", remainingTime.text());
		} catch (NullPointerException e) {
			return new Attribute<>("remaining time", NOT_FOUND);
		}
	}
	
	private Attribute<String> getShipping() {
		try{
			Element shipping = doc.getElementById("fShippingSvc");
			return new Attribute<>("shipping", shipping.text());
		} catch (NullPointerException e) {
			return new Attribute<>("shipping", NOT_FOUND);
		}
	}

	private Attribute<String> getImageURL(){
		Element im = doc.getElementById("icImg");
		String s = im.absUrl("src");
		return new Attribute<>("image", s);
	}

}
