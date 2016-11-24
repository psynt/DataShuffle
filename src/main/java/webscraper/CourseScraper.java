package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseScraper extends PageScraper {

	private DataDoc dataDoc;
	
	public CourseScraper(Document doc){
		super(doc);
		dataDoc = new DataDoc();
	}
	
	@Override
	public DataDoc scrapeDocument() {
				
		//get course title
		Elements courseTitle = doc.select("title");
		String[] title = courseTitle.get(0).text().split(" -");
		dataDoc.addField("Course Title", title[0]);
		
		//get factfile
		Element factFile = doc.getElementById("ugStudyFactfile");
		Elements ffLabels = factFile.getElementsByClass("sys_factfileLabel");
		Elements ffItems = factFile.getElementsByClass("sys_factfileTextField");
		
		for(int i = 0; i < ffItems.size()-1; i++){
			dataDoc.addField(ffLabels.get(i+5).text(), ffItems.get(i+1).text());
		}
		
		//getModules
		
		
		return dataDoc;
	}
}