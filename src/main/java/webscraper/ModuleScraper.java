package webscraper;

import org.jsoup.nodes.Document;

public class ModuleScraper extends PageScraper {

	private Document doc;
	private DataDoc dataDoc;
	
	public ModuleScraper(){
		dataDoc = new DataDoc();
	}
	
	@Override
	public DataDoc scrapeDocument(Document doc) {
		this.doc = doc;
				
		return dataDoc;
	}

	
	
}