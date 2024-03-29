package cards;

import content.Item;
import model.Group;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Deck extends TabPane implements Serializable {

	private static final long serialVersionUID = -990337246895626078L;
	private static final String TAB_DRAG_KEY = "tab";

	private Group gr;

	/**
	 * Decks represent the view element of a group
	 * @param draggingTab used for dragon drop
	 * @param g group that is the model of this deck
	 */
	public Deck(ObjectProperty<Tab> draggingTab, Group g){
		System.out.println(super.isResizable());
		gr = g;

		setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();


			}
		});

		setOnDragDropped(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				final Tab tab = draggingTab.get();
				tab.getTabPane().getTabs().remove(tab);
				getTabs().add(tab);
				getSelectionModel().select(tab);
				event.setDropCompleted(true);
				draggingTab.set(null);
				event.consume();
				requestLayout();
				((Card) tab).move(gr);
			}
		});

		getStyleClass().add(gr.getColour());


		setMinSize(1,1);

		setPrefHeight(300);
		setPrefWidth(300);



		setTabMinWidth(50);
		setTabMaxWidth(75);

		ContextMenu rightClickMenu = new ContextMenu();
		Menu editDeckCard = new Menu("Size");
		MenuItem incSize = new MenuItem("Increase Size");
		MenuItem decSize = new MenuItem("Decrease Size");
		MenuItem small = new MenuItem("Small");
		MenuItem medium = new MenuItem("Medium");
		MenuItem big = new MenuItem("Big");
		editDeckCard.getItems().addAll(incSize, decSize,small,medium,big);

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
		small.setOnAction(event -> {
            this.setPrefHeight(300);
            this.setPrefWidth(300);
        });
		medium.setOnAction(event -> {
            this.setPrefHeight(450);
            this.setPrefWidth(450);
        });
		big.setOnAction(event -> {
            this.setPrefHeight(600);
            this.setPrefWidth(600);
        });



		deleteDeck.setOnAction(event ->
			{
				gr.forEach(Item::unSelect);
				Card se = (Card) this.getSelectionModel().getSelectedItem();
				((Pane)this.getParent()).getChildren().remove(this);
				se.deleteCard(event);
			}

		);
		deleteCard.setOnAction(event -> {
			Card se = (Card) this.getSelectionModel().getSelectedItem();

			se.deleteCard(event);
			getTabs().remove(se);
			if (this.getTabs().size() == 0)
				((Pane)this.getParent()).getChildren().remove(this);
		});


		super.setContextMenu(rightClickMenu);

		setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

	}

}
