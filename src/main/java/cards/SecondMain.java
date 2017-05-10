package cards;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * class that serves as the starting point for the main view
 */
public class SecondMain {

	public static String title ="New";

    /**
     * gets the main view of the app going
     * @param primaryStage stage to set the scene on
     * @throws Exception fx start exception behaviour
     */
    public static void start1(Stage primaryStage) throws Exception{
        primaryStage.hide();
        BorderPane root = FXMLLoader.load(SecondMain.class.getResource("/MainSurface.fxml"));
		primaryStage.getIcons().add(new Image(SecondMain.class.getResourceAsStream("/titleIcon.png")));
        primaryStage.setTitle(title);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(SecondMain.class.getResource("/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        
        primaryStage.show();
        
    }

}