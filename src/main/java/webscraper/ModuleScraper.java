package webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ModuleScraper extends PageScraper {

	private Document doc;
	private DataDoc dataDoc;
	
	public ModuleScraper(){
		dataDoc = new DataDoc();
	}
	
	@Override
	public DataDoc scrapeDocument(Document doc) {
		this.doc = doc;
		
		Elements modTitle = doc.select("H2");
		String myString[] = modTitle.text().split(" ", 2);
		dataDoc.addField("Module Code", myString[0]);
		myString[1] = myString[1].replaceAll("\\(.*\\)","");
		dataDoc.addField("Module Name", myString[1]);
		
		Elements paras = doc.select("p");
		for(int i = 0; i < paras.size(); i++){
			System.out.println(paras.get(i).text());
		}
		
		for(int i = 0; i < paras.size(); i++){
			String fieldData[] = paras.get(i).text().split(":");
			if(paras.get(i).text().split(":").length == 2){
				dataDoc.addField(fieldData[0], fieldData[1]);
			}
		}
		
		return dataDoc;
	}

}