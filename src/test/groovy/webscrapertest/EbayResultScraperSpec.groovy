package webscrapertest

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import webscraper.DocumentLoader
import webscraper.EbayResultScraper
/**
 * Created by nichita on 15.12.2016.
 */
class EbayResultScraperSpec extends Specification {

    @Unroll("URL: #testURL .html")
    def "Test number of links returned"(){
        given:
        @Subject
        def sc = new EbayResultScraper(DocumentLoader.load(this.getClass().getResource("/" + testURL + ".html").toURI()))

        expect:
        sc.scrapeLinks().size() == expectedOutput

        where:
        testURL             ||   expectedOutput
        "ebaySearch"   ||  1

    }
}