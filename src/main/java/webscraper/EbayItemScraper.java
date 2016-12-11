package webscraper;

/**
 * Scraper for Ebay Item Webpages.  Loads all data into a Ttem object.
 * @author zane
 *
 */

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import content.Attribute;
import content.EbayItem;
import content.Item;
import content.Module;

public class EbayItemScraper extends PageScraper {
	
	public EbayItemScraper(Document doc) {
		super(doc);
		item = new EbayItem();
	}

	@Override
	public Item scrapeDocument() {
		
		addName();
		addCondition();
		addPrice();
		addRemainingTime();
		addShipping();
		
		return item;
	}

	private void addName() {
		Element name = doc.getElementById("itemTitle");
		String itemName = name.text().substring(15);
		item.addAttribute(new Attribute<String>("name", itemName));
	}


	private void addCondition() {
		Element condition = doc.getElementById("vi-itm-cond");
		item.addAttribute(new Attribute<String>("condition", condition.text()));
	}
	
	private void addPrice() {
		Element price = doc.getElementById("vi-mskumap-none");
		item.addAttribute(new Attribute<String>("price", price.text()));
	}
	
	private void addRemainingTime() {
		Element remainingTime = doc.getElementById("vi-cdown_timeLeft");
		item.addAttribute(new Attribute<String>("remaining time", remainingTime.text()));
	}
	
	private void addShipping() {
		Element shipping = doc.getElementById("fShippingSvc");
		item.addAttribute(new Attribute<String>("shipping", shipping.text()));
	}

}
