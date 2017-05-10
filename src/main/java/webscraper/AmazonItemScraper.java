package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import content.Attribute;
import content.Item;

/**
 *
 * Amazon has captcha in place to prevent robots from gathering data.
 * Work on this class is suspended.
 * @author Zane
 * @deprecated work halted
 */
@Deprecated
public class AmazonItemScraper extends PageScraper {

	public AmazonItemScraper(Document doc) {
		super(doc);
	}

	@Override
	public Item scrapeDocument() {
		addName();
		addPrice();
		addShipping();
		
		return item;
	}

	private void addShipping() {
		// TODO Auto-generated method stub
		
	}

	private void addPrice() {
		Element price = null;
		price = doc.getElementById("priceblock_ourprice");
		String priceText = price.text();
		item.addAttribute(new Attribute<String>("price", priceText));
		
	}

	private void addName() {
		Element itemName = null;
		itemName = doc.getElementById("title");
		item.addAttribute(new Attribute<String>("name", itemName.text()));
	}

}
