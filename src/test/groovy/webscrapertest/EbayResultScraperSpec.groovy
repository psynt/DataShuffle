package webscrapertest

import spock.lang.Specification
import webscraper.DocumentLoader
import webscraper.EbayResultScraper
/**
 * Created by nichita on 15.12.2016.
 */
class EbayResultScraperSpec extends Specification {
    def "Test number of links returned"(){
        given:
        EbayResultScraper sc = new EbayResultScraper(DocumentLoader.load(this.getClass().getResource("/ebaySearch.html").toURI()))

        expect:
        sc.scrapeLinks().size() == 1


    }
}