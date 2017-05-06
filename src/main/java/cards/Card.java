package cards;

import content.Attribute;
import content.Group;
import content.Item;
import groovy.util.MapEntry;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sidebar.SideMenuController;
import sidebar.SideMenuItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private int cardState;
	private int YES = 0;
	private int NO = 1;
	private int MAYBE = 2;
	boolean mouseSelected = false;
	private VBox layoutManager;
//	private ArrayList<String> keys = new ArrayList<>();
//	private ArrayList<Label> labels = new ArrayList<>();
	private HashMap <String,Label> labels = new HashMap<>();
	final Label label = new Label();
	final TextField tabTitle = new TextField();
	
	private SideMenuItems subject;

	public Card(Group g, Item i, SideMenuItems subjectSidebar, String name) {
		parent = g;
		item = i;
		Label cardName = new Label(parent.getName());

		layoutManager = new VBox();
		layoutManager.setMinHeight(50);
		layoutManager.setMinWidth(120);

		i.entrySet().stream().filter(e -> !e.getKey().matches("(i|I)mage")).forEach(this::addLabel);

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


		Menu addCardMenu = new Menu("Add Card to..");
		MenuItem yesCard = new MenuItem("Yes");
		MenuItem noCard = new MenuItem("No");
//		MenuItem maybeCard = new MenuItem("Maybe");
		addCardMenu.getItems().addAll(yesCard, noCard);//, maybeCard);
		yesCard.setOnAction(e -> cardState=YES);
		noCard.setOnAction(e -> cardState=NO);
//		maybeCard.setOnAction(e -> cardState=MAYBE);
		
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
		
		//add sidebar subject
		this.subject = subjectSidebar;
		this.subject.attach(this);
	}
	

	private void addLabel(Map.Entry<String, String> e) {
		System.err.println("add [" + e.getKey() + ":" + e.getValue() + "]");
		labels.put(e.getKey(),new Label(e.getKey() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n) {
		layoutManager.getChildren().add(n);
	}

	@Override
	public void update() {
		//handle show item checkboxes


		Attribute.getAtts().entrySet().forEach(e -> labels.get(e.getKey()).setVisible(Attribute.isSel(e.getKey(),0)));



//		ObservableList<Node> checkBoxes = subject.getShowItemsCheckBoxLayout().getChildren();
//		labels.keySet().stream().//map(k->Attribute.isSel(k,0)).forEach(k -> labels.get(k).setVisible());
//		for( it:checkBoxes) {
//			if (it instanceof CheckBox){
//				boolean selected = ((CheckBox)it).isSelected();
//				System.out.println("card updating " + selected );
//				try {
//					labels.get(((CheckBox) it).getText()).setVisible(selected);
//				}catch (Throwable t) {
//					System.err.println(((CheckBox) it).getText());
//				}
//				it.setVisible(selected);
//			}
//
//		}
//		for(int i = 0; i < checkBoxes.size(); i++){
//			if (checkBoxes.get(i) instanceof CheckBox){
//				boolean selected = ((CheckBox)checkBoxes.get(i)).isSelected();
//				System.out.println("card updating " + selected );
//				labels.get(i).setVisible(selected);
//			}
//		}
		
		//handle add to buttons
//		if(SideMenuController.addToGroup == YES && mouseSelected){
//			cardState=YES;
//		}
//		if(SideMenuController.addToGroup == NO && mouseSelected){
//			cardState=NO;
//		}
//		if(SideMenuController.addToGroup == YES && mouseSelected){
//			cardState=MAYBE;
//		}
	}

	/*public String getName() {
		return name;
	}*/

	public void move(Group newg){
		parent.remove(item);
		newg.add(item);
		parent = newg;
	}

	public ArrayList<String> getKeys(){
		return item.keys();
	}
	
}
