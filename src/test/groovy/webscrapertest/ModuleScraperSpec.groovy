package webscrapertest

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import webscraper.DocumentLoader
import webscraper.ModuleScraper

/**
 * Created by nichita on 15.12.2016.
 */
class ModuleScraperSpec extends Specification {


    @Unroll("URL: #testURL .html")
    def "Test that right attributes are returned"(){
        given:
        @Subject
        def sc = new ModuleScraper(DocumentLoader.load(this.getClass().getResource("/" + testURL + ".html").toURI()))

        when:
        def it = sc.scrapeDocument().getAttributes()
        println it

        then:
        it.size() == 2
        it.get(0).getName() == "Module Code"
        it.get(1).getName() == "Module Name"

        where:
        testURL << ["ModuleTest"]


    }

}