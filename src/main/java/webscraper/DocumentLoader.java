package webscraper;

/**
 * Class that provides functions for loading pages into JSoup Document objects.
 * @author zane
 *
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class DocumentLoader {
	
	public static Document load(URL url){
		Document newDoc = null;
		try {
			newDoc = Jsoup.connect(url.toString()).ignoreContentType(true).ignoreHttpErrors(true).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newDoc;
	}
	
	public static Document load(URI filePath){
		Document newDoc = null;
		try {

			File fileToRead = new File(filePath);
			newDoc = Jsoup.parse(fileToRead, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newDoc;
	}
	
}