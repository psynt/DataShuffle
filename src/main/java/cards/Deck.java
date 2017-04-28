package cards;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck extends TabPane implements Serializable {

	private static final long serialVersionUID = -990337246895626078L;
	private static final String TAB_DRAG_KEY = "tab";
	
	private ArrayList<Card> allCards;

	public Deck(ObjectProperty<Tab> draggingTab, int colour, int num) {
		
		allCards= new ArrayList<Card>();		

		setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				// this was breaking it so I just removed it for now
				// && draggingTab.get().getTabPane() != tabPane)
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();


			}
		});

		setOnDragDropped(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				// this was breaking it so I just removed it for now
				// && draggingTab.get().getTabPane() != tabPane)
				final Tab tab = draggingTab.get();
				tab.getTabPane().getTabs().remove(tab);
				getTabs().add(tab);
				getSelectionModel().select(tab);
				event.setDropCompleted(true);
				draggingTab.set(null);
				event.consume();
				requestLayout();

				//need to find a way to 'REPAINT' the tabs once theyre dragged, as sometimes they become blank
				// i have tried changing the height / then reverting back, as resizing tehe empty decks repaints them, but also doesnt work

			}
		});



		switch (colour) {
		case 0:
			setStyle("-fx-background-color: #b9eeb7;");
			break;
		case 1:
			setStyle("-fx-background-color: #ffe766;");
			break;
		case 2:
			setStyle("-fx-background-color: #d28f8f;");
			break;
		}

		//setMinWidth(120);
		//setMaxWidth(480);
		setPrefHeight(140);
		setPrefWidth(200);

		//setMaxHeight(480);
		//setMinHeight(150);

		setTabMinWidth(50);
		setTabMaxWidth(75);

		ContextMenu rightClickMenu = new ContextMenu();
		Menu editDeckCard = new Menu("Edit");
		MenuItem incSize = new MenuItem("Increase Size");
		MenuItem decSize = new MenuItem("Decrease Size");
		editDeckCard.getItems().addAll(incSize, decSize);

		Menu deleteMenu = new Menu("Delete");
		MenuItem deleteDeck = new MenuItem("Delete Deck");
		MenuItem deleteCard = new MenuItem("Delete Card");
		deleteMenu.getItems().addAll(deleteDeck, deleteCard);
		rightClickMenu.getItems().addAll(editDeckCard, deleteMenu);


		incSize.setOnAction(event -> {
            this.setPrefHeight(getHeight() + 30);
            this.setPrefWidth(getWidth() + 50);
        });
		decSize.setOnAction(event -> {
            this.setPrefHeight(getHeight() - 30);
            this.setPrefWidth(getWidth() - 50);
        });
		deleteDeck.setOnAction(event ->
				{
					getTabs().clear();
					((FlowPane)this.getParent()).getChildren().remove(this);

				}

		);
		deleteCard.setOnAction(event ->
				{
					getTabs().remove(this);
				}
		);


		super.setContextMenu(rightClickMenu);



		setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

	}
	
	public void saveCard(Card newCard){
		allCards.add(newCard);
	}
	
	public ArrayList<Card> getCards(){
		return allCards;
	}
	
	public void readdAllCards(){
		for(int i = 0; i < allCards.size(); i++){
			this.getTabs().add(allCards.get(i));
		}
	}
}
