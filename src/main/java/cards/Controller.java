package cards;

import static cards.CardFactory.createCard;
import static splash.Controller.d;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import content.Attribute;
import content.Group;
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
import model.Data;
import sidebar.SideMenu;
import sidebar.SideMenuController;

public class Controller {
	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;
	static boolean cardSelected = true;
//	private Data d;

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
//		System.err.println(d);
		d.last().setColor("red");
		
		mainPane.setStyle("-fx-background-color: #2f4f4f;");
        
		draggingTab = new SimpleObjectProperty<>();
		
		
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

		Attribute.getAtts().keySet().forEach(e->sideMenuController.addShowTickBox(e));

		sideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane, d);

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


		makeDecks();



	}

	private void makeDecks(){
		centerPane.getChildren().clear();
		d.forEach(e -> {
			Deck cards = new Deck(draggingTab, e);

			e.stream().filter(Item::isSelected).forEach(it -> cards.getTabs().add(newCard(e,it, d.getType())));
			cards.getTabs().forEach(it -> ((Card)it).setListener(this));

			centerPane.getChildren().add(cards);
		});

	}

	public Data getData(){
		return d;
	}

	@FXML public void newGreenDeck() {
		incNumDecks();
		d.last().setColour("green");
		Deck newDeck = new Deck(draggingTab, d.last());

		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newYellowDeck() {
		incNumDecks();
		d.last().setColour("yellow");
		Deck newDeck = new Deck(draggingTab, d.last());
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newRedDeck() {
		incNumDecks();
		d.last().setColour("red");
		Deck newDeck = new Deck(draggingTab, d.last());
		centerPane.getChildren().add(newDeck);
		

	}

	public static void cardUnselect(){
	//centerPane.setOnMouseClicked(event->{
		cardSelected=false;
	//});
	}

	private Card newCard(Group g, Item item, String type) {
		final Card card = createCard(g, item, type, sideMenuController);
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
		return d.size();
	}

	private void incNumDecks(){
		d.add(new Group("Deck "+d.size()));
	}
	
	
//	@FXML
//	private CardState saveState(){
//
//		CardState saveState = new CardState();
//
//		for(int i = 0; i < centerPane.getChildren().size(); i++){
//			Deck newDeck = (Deck)centerPane.getChildren().get(i);
//			newDeck.getTabs().forEach(e -> newDeck.saveCard((Card)e));
//			saveState.addDeck(newDeck);
//		}
//
//		// Write to disk with FileOutputStream
//		FileOutputStream f_out = null;
//		ObjectOutputStream obj_out = null;
//		try {
//			f_out = new FileOutputStream("cards.data");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		// Write object with ObjectOutputStream
//		try {
//			obj_out = new ObjectOutputStream (f_out);
//			obj_out.writeObject ( saveState );
//			f_out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return saveState;
//	}


	public void notifyObserver() {
		makeDecks();
	}
}