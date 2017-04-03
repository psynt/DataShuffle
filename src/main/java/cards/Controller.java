package cards;

import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sidebar.SideMenu;
import sidebar.SideMenuController;
import java.util.ArrayList;

import com.sun.javafx.geom.Rectangle;

import static cards.CardFactory.createCard;
import static splash.Controller.*;

public class Controller {
	private int numDecks = 0;
	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;
	static boolean cardSelected = true;

	boolean selected;
	@FXML
	BorderPane mainPane;
	@FXML
	//static
	FlowPane centerPane;
	@FXML
	ScrollPane scrollPane;

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
	
	SideMenu sideMenu;
	SideMenuController sideMenuController;

	@FXML
	public void initialize() {

		mainPane.setStyle("-fx-background-color: #2f4f4f;");
        
		draggingTab = new SimpleObjectProperty<>();
		incNumDecks();
		Deck cardStackGreen = new Deck(draggingTab, 0, getNumDecks());
		incNumDecks();
		Deck cardStackYellow = new Deck(draggingTab, 1, getNumDecks());
		//incNumDecks();
		//Deck cardStackRed = new Deck(draggingTab, 2, getNumDecks());

		centerPane.getChildren().add(cardStackGreen);
		centerPane.getChildren().add(cardStackYellow);
		//centerPane.getChildren().add(cardStackRed);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		scrollPane.setContent(centerPane);

		scrollPane.setStyle("-fx-background-color: #2f4f4f;");
		centerPane.setStyle("-fx-background-color: #2f4f4f;");
		centerPane.setOrientation(Orientation.VERTICAL);


		int sideMenuWidth = 250;
		
		ArrayList<Item> results = getSearchResults();

		ArrayList<Card> cards = new ArrayList<>();

		results.forEach(e -> cards.add(newCard(e, getType(), getNumDecks())));

		// add the left cards to the left vbox
		for(int i=0 ; i<cards.size() ; i++){

			if((i&1) == 1) {
				cardStackYellow.getTabs().add(cards.get(i));
			} else {
				cardStackGreen.getTabs().add(cards.get(i));
			}

		}

		// create a sidebar with some content in it.
		sideMenuController = new SideMenuController();
		final Pane menuPane = sideMenuController.createSidebarItems();
		sideMenu = new SideMenu(sideMenuWidth, menuPane);
		VBox.setVgrow(menuPane, Priority.ALWAYS);

		sideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane, results);

		// layout the scene.
		StackPane buttonLocation = new StackPane();
		buttonLocation.getChildren().addAll(sideMenu.getDisplayMenuButton());
		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

		//mainPane.getChildren().add(buttonLocation);
		sideMenuContainer.setRight(sideMenu);
		sideMenuContainer.setCenter(buttonLocation);
		sideMenuContainer.setMinWidth(50);

		mainPane.setRight(sideMenuContainer);
		mainPane.setCenter(scrollPane);
		//activate if you want to be able to see the side menu container
		sideMenuContainer.setBorder(new Border( new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		//sideMenuContainer.setMinWidth(200);

//
//		ArrayList<Item> guitar = new ArrayList<>();
//		try {
//			guitar = scrape();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}



	}





	private Card newCard(Item item, String type, int deck) {
		final Card card = createCard(item, type, deck, sideMenuController);
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
		Deck newDeck = new Deck(draggingTab, 0, incNumDecks());

		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newYellowDeck() {
		Deck newDeck = new Deck(draggingTab, 1, incNumDecks());
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newRedDeck() {
		Deck newDeck = new Deck(draggingTab, 2, incNumDecks());
		centerPane.getChildren().add(newDeck);
		

	}

	public static void cardUnselect(){
	//centerPane.setOnMouseClicked(event->{
		cardSelected=false;
	//});
	}
	
	int getNumDecks(){
		return numDecks;
	}

	int incNumDecks(){
		return ++numDecks;
	}
	
}