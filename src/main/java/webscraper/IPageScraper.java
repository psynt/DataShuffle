package webscraper;

import content.Item;

/**
 * Interface for scraping webpages for data
 * @author zane
 *
 */

public interface IPageScraper {

	public Item scrapeDocument();
	
}