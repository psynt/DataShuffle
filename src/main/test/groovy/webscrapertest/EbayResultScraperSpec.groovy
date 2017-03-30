package webscrapertest

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import webscraper.DocumentLoader
import webscraper.EbayItemScraper
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

    @Ignore
    def "Testing with http"(){
        given:
        @Subject
        def sc = new EbayResultScraper(DocumentLoader.load(new URL("http://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xguita.TRS0&_nkw=guitar&_sacat=0")))
        def result = sc.scrapeLinks().each { new EbayItemScraper(DocumentLoader.load(new URL(it))).scrapeDocument() }.toArray()

        expect:
        result.length > 0


    }
}