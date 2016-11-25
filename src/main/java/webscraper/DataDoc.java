package webscraper;

import java.util.HashMap;

public class DataDoc {

	private HashMap<String, String> dataFields;
	
	public DataDoc(){
		dataFields = new HashMap<String, String>();
	}
	
	public void addField(String key, String data){
		dataFields.put(key, data);
	}
	
}
