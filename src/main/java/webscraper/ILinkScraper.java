package webscraper;

import java.util.List;

/**
 * Interface for scraping for links
 * @author zane
 *
 */

public interface ILinkScraper {
	/**
	 * @return A list of links to webpages to be passed on to a page scraper.
	 */
	List<String> scrapeLinks();
}
