package webscrapertest

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import webscraper.DocumentLoader
import webscraper.EbayItemScraper

/**
 * Created by nichita on 15.12.2016.
 */
class EbayItemScraperSpec extends Specification {

    @Unroll
    def "Test attributes returned (for single item listings) URL: #testURL .html"(){
        given:
        @Subject
        def sc = new EbayItemScraper(DocumentLoader.load(this.getClass().getResource("/" + testURL + ".html").toURI()))

        when:
        def it = sc.scrapeDocument();

        then:
        it.getAttributes().size() == EbayItemScraper.NUM_FIELDS;

        where:
        testURL << ["ebayimgtest"]


    }

}