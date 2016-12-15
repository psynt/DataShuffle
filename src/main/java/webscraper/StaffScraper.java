package webscraper;


import org.jsoup.nodes.Document;

import content.Attribute;
import content.Item;

/**
 * Scraper for university Staff Webpages.  Loads all data into an Item object.
 * @author zane
 *
 */
public class StaffScraper extends PageScraper {
	
	public StaffScraper(Document doc){
		super(doc);
	}
	
	@Override
	public Item scrapeDocument() {

		if (item!=null){
			return item;
		}

		item = new Item();

		item.addAttribute(getName());
		item.addAttribute(getRoom());
		item.addAttribute(getJobTitle());
		item.addAttribute(getFaculty());
		
		return item;
	}

	private Attribute<String> getFaculty() {
		String faculty = doc.getElementsByClass("org").text();
		return new Attribute<String>("Faculty", faculty);
	}

	private Attribute<String> getJobTitle() {
		String jobTitle = doc.select("p").get(1).text().split(",")[0];
		return new Attribute<String>("JobTitle", jobTitle);
	}

	private Attribute<String> getRoom() {
		String room = doc.getElementById("staffprofile-address").getElementsByClass("street-address").text();
		return new Attribute<String>("room", room);
	}

	private Attribute<String> getName() {
		String name = doc.getElementsByClass("fn n").text();
		return new Attribute<String>("Name", name);
	}
}