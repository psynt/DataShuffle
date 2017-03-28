package cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/MainSurface.fxml"));
        primaryStage.setTitle("Hello World");        
        primaryStage.setScene(new Scene(root));
        
        primaryStage.show();
        
        
      /*  Delete comments for code that should add side menu to main screen
     // create a sidebar with some content in it.
     		int sideMenuWidth = 250;
     		final Pane menuPane = SideMenuItems.createSidebarItems();//create pane containing menu buttons
     		SideMenu sideMenu = new SideMenu(sideMenuWidth, menuPane);//create interactive side menu, passing its width and the button pane
     		VBox.setVgrow(menuPane, Priority.ALWAYS);//will always be at maximum vertical height
     		
     		/*initialize sideMenu controller, passing in current width, sideMenu panel and buttons
     		 * these are needed to call methods in contoller class*/
     		/*SideMenuController.Initialize(sideMenuWidth, sideMenu, menuPane);
     		
     		//create pane to place button on main screen, positioned to the centre right
     		StackPane buttonLocation = new StackPane();
    		buttonLocation.getChildren().addAll(sideMenu.getDisplayMenuButton());
    		buttonLocation.setAlignment(Pos.CENTER_RIGHT);

    		//add side menu and toggle button to main screen
    		root.setRight(sideMenu);
    		root.setCenter(buttonLocation);
    		*/
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}