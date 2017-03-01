package cards;

import content.Item;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.jsoup.nodes.Document;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Controller {

    @FXML VBox cardStackLeft;
    @FXML VBox cardStackRight;



    @FXML
    public void initialize(){



        ArrayList<Item> guitar = new ArrayList<>();
        try {
            guitar = scrape();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        ArrayList<EbayCard> card  = new ArrayList<>();

        guitar.stream().forEach(e -> card.add(new EbayCard(e)));

        //add the left cards to the left vbox
        for(int i=0 ; i<3 ; i++) {


            cardStackLeft.getChildren().add(card.get(i));
            cardStackRight.getChildren().add(card.get(i*2));

        }

//



    }


    public ArrayList<Item> scrape() throws MalformedURLException {
        ArrayList<Item> whatYouWant = new ArrayList<>();
        Document guitarSearch = DocumentLoader.load(new URL("http://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xguitar.TRS0&_nkw=guitar&_sacat=0"));
        EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
        ArrayList<String> links = thing1.scrapeLinks();

        for(String link:links){
            Document res = DocumentLoader.load(new URL(link));
            EbayItemScraper guitar = new EbayItemScraper(res);



            whatYouWant.add(guitar.scrapeDocument());

        }
        return whatYouWant;
    }

}
