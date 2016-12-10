package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AmazonItemScraper extends PageScraper {

	public AmazonItemScraper(Document doc) {
		super(doc);
	}

	@Override
	public DataDoc scrapeDocument() {
		addName();
		addPrice();
		addShipping();
		
		return dataDoc;
	}

	private void addShipping() {
		// TODO Auto-generated method stub
		
	}

	private void addPrice() {
		Element price = null;
		price = doc.getElementById("priceblock_ourprice");
		String priceText = price.text();
		dataDoc.addField("price", priceText);
		
	}

	private void addName() {
		Element itemName = null;
		itemName = doc.getElementById("title");
		dataDoc.addField("name", itemName.text());
	}

}
