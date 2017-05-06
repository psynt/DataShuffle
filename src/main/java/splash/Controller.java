package splash;

import cards.CardState;
import cards.Deck;
import content.Attribute;
import content.Group;
import content.Item;
import debug.Debug;
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
import model.Data;
import org.jsoup.nodes.Document;
import webscraper.*;
import webscraper.clever.CoursePOSTReq;
import webscraper.clever.ModulePOSTReq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	@FXML
	Button ebayButton;


	@FXML
	Button moduleButton;

	private static boolean loadFlag = false;

	public static final Data d = new Data();

//	public static Data getData(){
//		return d;
//	}

	@FXML
	public void initialize() {
	}

	@FXML
	public void closeWindow(ActionEvent actionEvent) {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void openFile(ActionEvent actionEvent) {
		// Read from disk using FileInputStream
		FileInputStream f_in = null;
		ObjectInputStream obj_in = null;
		try {
			f_in = new FileInputStream("cards.data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Read object using ObjectInputStream
		try {
			obj_in = new ObjectInputStream(f_in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read an object
		Object obj = null;
		try {
			obj = obj_in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (obj instanceof CardState) {
//			loadResults = ((CardState) obj).getAllDecks();
			try {
				loadFlag = true;
				cards.Main.start1((Stage) pane.getScene().getWindow());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Oops, something went wrong");
		}
	}

	@FXML
	public void ebayEvent(ActionEvent actionEvent){
		d.setType("Ebay");
		clickEvent(actionEvent);

	}
	@FXML
	public void moduleEvent(ActionEvent actionEvent){
		d.setType("Module");
		clickEvent(actionEvent);
	}

	@FXML
	public void clickEvent(ActionEvent actionEvent) {
		Button source = (Button) (actionEvent.getSource());
		String type = source.getText();
		System.err.println(type);
		Stage window = new Stage();
		window.setTitle(type + " Search");
		Button searchButton = new Button("Search");

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(300);
		window.setMinHeight(150);

		TextField userTextField = new TextField();
		userTextField.setId("userTextField");

		// Set up grid layout
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
		final ChoiceBox<Object> cb = new ChoiceBox<>();

		HBox hb = new HBox();
		if (d.getType().equals("Ebay")) {
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
			searchButton.setOnAction(e -> { // ebay
				try {
					d.add(new Group(ebay(userTextField.getText(), minTextField.getText(), maxTextField.getText(), cb.getSelectionModel().getSelectedItem().toString() )));
					System.out.println(d);
					window.close();
				} catch (MalformedURLException ex) {
					System.out.println("Bad url:");
					ex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("null text field kek");
					ex.printStackTrace();
				}
			});

		} else {// IF Module is selected

			scenetitle.setText("Search by module code:");

			label1.setText("Or use a Course Code:");
			minTextField.setPrefWidth(110.0);

			searchButton.setOnAction(e -> {
				try {
					d.add(new Group( modules(userTextField.getText(), minTextField.getText())));
					System.out.println(d);
					window.close();
				} catch (MalformedURLException ex) {
					System.out.println("Bad url:");
					ex.printStackTrace();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println("null text field -.-");
				}
			});

			hb.getChildren().addAll(label1, minTextField);

			grid.add(hb, 0, 2);
		}

		hb.setSpacing(8);

		grid.add(searchButton, 0, 3);

		Button closeButton = new Button("Close");
		grid.add(closeButton, 1, 3);
		closeButton.setOnAction(e -> {
			window.close();
		});

		Scene scene = new Scene(grid);
		window.setScene(scene);
		scene.getStylesheets().add(Controller.class.getResource("/application.css").toExternalForm());
		try {
			window.showAndWait();
			if (!d.isEmpty()) {
				d.last().setColor("red");
				cards.Main.start1((Stage) pane.getScene().getWindow());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("Caught");
		}
	}

	private ArrayList<Item> modules(String keyword, String code) throws MalformedURLException {
		d.setType ("Module");
		CoursePOSTReq courseGetter = new CoursePOSTReq();

		Map<String, String> res;
		if (code != null && code.length() > 0) {
			res = courseGetter.ucasCode(code);
		} else if (keyword != null && keyword.length() > 0) {
			res = courseGetter.keyword(keyword);
		} else
			throw new NullPointerException("either code or keyword needed");

		if (Debug.DEBUG) {
			if (res != null) {
				System.out.println(res);
			}
		}

		if (res.size() > 1) {
			System.err.println("Searches that return multiple results are not yet supported");
			throw new UnsupportedOperationException("Multi-result search not quite ready yet");
		} else if (res.size() < 1) {
			throw new NullPointerException("No results");
		}

		List<String> urls = new ArrayList<>(res.values());

		CourseScraper cs = new CourseScraper(DocumentLoader.load(new URL(urls.get(0))));
		List<String> modules = cs.getReqModules();
		List<Item> results = modules.stream().filter(e -> e.matches("G5\\d...")).map(e -> {
//			if(e.matches("G5\\d..."))
			try {
				return new ModulePOSTReq().courseCode(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}).map(ModuleScraper::new).map(ModuleScraper::scrapeDocument).collect(Collectors.toList());

		return new ArrayList<>(results);
	}

	private ArrayList<Item> ebay(String searchTerm, String min, String max, String auctionType) throws MalformedURLException {
		d.setType("Ebay");
		if(Objects.equals(auctionType, "Buy It Now!")){auctionType = "BIN";}

		//String searchUrl = "http://www.ebay.co.uk/sch/i.html?_&_nkw=datashuffle&_sacat=0".replace("datashuffle",searchTerm);

		String searchUrl = "http://www.ebay.co.uk/sch/i.html?_from=R40&_nkw=mario&_in_kw=1&_ex_kw=&_sacat=0&_mPrRngCbx=1&_udlo=2&_udhi=8&LH_AUCTYPE=1&_ftrt=901&_ftrv=1&_sabdlo=&_sabdhi=&_samilow=&_samihi=&_sadis=15&_stpos=&_sargn=-1%26saslc%3D1&_salic=3&_sop=12&_dmd=1&_ipg=50".replace("datashuffle", searchTerm).replace("minprice", min).replace("maxprice", max).replace("AUCTYPE", auctionType);

		ArrayList<Item> whatYouWant = new ArrayList<>();
		Document guitarSearch = DocumentLoader.load(new URL(searchUrl));
		EbayResultScraper thing1 = new EbayResultScraper(guitarSearch);
		ArrayList<String> links = thing1.scrapeLinks();

		for (String link : links) {
			Document res = DocumentLoader.load(new URL(link));
			EbayItemScraper ebayScraper = new EbayItemScraper(res);

			whatYouWant.add(ebayScraper.scrapeDocument());

		}
		return whatYouWant;
	}

//	public static Group getSearchResults() {
//		return d.get(0);
//	}

	
	public static boolean getLoadFlag(){
		return loadFlag;
	}

	public static void reset() {
		d.clear();
		Attribute.reset();
	}
}
