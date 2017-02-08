package main.java.cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ebayCard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        FXMLLoader loader = new FXMLLoader(getClass().getResource("ebayCard.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 250,350);

        ebayController controller = loader.<ebayController>getController();


        controller.setCardProperties("Acoustic Guitar", "£577", "Good as New", "5 days", "£25");

        primaryStage.setTitle("Hello World");

       // primaryStage.setScene(new Scene(root, 250, 350));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
