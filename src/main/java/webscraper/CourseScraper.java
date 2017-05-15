package webscraper;

import content.Attribute;
import content.Item;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Scraper for University of Nottingham Course Webpages.  Loads all data into an Item object.
 * @author zane
 *
 */
public class CourseScraper extends PageScraper {
	
	public static final int NUM_FIELDS = 10;
	
	public CourseScraper(Document doc){
		super(doc);
	}

	/**
	 *
	 * @return nothing meaningful
	 * @deprecated not actually useful
	 */
	@Override
	@Deprecated
	public Item scrapeDocument() {

		if (item!=null){
			return item;
		}

		item = new Item();
//		item.addAttribute(getCourseTitle());
//		item.addAttribute(getReqModules());
//		item.addAttributes(getFactFileData());

		return item;
	}

	/**
	 * Function to scrape all data from the course fact file.
	 * @return ArrayList of attributes in the course fact file.
	 */
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

	/**
	 * Function to scrape the title of the course.
	 * @return Attribute containing the title of the course.
	 */
	private Attribute<String> getCourseTitle() {
		Elements courseTitle = doc.select("title");
		String[] title = courseTitle.get(0).text().split(" -");
		return new Attribute<String>("Course Title", title[0]);
	}


	/*
		first <td> in all <tr>s
	 */

	/**
	 * gets all module codes on current programmespec course page
	 * @return list containting all the module codes + some extra information between codes (e.g. compulsory/ groups)
	 */
	public List<String> getReqModules(){
		ArrayList<String> atts = new ArrayList<>();

//		System.err.println(doc.select(":root"));//.select(":contains(Part)"));//:not(:contains(Assessment criteria)~tr)"));

		for (Element row : doc.select(":root")) {
			row.select("td").forEach (it -> {
				if (it.text().matches("([A-Z]|[0-9])([A-Z]|[0-9])([A-Z]|[0-9])([A-Z]|[0-9])([A-Z]|[0-9])([A-Z]|[0-9])") || it.text().contains("this group")) {
					System.err.println("adding" + it.text());
					atts.add(it.text());
				}
			});
			if(row.text().contains("please see the University of Nottingham Quality Framework to be found at")) break;
		}
		atts.remove(atts.size()-1);

		return atts;
	}
}