package cards;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;


public class Controller {
	
    private static final String TAB_DRAG_KEY = "tab";
    private ObjectProperty<Tab> draggingTab;

    @FXML BorderPane mainPane;
    @FXML FlowPane centerPane;
    @FXML ToolBar toolbar;

    @FXML
    public void initialize(){

    	draggingTab = new SimpleObjectProperty<>();
    	
    	HBox box = new HBox();
    	
        TabPane cardStackLeft = createTabPane();
        TabPane cardStackRight = createTabPane();
        
        box.getChildren().add(cardStackLeft);
        box.getChildren().add(cardStackRight);

        centerPane.getChildren().add(box);
        
        ArrayList<Item> guitar = new ArrayList<>();
        try {
            guitar = scrape();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ArrayList<Card> cards  = new ArrayList<>();

        guitar.stream().forEach(e -> cards.add( createCard(e.getAttributes().get(0).toString(), e)));

        //add the left cards to the left vbox
        for(int i=0 ; i<3 ; i++) {
            cardStackLeft.getTabs().add(cards.get(i));
        }
        for(int i=3 ; i<6 ; i++) {
            cardStackRight.getTabs().add(cards.get(i));
        }

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
    

    
    
    
    
    
    
    private TabPane createTabPane()
    {
        final TabPane tabPane = new TabPane();
        tabPane.setOnDragOver(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent event)
            {
                final Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()
                        && TAB_DRAG_KEY.equals(dragboard.getString())
                        && draggingTab.get() != null
                        && draggingTab.get().getTabPane() != tabPane)
                {
                    event.acceptTransferModes(TransferMode.MOVE);
                    event.consume();
                }
            }
        });
        tabPane.setOnDragDropped(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent event)
            {
                final Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()
                        && TAB_DRAG_KEY.equals(dragboard.getString())
                        && draggingTab.get() != null
                        && draggingTab.get().getTabPane() != tabPane)
                {
                    final Tab tab = draggingTab.get();
                    tab.getTabPane().getTabs().remove(tab);
                    tabPane.getTabs().add(tab);
                    tabPane.getSelectionModel().select(tab);
                    event.setDropCompleted(true);
                    draggingTab.set(null);
                    event.consume();
                }
            }
        });
        
        
        tabPane.setMinWidth(50);
        tabPane.setTabMaxWidth(100);
        
        return tabPane;
    }

    private Card createCard(String text, Item item)
    {
        final Card card = new Card(item);
        final Label label = new Label(text);
        card.setGraphic(label);
        label.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Dragboard dragboard = label.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(TAB_DRAG_KEY);
                dragboard.setContent(clipboardContent);
                draggingTab.set(card);
                event.consume();
            }
        });
        return card;
    }

}
