package webscraper.reterivers;

import content.Attribute;
import model.Group;
import content.Item;
import model.Data;
import org.jsoup.nodes.Document;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nichita on 06/05/17.
 */
public class EbayGetter implements Getter {
    @Override
    public Data getTheStuff(Map<String, String> args) throws MalformedURLException {

        Data d = new Data("Ebay");

        if(args.get("auctionType").equals("Buy It Now!")) {args.put("auctionType", "BIN");}

        String searchUrl = "http://www.ebay.co.uk/sch/i.html?_from=R40&_nkw=datashuffle&_in_kw=1&_ex_kw=&_sacat=0&_mPrRngCbx=1&_udlo=2&_udhi=8&LH_AUCTYPE=1&_ftrt=901&_ftrv=1&_sabdlo=&_sabdhi=&_samilow=&_samihi=&_sadis=15&_stpos=&_sargn=-1%26saslc%3D1&_salic=3&_sop=12&_dmd=1&_ipg=50"
                .replace("datashuffle", args.get("searchTerm")).replace("minprice", args.get("min"))
                .replace("maxprice", args.get("max")).replace("AUCTYPE", args.get("auctionType"));

        ArrayList<Item> whatYouWant = new ArrayList<>();
        Document guitarSearch = DocumentLoader.load(new URL(searchUrl));
        EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
        ArrayList<String> links = thing1.scrapeLinks();

        for (String link : links) {
            Document res = DocumentLoader.load(new URL(link));
            EbayItemScraper ebayScraper = new EbayItemScraper(res);
            Item it = ebayScraper.scrapeDocument();
            it.addAttribute(new Attribute<>("link", new URL(link)));


            whatYouWant.add(it);

        }
        d.add(new Group(whatYouWant));
        d.get(0).setColour("green");
        return d;
    }
}
