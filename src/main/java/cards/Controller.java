package cards;

import static cards.CardFactory.createCard;
import static splash.Controller.getSearchResults;
import static splash.Controller.getType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sidebar.SideMenu;
import sidebar.SideMenuController;

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

		centerPane.getChildren().add(cardStackGreen);
		centerPane.getChildren().add(cardStackYellow);
		
		
		
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setContent(centerPane);
		scrollPane.setStyle("-fx-background-color: #2f4f4f;");
		centerPane.setStyle("-fx-background-color: #2f4f4f;");
		centerPane.setOrientation(Orientation.VERTICAL);

		// create a sidebar with some content in it.
		int sideMenuWidth = 250;
		sideMenuController = new SideMenuController();
		final Pane menuPane = sideMenuController.createSidebarItems();
		menuPane.setStyle("-fx-background-color: #D1D1D1;");
		sideMenu = new SideMenu(sideMenuWidth, menuPane);
		VBox.setVgrow(menuPane, Priority.ALWAYS);
		
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
		
		//if loading from a file
		if(splash.Controller.getLoadFlag()){
			//splash.Controller.getLoadResults().forEach(e -> centerPane.getChildren().add(e));
			for(int i = 0; i < splash.Controller.getLoadResults().size(); i++){
				System.out.println("adding card " + i);
				centerPane.getChildren().add(splash.Controller.getLoadResults().get(i));
				((Deck) centerPane.getChildren().get(i)).readdAllCards();
			}
		}
		
		//Adds tick boxes for each label on the cards
		if (cards.size() >= 1){
			cards.get(0).getKeys().forEach( e -> sideMenuController.addShowTickBox(e) );
		}

		sideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane, results);

		// layout the scene.
		StackPane buttonLocation = new StackPane();
		buttonLocation.getChildren().addAll(sideMenu.getDisplayMenuButton());
		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

		sideMenuContainer.setRight(sideMenu);
		sideMenuContainer.setCenter(buttonLocation);
		sideMenuContainer.setMinWidth(50);

		mainPane.setRight(sideMenuContainer);
		mainPane.setCenter(scrollPane);
		//activate if you want to be able to see the side menu container
		sideMenuContainer.setBorder(new Border( new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		//sideMenuContainer.setMinWidth(200);




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


	private int getNumDecks(){
		return numDecks;
	}

	private int incNumDecks(){
		return ++numDecks;
	}
	
	
	@FXML
	private CardState saveState(){
		
		CardState saveState = new CardState();
		
		for(int i = 0; i < centerPane.getChildren().size(); i++){
			Deck newDeck = (Deck)centerPane.getChildren().get(i);
			newDeck.getTabs().forEach(e -> newDeck.saveCard((Card)e));
			saveState.addDeck(newDeck);
		}
		
		// Write to disk with FileOutputStream
		FileOutputStream f_out = null;
		ObjectOutputStream obj_out = null;
		try {
			f_out = new FileOutputStream("cards.data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Write object with ObjectOutputStream
		try {
			obj_out = new ObjectOutputStream (f_out);
			obj_out.writeObject ( saveState );
			f_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveState;
	}
	
}