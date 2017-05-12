package splash;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	 public void display(String title , String message){
		    Stage window = new Stage();
		    window.setTitle("title");
		 
		    window.initModality(Modality.APPLICATION_MODAL);
		    window.setMinWidth(300);
		    window.setMinHeight(150);

		    Button button = new Button("Close the window");
		    button.setOnAction(e -> window.close());

		    Label label = new Label(message);

		    VBox layout = new VBox(10);
		    layout.getChildren().addAll(label , button);
		    layout.setAlignment(Pos.CENTER);

		    Scene scene = new Scene(layout);
		    window.setScene(scene);
		  
		    window.showAndWait();
		    }
}
