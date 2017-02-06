package main.java.cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ebayCard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ebayCard.fxml"));

        primaryStage.setTitle("Hello World");

        primaryStage.setScene(new Scene(root, 250, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
