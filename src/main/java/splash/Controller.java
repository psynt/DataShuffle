package splash;

import content.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import webscraper.DocumentLoader;
import webscraper.EbayItemScraper;
import webscraper.EbayResultScraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Controller {

	@FXML AnchorPane pane;
	


	@FXML public void clickEvent() {
		Stage window = new Stage();
	    window.setTitle("Search");
	 
	    window.initModality(Modality.APPLICATION_MODAL);
	    window.setMinWidth(300);
	    window.setMinHeight(150);
	    
	   

	    
	    TextField userTextField = new TextField();  
	    
	    userTextField.setOnAction(e -> {


	    	ArrayList<Item>	searchResults = new ArrayList<>();

			try {
				searchResults = scrape(userTextField.getText());
				System.out.println(searchResults);
			} catch (MalformedURLException f) {
				f.printStackTrace();
			}



	        });
	    Button button = new Button("Search");
	    button.setOnAction(e -> window.close());
	    
	    
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
	    
	    Slider slider = new Slider();
	    slider.setMin(0);
	    slider.setMax(100);
	    slider.setValue(40);
	    slider.setShowTickLabels(true);
	    slider.setShowTickMarks(true);
	    slider.setMajorTickUnit(50);
	    slider.setMinorTickCount(5);
	    slider.setBlockIncrement(10);
	    GridPane.setConstraints(slider,0,6);
	    grid.getChildren().add(slider);
	    
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

	public ArrayList<Item> scrape(String URL) throws MalformedURLException {
		String searchURL = "http://www.ebay.co.uk/sch/i.html?_&_nkw=datashuffle&_sacat=0".replace("datashuffle", URL);




		ArrayList<Item> whatYouWant = new ArrayList<>();
		Document guitarSearch = DocumentLoader.load(new URL(
				searchURL));
		EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
		ArrayList<String> links = thing1.scrapeLinks();

		for (String link : links) {
			Document res = DocumentLoader.load(new URL(link));
			EbayItemScraper guitar = new EbayItemScraper(res);

			whatYouWant.add(guitar.scrapeDocument());

		}
		return whatYouWant;
	}

}
