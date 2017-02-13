package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

	@FXML AnchorPane pane;

	@FXML public void clickEvent() {
		Stage window = new Stage();
	    window.setTitle("Search");
	 
	    window.initModality(Modality.APPLICATION_MODAL);
	    window.setMinWidth(300);
	    window.setMinHeight(150);
	    
	   

	    
	    TextField userTextField = new TextField();  
	    

	    Button button = new Button("Search");
	    button.setOnAction(e -> window.close());

	   // Label label = new Label();
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25,25,25,25));
	    Text scenetitle = new Text("Welcome");  
	     
	    scenetitle.setId("welcome-text");  
	    grid.add(scenetitle, 0, 0, 2, 1);
	    userTextField.setId("userTextField");
	  

	   // VBox layout = new VBox(10);
	    
	    grid.add(userTextField,0,3);

	    
	    Scene scene = new Scene(grid);
	    window.setScene(scene);
	    scene.getStylesheets().add(Controller.class.getResource("application.css").toExternalForm());  
	    window.showAndWait();
	}

}
