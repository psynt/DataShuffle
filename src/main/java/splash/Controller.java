package splash;

import cards.CardState;
import cards.SecondMain;
import content.Attribute;
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
import webscraper.reterivers.EbayGetter;
import webscraper.reterivers.ModuleGetter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.util.*;

public class Controller {

	@FXML
	AnchorPane pane;
	@FXML
	Button ebayButton;


	@FXML
	Button moduleButton;

	private static boolean loadFlag = false;

	private static Data d = new Data();

	public static Data getData(){
		return d;
	}

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
				SecondMain.start1((Stage) pane.getScene().getWindow());
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
					d = ebay(userTextField.getText()
							, minTextField.getText()
							, maxTextField.getText()
							, cb.getSelectionModel().getSelectedItem().toString());
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
					d = modules(userTextField.getText()
							   , minTextField.getText());
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
			source.getScene().getStylesheets().clear();
			if (!d.isEmpty()) {
				SecondMain.start1((Stage) pane.getScene().getWindow());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("Caught");
		}
	}

	private Data modules(String keyword, String code) throws MalformedURLException {
		Map<String,String> args = new HashMap<>();
		if (code != null) args.put("code",code);
		if (keyword != null) args.put("keyword",keyword);
		return new ModuleGetter().getTheStuff(args);
	}

	private Data ebay(String searchTerm, String min, String max, String auctionType) throws MalformedURLException {
		Map<String,String> args = new HashMap<>();
		args.put("searchTerm",searchTerm);
		args.put("min",min);
		args.put("max",max);
		args.put("auctionType",auctionType);
		return new EbayGetter().getTheStuff(args);
	}


	
	public static boolean getLoadFlag(){
		return loadFlag;
	}

	public static void reset() {
		d = new Data();
		Attribute.reset();
	}
}
