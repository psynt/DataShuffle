
package splash;

import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import content.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;


import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

public class Controller {

	@FXML AnchorPane pane;

	static ArrayList<Item>	searchResults = new ArrayList<>();

	@FXML public void clickEvent() {
		Stage window = new Stage();
	    window.setTitle("Search");
	 
	    window.initModality(Modality.APPLICATION_MODAL);
	    window.setMinWidth(300);
	    window.setMinHeight(150);
	    
	   

	    
	    TextField userTextField = new TextField();  
	    
	    userTextField.setOnAction(e -> {
	        // add your code to be run here
	       
	        ArrayList<Item> whatYouWant = new ArrayList<>();
			Document guitarSearch = null;
			try {
				guitarSearch = DocumentLoader.load(new URL(
						"http://www.ebay.co.uk/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xguitar.TRS0&_nkw=guitar&_sacat=0"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
			ArrayList<String> links = thing1.scrapeLinks();

			for (String link : links) {
				Document res = null;
				try {
					res = DocumentLoader.load(new URL(link));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EbayItemScraper guitar = new EbayItemScraper(res);
				whatYouWant.add(guitar.scrapeDocument());
			}
			System.out.println("URL");
			System.out.println(whatYouWant);
	        });
	    //Button button = new Button("Search");
	    //button.setOnAction(e -> window.close());
	    
	    
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25,25,25,25));
	    Text scenetitle = new Text("Welcome");  
	     
	    scenetitle.setId("welcome-text");  
	    grid.add(scenetitle, 0, 0, 2, 1);
	    userTextField.setId("userTextField");
	    //grid.getChildren().add(button);
	    
	    grid.add(userTextField,0,3);
	    Label label1 = new Label("Min:");
	    Label label2 = new Label("Max:");
	    TextField minTextField = new TextField();
	    TextField maxTextField = new TextField();
	    minTextField.setPrefWidth(55.0);
	    maxTextField.setPrefWidth(55.0);
	    HBox hb = new HBox();
	    hb.getChildren().addAll(label1, minTextField);
	    hb.getChildren().addAll(label2, maxTextField);
	    hb.setSpacing(8);
	    
	   // GridPane.setConstraints(mTextField,0,6);
	    //mTextField.setId("minTextField");
	    grid.add(hb,0,4,1,1);
	    
	    final ChoiceBox cb = new ChoiceBox();
	    cb.setItems(FXCollections.observableArrayList(
	    	    "All Listing", "Austion ", 
	    	    new Separator(), "Buy It Now!")
	    	);
	    GridPane.setConstraints(cb,0,8);
	    grid.getChildren().add(cb);
	    
	   // Button btn = new Button();
	    //grid.getChildren().add(btn);
	    
	    
	    Scene scene = new Scene(grid);
	    window.setScene(scene);
	    scene.getStylesheets().add(Controller.class.getResource("/application.css").toExternalForm());
	    window.showAndWait();
	}

}

