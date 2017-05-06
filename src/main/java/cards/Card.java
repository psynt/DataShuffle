package cards;

import content.Attribute;
import model.Group;
import content.Item;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sidebar.SideMenuItems;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends Tab implements Observer, Serializable{
	private static final long serialVersionUID = -630866289349768478L;
	private Item item;
	private Group parent;
	boolean mouseSelected = false;
	private VBox layoutManager;
	private HashMap <String,Label> labels = new HashMap<>();
	final Label label = new Label();
	final TextField tabTitle = new TextField();
	Controller ob;

	public Card(Group g, Item i, SideMenuItems subjectSidebar, String name) {
		parent = g;
		item = i;
		Label cardName = new Label(parent.getName());

		layoutManager = new VBox();
		layoutManager.setMinHeight(50);
		layoutManager.setMinWidth(120);

		Attribute.getAtts().entrySet().forEach(it -> addLabel(it.getKey(),i.get(it.getKey())));

		label.setText(name);
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


		Menu addCardMenu = new Menu("Delete");
		MenuItem noCard = new MenuItem("Remove Card");
		addCardMenu.getItems().addAll(noCard);
		noCard.setOnAction(e -> {
//			item.unSelect();
//			ob.notifyObserver();
			deleteCard(e);
		});
		
		//method to detect if card is left clicked
		layoutManager.setOnMouseClicked(e ->{
			mouseSelected=true;
			System.out.print("Save as test");
		});
		
		//method to detect if mouse has been clicked outside card
		layoutManager.setOnMouseExited(e ->{
			Controller.cardUnselect();
			if(!Controller.cardSelected){
				mouseSelected=false;
				System.out.print("Sst");
			}
		});


		rightClickMenu.getItems().addAll(setColour, renameCard, addCardMenu);

		super.setContextMenu(rightClickMenu);
		PauseTransition pause = new PauseTransition(Duration.seconds(2));

		pause.setOnFinished(e -> {

			Node tab = super.getTabPane();

			rightClickMenu.show(tab, Side.RIGHT, 0, 0);
		});
		layoutManager.getChildren().add(cardName);
		layoutManager.getChildren().addAll(labels.values());
		setContent(layoutManager);
		setClosable(true);

		setOnCloseRequest(e -> deleteCard(e));
		
		//add sidebar subject
		subjectSidebar.attach(this);
	}

	public void deleteCard(Event e){
		item.unSelect();
		ob.notifyObserver();

	}

	public void setListener(Controller c){
		ob = c;
	}


	private void addLabel(String key, String val) {
//		System.err.println("add [" + e.getKey() + ":" + e.getValue() + "]");
		labels.put(key,new Label(key + "\t:\t" + val));
	}

	public void addComponent(Node n) {
		layoutManager.getChildren().add(n);
	}

	@Override
	public void update() {

		Attribute.getAtts().forEach((key, value) -> {
			try {
				labels.get(key).setVisible(Attribute.isSel(key, 0));
			} catch (NullPointerException ex) {
				System.out.println(key + " " + labels.get(key));
			}
		});

	}

	public void move(Group newg){
		parent.remove(item);
		newg.add(item);
		parent = newg;
	}

}
