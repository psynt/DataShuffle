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

import content.Attribute;
import content.Item;

public class StaffScraper extends PageScraper {

	private DataDoc dataDoc;
	
	public StaffScraper(Document doc){
		super(doc);
		item = new Staff();
	}
	
	@Override
	public Item scrapeDocument() {
		getName();	
		getRoom();	
		getJobTitle();
		getFaculty();
		
		return item;
	}

	private void getFaculty() {
		String faculty = doc.getElementsByClass("org").text();
		item.addAttribute(new Attribute<String>("Faculty", faculty));
	}

	private void getJobTitle() {
		String jobTitle = doc.select("p").get(1).text().split(",")[0];
		item.addAttribute(new Attribute<String>("JobTitle", jobTitle));
	}

	private void getRoom() {
		String room = doc.getElementById("staffprofile-address").getElementsByClass("street-address").text();
		item.addAttribute(new Attribute<String>("room", room));
	}

	private void getName() {
		String name = doc.getElementsByClass("fn n").text();
		item.addAttribute(new Attribute<String>("Name", name));
	}
}