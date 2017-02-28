package webscrapertest

import content.Attribute
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


    def "Testing with a URL"(){
        given:
        @Subject
        def sc = new EbayResultScraper(DocumentLoader.load(new URL("http://www.ebay.co.uk/itm/Rio-Acoustic-Guitar-Starter-Package-Pack-Outfit-Full-Size-Beginner-Adult-Student-/180936577416?var=&hash=item2a20a91188:m:m_1jdz_JLJwtDHhPoiVNFFA")))
        def result = sc.scrapeLinks().each { new EbayItemScraper(DocumentLoader.load(new URL(it))).scrapeDocument() }.toArray()

        expect:
        result.length > 0


    }
}