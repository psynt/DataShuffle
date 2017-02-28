package cards;

import content.Attribute;
import content.Item;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jsoup.nodes.Document;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Controller2 {



    @FXML Pane mainPane;
    @FXML VBox cardStackLeft;
    @FXML VBox cardStackRight;



    @FXML
    public void initialize(){

        // Creating a card for each column of the two vbox's
      //  DocumentLoader guitar = new DocumentLoader();



        ArrayList<Item> guitar = new ArrayList<>();
        try {
            guitar = scrape();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        String x = guitar.get(1);

        Item x = guitar.get(1);
        ArrayList<Attribute> a = x.getAttributes();
        Attribute z = a.get(1);
        String nem = a.get(0).toString();
        String g = a.get(2).toString();
        String gx = a.get(3).toString();
        String gy = a.get(4).toString();
        String gz = a.get(5).toString();

        String something = z.toString();











        ebayCard c1 = new ebayCard(something,g, gx, gy, nem );

       // ebayCard c2 = new ebayCard("01","12.00","good","2 hours", "12.00");
        //ebayCard c3 = new ebayCard("01","12.00","good","2 hours", "12.00");
       // ebayCard c4 = new ebayCard("01","12.00","good","2 hours", "12.00");
       // ebayCard c5 = new ebayCard("01","12.00","good","2 hours", "12.00");
       // ebayCard c6 = new ebayCard("01","12.00","good","2 hours", "12.00");

        //add the left cards to the left vbox
        cardStackLeft.getChildren().add(0,c1);
        //cardStackLeft.getChildren().add(1,c2);
       // cardStackLeft.getChildren().add(2,c3);

        //add the right cards to the right vbox
        //cardStackRight.getChildren().add(0,c4);
        //cardStackRight.getChildren().add(1,c5);
        //cardStackRight.getChildren().add(2,c6);





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
