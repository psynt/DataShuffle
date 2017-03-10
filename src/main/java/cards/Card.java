package cards;

import content.Item;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends Tab {

	private String name;
	private VBox layoutManager;
    private ArrayList<Label> labels = new ArrayList<>();
    final Label label = new Label();
    final TextField tabTitle = new TextField();

    public Card(Item i){
    	
        layoutManager = new VBox();
        layoutManager.setMinHeight(200);
        layoutManager.setMinWidth(400);
        
        i.entrySet().stream().filter(e -> !e.getKey().matches("(i|I)mage")).forEach(e ->
        		addLabel(e)
        );

//        name = i.get("name");
        label.setText(i.get("name"));
        //setGraphic(new Label(i.get("name")));
        setGraphic(label);


        tabTitle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(tabTitle.getText());
                setGraphic(label);

            }
        });

        ContextMenu rightClickMenu = new ContextMenu();
        Menu setColour = new Menu("Set Colour");
        MenuItem red = new MenuItem("Red");
        MenuItem blue = new MenuItem("Blue");
        MenuItem green = new MenuItem("Green");
        MenuItem orange = new MenuItem("Orange");
        MenuItem yellow = new MenuItem("Yellow");
        setColour.getItems().addAll(red, blue, green, orange, yellow);
        red.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Card.super.setStyle("-fx-background-color: tomato;");

            }
        });
        blue.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Card.super.setStyle("-fx-background-color: lightskyblue;");

            }
        });
        green.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Card.super.setStyle("-fx-background-color: mediumspringgreen;");

            }
        });
        orange.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Card.super.setStyle("-fx-background-color: lightsalmon;");

            }
        });
        yellow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Card.super.setStyle("-fx-background-color: yellow;");

            }
        });
        MenuItem renameCard = new MenuItem("Rename Card");
        renameCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabTitle.setText(label.getText());
                setGraphic(tabTitle);
                tabTitle.selectAll();
                tabTitle.requestFocus();

            }
        });
        tabTitle.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue, Boolean newValue) {
                if (! newValue) {
                    label.setText(tabTitle.getText());
                    setGraphic(label);
                }
            }
        });


        rightClickMenu.getItems().addAll(setColour,renameCard);

        super.setContextMenu(rightClickMenu);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));


        pause.setOnFinished(e -> {

            Node tab = super.getTabPane();

            rightClickMenu.show(tab, Side.RIGHT, 0, 0);
        });



        layoutManager.getChildren().addAll(labels);
        setContent(layoutManager);
        setClosable(true);

    }
    
    private void addLabel(Map.Entry<String,String> e) {
        labels.add(new Label(e.getKey() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n){
    	layoutManager.getChildren().add(n);
    }

    public String getName() {
        return name;
    }
}
