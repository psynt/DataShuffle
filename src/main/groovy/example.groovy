import content.Attribute
import content.Item
import org.jsoup.nodes.Document
import webscraper.DocumentLoader
import webscraper.EbayItemScraper
import webscraper.EbayResultScraper

import java.util.stream.Collectors

/**
 * Created by nichita on 15/02/17.
 */
class eg {
}



ArrayList<Item> whatYouWant = new ArrayList<>()
Document guitarSearch = DocumentLoader.load(/*URL goes here*/)
EbayResultScraper thing1 = new EbayResultScraper(guitarSearch)
ArrayList<String> links = thing1.scrapeLinks()

for(String link:links){
    Document res = DocumentLoader.load(new URL(link))
    EbayItemScraper guitar = new EbayItemScraper(res);

    whatYouWant.add(guitar.scrapeDocument())

}


ArrayList<Item> things = links.parallelStream().map( e -> new EbayItemScraper(DocumentLoader.load(new URL(e))) ).collect(Collectors.toList())

Item f= new Item();
f.addAttribute(new Attribute("Foo","Bar"))
f.getAttributes().parallelStream().map(e -> e.getValue()).forEach( e -> println(e)) // or e.toString()