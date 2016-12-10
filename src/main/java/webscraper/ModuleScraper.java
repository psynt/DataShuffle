package webscraper;

/**
 * Scraper for university Module Webpages.  Loads all data into a Ttem object.
 * @author zane
 *
 */

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import content.Attribute;
import content.Item;
import content.Module;

public class ModuleScraper extends PageScraper {

	private DataDoc dataDoc;
	
	public ModuleScraper(Document doc){
		super(doc);
		item = new Module();
	}
	
	@Override
	public Item scrapeDocument() {
		
		Elements modTitle = doc.select("H2");
		String myString[] = modTitle.text().split(" ", 2);
		item.addAttribute(new Attribute<String>("Module Code", myString[0]));
		myString[1] = myString[1].replaceAll("\\(.*\\)","");
		item.addAttribute(new Attribute<String>("Module Name", myString[1]));
		
		Elements paras = doc.select("p");
		
		for(int i = 0; i < paras.size(); i++){
			String fieldData[] = paras.get(i).text().split(":");
			if(paras.get(i).text().split(":").length == 2){
				item.addAttribute(new Attribute<String>(fieldData[0], fieldData[1]));
			}
		}	
		return item;
	}
}