package cards;

import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import org.jsoup.nodes.Document;
import sidebar.SideMenu;
import sidebar.SideMenuController;
import sidebar.SideMenuItems;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static cards.CardFactory.createCard;

public class Controller {
	private int numDecks = 0;
	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;

	@FXML
	BorderPane mainPane;
	@FXML
	FlowPane centerPane;
	@FXML
	BorderPane sideMenuContainer;
	
	@FXML
	ToolBar toolbar;
	@FXML
	Button newGreenDeckButton;
	@FXML 
	Button newYellowDeckButton;
	@FXML 
	Button newRedDeckButton;

	@FXML
	public void initialize() {

        mainPane.setStyle("-fx-background-color: #2f4f4f;");
        
		draggingTab = new SimpleObjectProperty<>();

		Deck cardStackLeft = new Deck(draggingTab, 0);

		Deck cardStackRight = new Deck(draggingTab, 2);

		centerPane.getChildren().add(cardStackLeft);
		centerPane.getChildren().add(cardStackRight);

		int sideMenuWidth = 250;

		// create a sidebar with some content in it.
		final Pane menuPane = SideMenuItems.createSidebarItems();
		SideMenu sideMenu = new SideMenu(sideMenuWidth, menuPane);
		VBox.setVgrow(menuPane, Priority.ALWAYS);

		SideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane);

		// layout the scene.
		StackPane buttonLocation = new StackPane();
		buttonLocation.getChildren().addAll(sideMenu.getDisplayMenuButton());
		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

		//mainPane.getChildren().add(buttonLocation);
		sideMenuContainer.setRight(sideMenu);
		sideMenuContainer.setCenter(buttonLocation);
		mainPane.setRight(sideMenuContainer);
		mainPane.setCenter(centerPane);
		//sideMenuContainer.setMinWidth(200);

		//activate if you want to be able to see the side menu container
//		sideMenuContainer.setBorder(new Border( new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

		ArrayList<Item> guitar = new ArrayList<>();
		try {
			guitar = scrape();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		ArrayList<Card> cards = new ArrayList<>();

		guitar.forEach(e -> cards.add(newCard(e, "Ebay", getNumDecks())));

		// add the left cards to the left vbox
		for (int i = 0; i < 3; i++) {
			cardStackLeft.getTabs().add(cards.get(i));
		}
		for (int i = 3; i < 6; i++) {
			cardStackRight.getTabs().add(cards.get(i));
		}

	}

	public ArrayList<Item> scrape() throws MalformedURLException {
		ArrayList<Item> whatYouWant = new ArrayList<>();
		Document guitarSearch = DocumentLoader.load(new URL(
				"http://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xguitar.TRS0&_nkw=guitar&_sacat=0"));
		EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
		ArrayList<String> links = thing1.scrapeLinks();

		for (String link : links) {
			Document res = DocumentLoader.load(new URL(link));
			EbayItemScraper guitar = new EbayItemScraper(res);

			whatYouWant.add(guitar.scrapeDocument());

		}
		return whatYouWant;
	}

	private Card newCard(Item item, String type, int g) {
		final Card card = createCard(item,type, g);
		card.getGraphic().setOnDragDetected(event -> {
            Dragboard dragboard = card.getGraphic().startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(TAB_DRAG_KEY);
            dragboard.setContent(clipboardContent);
            draggingTab.set(card);
            event.consume();
        });

		return card;
	}

	@FXML public void newGreenDeck() {
		Deck newDeck = new Deck(draggingTab, 0);
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newYellowDeck() {
		Deck newDeck = new Deck(draggingTab, 1);
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newRedDeck() {
		Deck newDeck = new Deck(draggingTab, 2);
		centerPane.getChildren().add(newDeck);
	}

	int getNumDecks(){
	numDecks++;
	return numDecks;
	}
	
}
