package splash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private static Scene scene;

	public static Scene getScene(){
		return scene;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane layout = new AnchorPane();

			scene = new Scene(layout, 400, 600);

			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Parent content = FXMLLoader.load(getClass().getResource("/Mymenu.fxml"));

			layout.getChildren().add(content);

			primaryStage.setTitle("Menu");
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
