package cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static String title ="New";
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("/MainSurface.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    //@Override
    public static void start1(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(Main.class.getResource("/MainSurface.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        
        primaryStage.show();
        
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}