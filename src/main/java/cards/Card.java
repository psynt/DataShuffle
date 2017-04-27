package cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import content.Item;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sidebar.SideMenuController;
import sidebar.SideMenuItems;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends Tab implements Observer, Serializable{
	private static final long serialVersionUID = -630866289349768478L;
	int cardState;
	int YES = 0;
	int NO = 1;
	int MAYBE = 2;
	boolean mouseSelected = false;
	private VBox layoutManager;
	private ArrayList<String> keys = new ArrayList<>();
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


		Menu addCardMenu = new Menu("Add Card to..");
		MenuItem yesCard = new MenuItem("Yes");
		MenuItem noCard = new MenuItem("No");
		MenuItem maybeCard = new MenuItem("Maybe");
		addCardMenu.getItems().addAll(yesCard, noCard, maybeCard);
		yesCard.setOnAction(e -> cardState=YES);
		noCard.setOnAction(e -> cardState=NO);
		maybeCard.setOnAction(e -> cardState=MAYBE);
		
		//method to detect if card is left clicked
		layoutManager.setOnMouseClicked(e ->{
			mouseSelected=true;
			if(mouseSelected==true){
				System.out.print("Save as test");
			}
			});
		
			//method to detect if mouse has been clicked outside card
			layoutManager.setOnMouseExited(e ->{
				Controller.cardUnselect();
				if(Controller.cardSelected ==false){
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
		layoutManager.getChildren().addAll(labels);
		setContent(layoutManager);
		setClosable(true);
		
		//add sidebar subject
		this.subject = subjectSidebar;
		this.subject.attach(this);
	}
	

	private void addLabel(Map.Entry<String, String> e) {
		keys.add(e.getKey());
		labels.add(new Label(e.getKey() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n) {
		layoutManager.getChildren().add(n);
	}

	@Override
	public void update() {
		//handle show item checkboxes
		
		ObservableList<Node> checkBoxes = subject.getShowItemsCheckBoxLayout().getChildren();
		for(int i = 0; i < checkBoxes.size(); i++){
			if (checkBoxes.get(i) instanceof CheckBox){
				boolean selected = ((CheckBox)checkBoxes.get(i)).isSelected();
				System.out.println("card updating " + selected );
				labels.get(i).setVisible(selected);
			}
		}		
		
		//handle add to buttons
		if(SideMenuController.addToGroup == YES && mouseSelected ==true){
			cardState=YES;
		}
		if(SideMenuController.addToGroup == NO && mouseSelected ==true){
			cardState=NO;
		}
		if(SideMenuController.addToGroup == YES && mouseSelected ==true){
			cardState=MAYBE;
		}
	}

	/*public String getName() {
		return name;
	}*/

	public ArrayList<String> getKeys(){
		return keys;
	}
	
}
