package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import content.Attribute;
import content.Item;

import java.util.ArrayList;

/**
 * Scraper for university Course Webpages.  Loads all data into an Item object.
 * @author zane
 *
 */
public class CourseScraper extends PageScraper {
	
	public CourseScraper(Document doc){
		super(doc);
		item = new Item();
	}
	
	@Override
	public Item scrapeDocument() {

		if (item!=null){
			return item;
		}

		item = new Item();
		item.addAttribute(getCourseTitle());
		item.addAttributes(getFactFileData());
		
		return item;
	}

	private ArrayList<Attribute> getFactFileData() {
		ArrayList<Attribute> atts = new ArrayList<>();
		Element factFile = doc.getElementById("ugStudyFactfile");
		Elements ffLabels = factFile.getElementsByClass("sys_factfileLabel");
		Elements ffItems = factFile.getElementsByClass("sys_factfileTextField");
		
		for(int i = 0; i < ffItems.size()-1; i++){
			atts.add(new Attribute<String>(ffLabels.get(i+5).text(), ffItems.get(i+1).text()));
		}

		return atts;
	}

	private Attribute<String> getCourseTitle() {
		Elements courseTitle = doc.select("title");
		String[] title = courseTitle.get(0).text().split(" -");
		return new Attribute<String>("Course Title", title[0]);
	}
}