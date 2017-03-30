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
import javafx.scene.layout.HBox;
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

	@FXML
	AnchorPane pane;

	static ArrayList<Item> searchResults = new ArrayList<>();

	@FXML
	public void clickEvent() {
		Stage window = new Stage();
		window.setTitle("Search");

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(300);
		window.setMinHeight(150);

		TextField userTextField = new TextField();
		userTextField.setId("userTextField");
		userTextField.setOnAction(e -> {

			search(window, userTextField);

		});

		//Set up grid layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		
		Text scenetitle = new Text("Enter your search terms");
		scenetitle.setId("welcome-text");
		grid.add(scenetitle, 0, 0);

		grid.add(userTextField, 0, 1);

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
		grid.add(hb, 0, 2);

		final ChoiceBox cb = new ChoiceBox();
		cb.setItems(FXCollections.observableArrayList("All Listings", "Auction ", new Separator(), "Buy It Now!"));
		grid.add(cb, 1, 1);

		Button searchButton = new Button("Search");
		grid.add(searchButton, 0, 3);
		searchButton.setOnAction(e -> {
			search(window, userTextField);
		});

		Button closeButton = new Button("Close");
		grid.add(closeButton, 1, 3);
		closeButton.setOnAction(e -> {
			window.close();
		});

		Scene scene = new Scene(grid);
		window.setScene(scene);
		scene.getStylesheets().add(Controller.class.getResource("/application.css").toExternalForm());
		window.showAndWait();
	}

	private void search(Stage window, TextField userTextField) {
		try {
			System.err.println("Starting");
			searchResults = scrape(userTextField.getText());
			System.out.println(searchResults);
			cards.Main.start1(window);
		} catch (Exception f) {
			f.printStackTrace();
		}
	}

	public ArrayList<Item> scrape(String URL) throws MalformedURLException {
		String searchURL = "http://www.ebay.co.uk/sch/i.html?_&_nkw=datashuffle&_sacat=0".replace("datashuffle", URL);

		ArrayList<Item> whatYouWant = new ArrayList<>();
		Document guitarSearch = DocumentLoader.load(new URL(searchURL));
		EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
		ArrayList<String> links = thing1.scrapeLinks();

		for (String link : links) {
			Document res = DocumentLoader.load(new URL(link));
			EbayItemScraper guitar = new EbayItemScraper(res);

			whatYouWant.add(guitar.scrapeDocument());

		}
		return whatYouWant;
	}

	public static ArrayList<Item> getSearchResults() {
		return searchResults;
	}

}
