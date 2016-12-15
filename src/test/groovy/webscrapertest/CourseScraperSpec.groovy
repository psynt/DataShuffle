package webscrapertest

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import webscraper.CourseScraper
import webscraper.DocumentLoader
/**
 * @Author Zane
 */

class CourseScraperSpec extends Specification {

    @Unroll
    def "Test attributes returned URL: #testURL .html"(){
        given:
        @Subject
        def sc = new CourseScraper(DocumentLoader.load(this.getClass().getResource("/" + testURL + ".html").toURI()))

        when:
        def it = sc.scrapeDocument();

        then:
        it.getAttributes().size() == CourseScraper.NUM_FIELDS;

        where:
        testURL << ["CourseTrimmed","Course"]

    }

}