package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import content.Attribute;
import content.Item;

/**
 * Scraper for university Module Webpages.  Loads all data into an Item object.
 * @author zane
 *
 */
public class ModuleScraper extends PageScraper {
	
	public ModuleScraper(Document doc){
		super(doc);
	}
	
	@Override
	public Item scrapeDocument() {

		if (item != null){
			return item;
		}

		item = new Item();
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