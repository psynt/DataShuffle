package splash;

import com.airhacks.afterburner.injection.Injector;
import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/titleIcon.png")));

			layout.getChildren().add(content);

			primaryStage.setTitle("Data Shuffle");
			primaryStage.setScene(scene);
			Injector.setModelOrService(HostServices.class, getHostServices());
			primaryStage.getProperties().put("hostServices", this.getHostServices());
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
