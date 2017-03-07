package cards;

import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.jsoup.nodes.Document;
import webscraper.CourseScraper;
import webscraper.DocumentLoader;

import java.net.MalformedURLException;
import java.net.URL;


public class Controller2 {
	
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
        
        Item course = new Item();
        try {
            course = scrapeCourseSpec();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Card cards  = new Card(course);

        //course.stream().forEach(e -> cards.add( createCard(e.getAttributes().get(0).toString(), e)));

        //add the left cards to the left vbox
       cardStackLeft.getTabs().add(cards);


    }

    public Item scrapeCourseSpec() throws MalformedURLException {
        Item results;
        Document courseSearch = DocumentLoader.load(new URL("http://www.nottingham.ac.uk/ugstudy/courses/computerscience/bsc-computer-science.aspx"));
        CourseScraper course = new CourseScraper(courseSearch);
        results =course.scrapeDocument();





        return results;
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
