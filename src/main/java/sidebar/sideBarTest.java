package sidebar;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class sideBarTest extends Application {
	

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		BorderPane mainScreen = new BorderPane();
		stage.setTitle("Side menu test");

		// create a WebView to show to the left of the SideBar.
		mainScreen.setStyle("-fx-background-color: #2f4f4f;");
		mainScreen.setPrefSize(800, 600);

		// create a sidebar with some content in it.
		int sideMenuWidth = 250;
		final Pane menuPane = SideMenuItems.createSidebarItems();//create pane containing menu buttons
		SideMenu sideMenu = new SideMenu(sideMenuWidth, menuPane);//create interactive side menu, passing its width and the button pane
		VBox.setVgrow(menuPane, Priority.ALWAYS);//will always be at maximum vertical height
		
		/*initialize sideMenu controller, passing in current width, sideMenu panel and buttons
		 * these are needed to call methods in contoller class*/
		SideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane);

		// layout the scene.
		final BorderPane layout = new BorderPane();

		StackPane buttonLocation = new StackPane();
		buttonLocation.getChildren().addAll(mainScreen, sideMenu.getDisplayMenuButton());
		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

		layout.setRight(sideMenu);
		layout.setCenter(buttonLocation);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}
	
	
}