package cards;

import content.Attribute;
import javafx.event.ActionEvent;
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
import javafx.application.HostServices;

import javax.inject.Inject;
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
	private VBox layoutManager;
	private HashMap <String,Labeled> labels = new HashMap<>();
	private final Label label = new Label();
	private final TextField tabTitle = new TextField();
	private Controller ob;

	@Inject
    private HostServices hostServices ;

	public Card(Group g, Item i, SideMenuItems subjectSidebar, String name) {
		parent = g;
		item = i;
		Label cardName = new Label(parent.getName());

		layoutManager = new VBox();
		layoutManager.setMinHeight(50);
		layoutManager.setMinWidth(120);


		for (String it:Attribute.getAtts().keySet()) {
			if(it.matches("([Ii])mage")) continue;
			if(it.matches("([Ll])ink")){
//				Hyperlink l = new Hyperlink(i.get(it));
				Hyperlink hl = new Hyperlink("Link");
				hl.setTooltip(new Tooltip(i.get(it)));
				hl.setOnAction((ActionEvent event) -> {
//					Hyperlink h = (Hyperlink) event.getTarget();
//					String s = h.getTooltip().getText();
//					System.out.println(s);
//					System.out.println(hostServices.toString());
					HostServices hs = (HostServices)(((Hyperlink)(event.getSource())).getScene().getWindow().getProperties().get("hostServices"));
					hs.showDocument(i.get(it));
					event.consume();
				});

				labels.put(it,hl);
				continue;
			}
			addLabel(it,i.get(it));

		}


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
		noCard.setOnAction(this::deleteCard);
		
		//method to detect if card is left clicked
		layoutManager.setOnMouseClicked(e ->{
			System.out.print("Save as test");
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

		setOnCloseRequest(this::deleteCard);
		
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
		labels.put(key,new Label(key + "\t:\t" + val));
	}

//	private void addLabeled(String key, Labeled l) {
//		labels.put(key,l);
//	}

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
