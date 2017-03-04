package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import content.Attribute;
import content.Item;

import java.util.*;
import java.util.stream.Collectors;

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
	
	@Override
	public Item scrapeDocument() {

		if (item!=null){
			return item;
		}

		item = new Item();
//		item.addAttribute(getCourseTitle());
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
	public List<String> getReqModules(){
		ArrayList<String> atts = new ArrayList<>();

		for (Element row : doc.select(":contains(Compulsory)~tr:not(:contains(Assessment criteria)~tr)")) {
			//for (Element row : ) {
				//Elements tds = ;
				//if(tds.size()>0) {
				//table.select("tr").forEach(row -> {
					row.select("td").forEach (it -> {
						if (it.text().matches("[A-Z]\\d\\d[A-Z][A-Z][A-Z]") || it.text().matches("Students.*")) {
							atts.add(it.text());
						}
					});
				//}
			//});
		}


		return atts;//.parallelStream().collect(Collectors.toList());
	}
}