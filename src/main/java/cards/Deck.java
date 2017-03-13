package cards;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Deck extends TabPane {

	private static final String TAB_DRAG_KEY = "tab";

	public Deck(ObjectProperty<Tab> draggingTab) {

		setOnDragOver( event -> {
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
				//this was breaking it so I just removed it for now
				//&& draggingTab.get().getTabPane() != tabPane)
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			}
		});

		setOnDragDropped(event -> {
            final Dragboard dragboard = event.getDragboard();
            if (dragboard.hasString() && TAB_DRAG_KEY.equals(dragboard.getString()) && draggingTab.get() != null) {
                //this was breaking it so I just removed it for now
                //&& draggingTab.get().getTabPane() != tabPane)
                final Tab tab = draggingTab.get();
                tab.getTabPane().getTabs().remove(tab);
                getTabs().add(tab);
                getSelectionModel().select(tab);
                event.setDropCompleted(true);
                draggingTab.set(null);
                event.consume();
            }
        });

		setMinWidth(50);
		setMaxWidth(480);
		setTabMinWidth(50);
		setTabMaxWidth(100);
		setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)))
		);

	}
}
