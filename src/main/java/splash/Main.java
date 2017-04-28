package splash;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene scene;

	public static Scene getScene(){
		return scene;
	}

	@FXML
	Button ebayButton;
	@FXML
	Button moduleButton;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane layout = new AnchorPane();

			scene = new Scene(layout, 400, 600);



			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Parent content = FXMLLoader.load(getClass().getResource("/Mymenu.fxml"));


			layout.getChildren().add(content);

			primaryStage.setTitle("Data Shuffle");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
