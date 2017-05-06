package cards;

import content.Attribute;
import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Group;
import sidebar.SideMenu;
import sidebar.SideMenuController;

import static cards.CardFactory.createCard;
import static splash.Controller.getData;

public class Controller {
	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;
//	static boolean cardSelected = true;

	@FXML
	BorderPane mainPane;
	@FXML
	//static
	FlowPane centerPane;
	@FXML
	ScrollPane scrollPane;

	@FXML
	BorderPane sideMenuContainer;

	SideMenu sideMenu;
	SideMenuController sideMenuController;

	@FXML
	public void initialize() {
        
		draggingTab = new SimpleObjectProperty<>();

		scrollPane.setContent(centerPane);

		// create a sidebar with some content in it.
		int sideMenuWidth = 250;
		sideMenuController = new SideMenuController();
		final Pane menuPane = sideMenuController.createSidebarItems();
		menuPane.setId("menuPane");
		sideMenu = new SideMenu(sideMenuWidth, menuPane);
		VBox.setVgrow(menuPane, Priority.ALWAYS);

		Attribute.getAtts().keySet().forEach(e->sideMenuController.addShowTickBox(e));

		sideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane, getData());

		// layout the scene.
		StackPane buttonLocation = new StackPane();
		buttonLocation.getChildren().addAll(sideMenu.getDisplayMenuButton());
		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

		sideMenuContainer.setRight(sideMenu);
		sideMenuContainer.setCenter(buttonLocation);
		sideMenuContainer.setMinWidth(50);

		mainPane.setRight(sideMenuContainer);
		mainPane.setCenter(scrollPane);
		sideMenuContainer.setBorder(new Border( new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));


		makeDecks();



	}

	private void makeDecks(){
		centerPane.getChildren().clear();
		getData().forEach(e -> {
			Deck cards = new Deck(draggingTab, e);

			e.stream().filter(Item::isSelected).forEach(it -> cards.getTabs().add(newCard(e,it, getData().getType())));
			cards.getTabs().forEach(it -> ((Card)it).setListener(this));

			centerPane.getChildren().add(cards);
		});

	}

	@FXML public void newGreenDeck() {
		incNumDecks();
		getData().last().setColour("green");
		Deck newDeck = new Deck(draggingTab, getData().last());
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newYellowDeck() {
		incNumDecks();
		getData().last().setColour("yellow");
		Deck newDeck = new Deck(draggingTab, getData().last());
		centerPane.getChildren().add(newDeck);
	}
	
	@FXML public void newRedDeck() {
		incNumDecks();
		getData().last().setColour("red");
		Deck newDeck = new Deck(draggingTab, getData().last());
		centerPane.getChildren().add(newDeck);
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
		return getData().size();
	}

	private void incNumDecks(){
		getData().add(new Group("Deck "+getData().size()));
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