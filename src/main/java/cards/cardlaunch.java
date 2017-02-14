package cards;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by edwardbutcher on 2/14/17.
 */
public class cardlaunch extends Application{


    public static void main(String[] args) {
    launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Card");

        BorderPane guitar = new Card("Acoustic Guitar", "£577", "Good as New", "5 days", "£25");




        root.getChildren().add(guitar);


        primaryStage.setScene(scene);



        primaryStage.show();


    }


    }

