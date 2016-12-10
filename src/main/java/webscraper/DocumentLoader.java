package webscraper;

/**
 * Class that provides functions for loading pages into JSoup Document objects.
 * @author zane
 *
 */

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentLoader {
	
	public static Document loadDocFromUrl(String url){
		Document newDoc = null;
		try {
			newDoc = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newDoc;
	}
	
	public static Document loadDocFromFile(String filePath){
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