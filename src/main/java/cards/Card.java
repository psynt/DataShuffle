package cards;

import java.util.ArrayList;
import java.util.Map;

import content.Item;
import javafx.animation.PauseTransition;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sidebar.SideMenu;
import sidebar.SideMenuItems;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends Tab implements Observer{

	private VBox layoutManager;
	private ArrayList<Label> labels = new ArrayList<>();
	final Label label = new Label();
	final TextField tabTitle = new TextField();
	
	private SideMenuItems subject;

	public Card(Item i, int numDeck, SideMenuItems subjectSidebar, String name) {
		Label cardName = new Label("Deck " + numDeck);

		layoutManager = new VBox();
		layoutManager.setMinHeight(50);
		layoutManager.setMinWidth(120);

		i.entrySet().stream().filter(e -> !e.getKey().matches("(i|I)mage")).forEach(e -> addLabel(e));

		// name = i.get("name");
		label.setText(name);
		// setGraphic(new Label(i.get("name")));
		setGraphic(label);


		tabTitle.setOnAction(event -> {
            label.setText(tabTitle.getText());
            setGraphic(label);

        });

		ContextMenu rightClickMenu = new ContextMenu();
		Menu setColour = new Menu("Set Colour");
		MenuItem red = new MenuItem("Red");
		MenuItem blue = new MenuItem("Blue");
		MenuItem green = new MenuItem("Green");
		MenuItem orange = new MenuItem("Orange");
		MenuItem yellow = new MenuItem("Yellow");
		setColour.getItems().addAll(red, blue, green, orange, yellow);
		red.setOnAction(e -> Card.super.setStyle("-fx-background-color: tomato;"));
		blue.setOnAction(e -> Card.super.setStyle("-fx-background-color: lightskyblue;"));
		green.setOnAction(e -> Card.super.setStyle("-fx-background-color: mediumspringgreen;"));
		orange.setOnAction(e -> Card.super.setStyle("-fx-background-color: lightsalmon;"));
		yellow.setOnAction(e -> Card.super.setStyle("-fx-background-color: yellow;"));
		MenuItem renameCard = new MenuItem("Rename Card");
		renameCard.setOnAction(event -> {
            tabTitle.setText(label.getText());
            setGraphic(tabTitle);
            tabTitle.selectAll();
            tabTitle.requestFocus();

        });
		tabTitle.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                label.setText(tabTitle.getText());
                setGraphic(label);
            }
        });



		rightClickMenu.getItems().addAll(setColour, renameCard);

		super.setContextMenu(rightClickMenu);
		PauseTransition pause = new PauseTransition(Duration.seconds(2));

		pause.setOnFinished(e -> {

			Node tab = super.getTabPane();

			rightClickMenu.show(tab, Side.RIGHT, 0, 0);
		});
		layoutManager.getChildren().add(cardName);
		layoutManager.getChildren().addAll(labels);
		setContent(layoutManager);
		setClosable(true);
		
		//add sidebar subject
		this.subject = subjectSidebar;
		this.subject.attach(this);
	}

	private void addLabel(Map.Entry<String, String> e) {
		labels.add(new Label(e.getKey() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n) {
		layoutManager.getChildren().add(n);
	}

	@Override
	public void update() {
		boolean selected = subject.showTitleCheckBox.isSelected();
		labels.get(0).setVisible(selected);
	}

	/*public String getName() {
		return name;
	}*/

}
