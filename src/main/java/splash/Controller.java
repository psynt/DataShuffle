package splash;

import content.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import webscraper.*;
import webscraper.clever.CoursePOSTReq;
import webscraper.clever.ModulePOSTReq;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Controller {

	@FXML
	AnchorPane pane;

	private static ArrayList<Item> searchResults = new ArrayList<>();
	private static String Type;

	@FXML
	public void clickEvent(ActionEvent actionEvent) {
		Button source = (Button)(actionEvent.getSource());
		String type = source.getText();
		System.err.println(type);
		Stage window = new Stage();
		window.setTitle(type + " Search");

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(300);
		window.setMinHeight(150);

		TextField userTextField = new TextField();
		userTextField.setId("userTextField");
		userTextField.setOnAction(e -> {
//			try {
				search(window, userTextField,type);
//			} catch (NullPointerException ex){
//				System.out.println("null text field -.-");
//			}

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
		Label label1 = new Label();
		Label label2 = new Label();
		TextField minTextField = new TextField();
		TextField maxTextField = new TextField();
		final ChoiceBox<Object> cb = new ChoiceBox();

		HBox hb = new HBox();
		if(type.equals("Ebay")) {
			label1.setText("Min:");
			label2.setText("Max:");
			minTextField.setPrefWidth(55.0);
			maxTextField.setPrefWidth(55.0);


			hb.getChildren().addAll(label1, minTextField);
			hb.getChildren().addAll(label2, maxTextField);


			cb.setItems(FXCollections.observableArrayList("All Listings", "Auction ", new Separator(), "Buy It Now!"));
			cb.getSelectionModel().selectFirst();

			grid.add(hb, 0, 2);
			grid.add(cb, 1, 1);
		}else{
			scenetitle.setText("Search by module code:");
			//IF Module is selected
			label1.setText("Or use a Course Code:");
			minTextField.setPrefWidth(110.0);



			//hb.getChildren().addAll(label1, minTextField);

			grid.add(hb, 0, 2);
		}

	 	hb.setSpacing(8);

		Button searchButton = new Button("Search");
		grid.add(searchButton, 0, 3);
		searchButton.setOnAction(e -> {
//			try {
				search(window, userTextField,type);
//			} catch (Exception ex){
//				System.out.println("null text field -.-");
//			}
		});

		Button closeButton = new Button("Close");
		grid.add(closeButton, 1, 3);
		closeButton.setOnAction(e -> {
			window.close();
		});

		Scene scene = new Scene(grid);
		window.setScene(scene);
		scene.getStylesheets().add(Controller.class.getResource("/application.css").toExternalForm());
		try{
			window.showAndWait();
		}catch (Throwable t){
			System.out.println("Caught");
		}
	}

	private void search(Stage window, TextField userTextField, String type) {
		try {
			System.err.println("Starting " + userTextField.getText());
			switch (type) {
				case "Ebay":
					searchResults = ebay(userTextField.getText());
					break;
				case "Module":
					searchResults = modules(userTextField.getText());
					break;
			}
			System.out.println(searchResults);
			cards.Main.start1(window);
		} catch (NullPointerException ex){
			System.out.println("null text field -.-");
		} catch (UnsupportedOperationException ex){
			System.out.println("Sorry, no can do at the moment. Will be implemented in about 40-50 releases. Hang tight ;)");
		} catch (Exception f) {
			f.printStackTrace();
		}
	}

	private ArrayList<Item> modules(String url) throws MalformedURLException {
		Type = "Module";
		CoursePOSTReq courseGetter = new CoursePOSTReq();

		Map<String,String> res = courseGetter.ucasCode(url);
		if(res.size()>1){
			System.err.println("Searches that return multiple results are not yet supported");
			throw new UnsupportedOperationException("Multi-result search not quite ready yet");
		}

		List<String> urls = res.entrySet().parallelStream().map(e -> e.getValue()).collect(Collectors.toList());

		CourseScraper cs = new CourseScraper(DocumentLoader.load(new URL(urls.get(0))));
		List<String> modules = cs.getReqModules();
		List<Item> results = modules.stream().filter(e -> e.matches("G5\\d...")).map(e -> {
			try {
				return new ModulePOSTReq().courseCode(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}).map(ModuleScraper::new).map(ModuleScraper::scrapeDocument).collect(Collectors.toList());

		return new ArrayList<>(results);
	}

	private ArrayList<Item> ebay(String url) throws MalformedURLException {
		Type = "Ebay";
		String searchUrl = "http://www.ebay.co.uk/sch/i.html?_&_nkw=datashuffle&_sacat=0".replace("datashuffle", url);

		ArrayList<Item> whatYouWant = new ArrayList<>();
		Document guitarSearch = DocumentLoader.load(new URL(searchUrl));
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
	public static String getType() {
		return Type;
	}

}
