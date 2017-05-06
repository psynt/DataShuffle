package cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Data;

public class Main extends Application {

	public static String title ="New";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("/MainSurface.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    public static void start1(Stage primaryStage) throws Exception{
        primaryStage.hide();
        BorderPane root = FXMLLoader.load(Main.class.getResource("/MainSurface.fxml"));
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/titleIcon.png")));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}