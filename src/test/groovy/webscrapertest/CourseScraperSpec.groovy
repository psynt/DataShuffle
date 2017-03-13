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
        def it = sc.getReqModules();
        println it

        then:
        it.parallelStream().filter{e -> e.matches(/G5\d\w\w\w/)}.toArray().length == n
        it.parallelStream().filter{e -> e.equals("Students must take all modules in this group")}.toArray().length == years

        where:
        testURL  | n | years
        "view_specification.asp" | 39 | 3

    }

}