package cards;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Deck extends TabPane {

	private static final String TAB_DRAG_KEY = "tab";

	public Deck(ObjectProperty<Tab> draggingTab, int colour, int num) {

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

		setMinWidth(120);
		setMaxWidth(480);
		setMaxHeight(480);
		setMinHeight(150);

		setTabMinWidth(50);
		setTabMaxWidth(75);

		ContextMenu rightClickMenu = new ContextMenu();
		Menu resizeCard = new Menu("Resize Deck");
		MenuItem incSize = new MenuItem("Increase Size");
		MenuItem decSize = new MenuItem("Decrease Size");
		resizeCard.getItems().addAll(incSize, decSize);

		Menu deleteMenu = new Menu();
		MenuItem deleteDeck = new MenuItem("Delete Deck");
		MenuItem deleteCard = new MenuItem("Delete Card");
		deleteMenu.getItems().addAll(deleteDeck, deleteCard);
		rightClickMenu.getItems().addAll(resizeCard, deleteMenu);


		incSize.setOnAction(event -> {
            setPrefHeight(getHeight() + 30);
            setPrefWidth(getWidth() + 60);
        });
		decSize.setOnAction(event -> {
            setPrefHeight(getHeight() - 30);

            setPrefWidth(getWidth() - 60);
        });
		deleteDeck.setOnAction(event ->
				{
					getTabs().clear();

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
}
