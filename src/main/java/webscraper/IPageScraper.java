package webscraper;

import content.Item;

/**
 * Interface for scraping webpages for data
 * @author Zane
 *
 */

public interface IPageScraper {

	/**
	 * @return Item class containing all scraped data from that page.
	 */
	public Item scrapeDocument();
	
}