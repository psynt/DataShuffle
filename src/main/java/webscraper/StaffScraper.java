package webscraper;

/**
 * Scraper for university Staff Webpages.  Loads all data into a datadoc object.
 * @author zane
 *
 */

import java.io.IOException;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StaffScraper extends PageScraper {

	private DataDoc dataDoc;
	
	public StaffScraper(Document doc){
		super(doc);
		dataDoc = new DataDoc();
	}
	
	@Override
	public DataDoc scrapeDocument() {

	String name = doc.getElementsByClass("fn n").text();
	String room = doc.getElementById("staffprofile-address").getElementsByClass("street-address").text();

	dataDoc.addField("Name", name);
	dataDoc.addField("room", room);
	
	return dataDoc;
	}
}