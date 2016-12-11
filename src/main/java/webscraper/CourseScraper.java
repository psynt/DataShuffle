package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import content.Attribute;
import content.Item;

public class CourseScraper extends PageScraper {
	
	public CourseScraper(Document doc){
		super(doc);
		item = new Item();
	}
	
	@Override
	public Item scrapeDocument() {
				
		getCourseTitle();
		getFactFileData();
		//getModules
		
		return item;
	}

	private void getFactFileData() {
		Element factFile = doc.getElementById("ugStudyFactfile");
		Elements ffLabels = factFile.getElementsByClass("sys_factfileLabel");
		Elements ffItems = factFile.getElementsByClass("sys_factfileTextField");
		
		for(int i = 0; i < ffItems.size()-1; i++){
			item.addAttribute(new Attribute<String>(ffLabels.get(i+5).text(), ffItems.get(i+1).text()));
		}
	}

	private void getCourseTitle() {
		Elements courseTitle = doc.select("title");
		String[] title = courseTitle.get(0).text().split(" -");
		item.addAttribute(new Attribute<String>("Course Title", title[0]));
	}
}