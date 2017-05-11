package cards;

import static cards.CardFactory.createCard;
import static splash.Controller.getData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import content.Attribute;
import content.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
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
import model.Group;
import sidebar.SideMenu;
import sidebar.SideMenuController;

public class Controller {
	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;

	@FXML
	BorderPane mainPane;
	@FXML
	FlowPane centerPane;
	@FXML
	ScrollPane scrollPane;

	@FXML
	BorderPane sideMenuContainer;

	private SideMenuController sideMenuController;

	@FXML
	public void initialize() throws IOException {
        
		draggingTab = new SimpleObjectProperty<>();

		scrollPane.setContent(centerPane);

		// create a sidebar with some content in it.
		int sideMenuWidth = 250;
		sideMenuController = new SideMenuController();
		final Pane menuPane = sideMenuController.createSidebarItems();
		menuPane.setId("menuPane");
		SideMenu sideMenu = new SideMenu(sideMenuWidth, menuPane);
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


		makeDecks();

	}

	/**
	 * (re)makes the decks, according to the groups that the cards belong to, and according to which exact items are selected and which aren't
	 */
	private void makeDecks(){
		centerPane.getChildren().clear();
		getData().stream().filter(Group::isSelected).forEach(e -> {
			Deck cards = new Deck(draggingTab, e);

			e.stream().filter(Item::isSelected).forEach(it -> cards.getTabs().add(newCard(e,it, getData().getType())));
			cards.getTabs().forEach(it -> ((Card)it).setListener(this));

			centerPane.getChildren().add(cards);
		});

	}

	/**
	 * Wraps up the creation of a card, adding the sidemenu observable, as well as the dragon drop functionality
	 * @param g group that the new card is part of
	 * @param item Item that represents the model of the new card
	 * @param type the type of card that needs to be returned
	 * @return a card of the appropriate type
	 */
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


	/**
	 * adds a new deck with the same CSS class (colour) as the node that fired this event
	 * !!!! update this if the order of style classes on the "new deck" buttons changes
	 * @param actionEvent event that was fired
	 */
	@FXML public void newDeck(ActionEvent actionEvent){
		String colour = ((Styleable)(actionEvent.getSource())).getStyleClass().get(1);
		getData().add(new Group("Deck "+getData().size(),colour));
		Deck newDeck = new Deck(draggingTab, getData().last());
		centerPane.getChildren().add(newDeck);
	}


}