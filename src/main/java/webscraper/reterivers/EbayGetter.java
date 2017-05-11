package webscraper.reterivers;

import content.Attribute;
import javafx.concurrent.Task;
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
import java.util.List;
import java.util.Map;

/**
 * Created by nichita on 06/05/17.
 */
public class EbayGetter implements Getter {

    private List<String> decide(Map<String, String> args) throws MalformedURLException {

        if(args.get("auctionType").equals("Buy It Now!")) {args.put("auctionType", "BIN");}

        String searchUrl = "http://www.ebay.co.uk/sch/i.html?_from=R40&_nkw=datashuffle&_in_kw=1&_ex_kw=&_sacat=0&_mPrRngCbx=1&_udlo=2&_udhi=8&LH_AUCTYPE=1&_ftrt=901&_ftrv=1&_sabdlo=&_sabdhi=&_samilow=&_samihi=&_sadis=15&_stpos=&_sargn=-1%26saslc%3D1&_salic=3&_sop=12&_dmd=1&_ipg=50"
                .replace("datashuffle", args.get("searchTerm")).replace("minprice", args.get("min"))
                .replace("maxprice", args.get("max")).replace("AUCTYPE", args.get("auctionType"));

        Document guitarSearch = DocumentLoader.load(new URL(searchUrl));
        EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
        return thing1.scrapeLinks();

    }

    public Task<Data> getTask(Map<String, String> args) throws MalformedURLException {
        List<String> links = decide(args);
        return new Task<Data>() {
            @Override
            protected Data call() throws Exception {

                Data d = new Data("Ebay");

                ArrayList<Item> whatYouWant = new ArrayList<>();
                for (int i = 0 ; i<links.size() ; i++) {
                    String link = links.get(i);
                    Document res = DocumentLoader.load(new URL(link));
                    EbayItemScraper ebayScraper = new EbayItemScraper(res);
                    Item it = ebayScraper.scrapeDocument();
                    it.addAttribute(new Attribute<>("link", new URL(link)));


                    whatYouWant.add(it);
                    updateProgress(i,links.size());

                }
                d.add(new Group(whatYouWant,"Jimmy the search results"));
                d.get(0).setColour("green");
                return d;
            }
        };
    }

    @Override
    public Data getTheStuff(Map<String, String> args) throws MalformedURLException {

        List<String> links = decide(args);

        Data d = new Data("Ebay");
//
//        ArrayList<Item> whatYouWant = new ArrayList<>();
//        for (int i = 0 ; i<links.size() ; i++) {
//            String link = links.get(i);
//            Document res = DocumentLoader.load(new URL(link));
//            EbayItemScraper ebayScraper = new EbayItemScraper(res);
//            Item it = ebayScraper.scrapeDocument();
//            it.addAttribute(new Attribute<>("link", new URL(link)));
//
//
//            whatYouWant.add(it);
//
//        }
//        d.add(new Group(whatYouWant,"Jimmy the search results"));
//        d.get(0).setColour("green");
        return d;
    }
}
