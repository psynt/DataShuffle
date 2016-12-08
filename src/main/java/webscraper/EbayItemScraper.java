package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class EbayItemScraper extends PageScraper {
	
	public EbayItemScraper(Document doc) {
		super(doc);
	}

	@Override
	public DataDoc scrapeDocument() {
		
		addName();
		addCondition();
		addPrice();
		addRemainingTime();
		addShipping();
		
		return dataDoc;
	}

	private void addName() {
		Element name = doc.getElementById("itemTitle");
		String itemName = name.text().substring(15);
		dataDoc.addField("name", itemName);
	}


	private void addCondition() {
		Element condition = doc.getElementById("vi-itm-cond");
		dataDoc.addField("condition", condition.text());
	}
	
	private void addPrice() {
		Element price = doc.getElementById("vi-mskumap-none");
		dataDoc.addField("price", price.text());
	}
	
	private void addRemainingTime() {
		Element remainingTime = doc.getElementById("vi-cdown_timeLeft");
		dataDoc.addField("remaining time", remainingTime.text());
	}
	
	private void addShipping() {
		Element shipping = doc.getElementById("fShippingSvc");
		dataDoc.addField("shipping", shipping.text());
	}

}
