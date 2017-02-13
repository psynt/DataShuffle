package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			 // Button button = new Button();
		       //button.setText("Open a window");
		       //button.setOnAction(e -> new AlertBox().display("title", "message"));

		     AnchorPane layout = new AnchorPane();
		      // layout.getChildren().add(button);

			Scene scene = new Scene(layout,800,600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Parent content = FXMLLoader.load(getClass().getResource("Mymenu.fxml"));
			
			layout.getChildren().add(content);
			
			primaryStage.setTitle("Menu");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
