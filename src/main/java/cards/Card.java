package main.java.cards;/**
 * Created by edwardbutcher on 2/5/17.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Scene;

public class Card extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Card");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.WHITE);


        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();



        Tab tab = new Tab();
        tab.setText("Tab");

        VBox tabBox = new VBox();

        tabBox.setAlignment(Pos.TOP_CENTER);
        tabBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        Image image = new Image("guitar.jpg");
        ImageView guitar = new ImageView();
        guitar.setImage(image);


        tabBox.getChildren().addAll(
                new Label("Name: Hot and sexy Guitar"),

                guitar,
                new Label("Price: £155.00"),
                new Label("Good Condition"),
                new Label("Remaining time: 10 days"),
                new Label("8 bids")



        );


        tab.setContent(tabBox);
        tabPane.getTabs().add(tab);


        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());






        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
