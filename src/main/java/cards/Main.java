package cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cards.SideMenu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/MainSurface.fxml"));
        primaryStage.setTitle("Hello World");
        
        BorderPane mainScreen = new BorderPane();
     // create a WebView to show to the right of the SideBar.
        mainScreen.setStyle("-fx-background-color: #2f4f4f;");
        mainScreen.setPrefSize(800, 600);
        
     // create a sidebar with some content in it.
        final Pane menuPane = SideMenuItems.createSidebarItems();
        SideMenu sideMenu = new SideMenu(250, menuPane);
        VBox.setVgrow(menuPane, Priority.ALWAYS);
        
     // layout the scene.
        StackPane buttonLocation = new StackPane();
        buttonLocation.getChildren().addAll(mainScreen, sideMenu.getDisplayMenuButton());
        buttonLocation.setAlignment(Pos.CENTER_RIGHT);

        root.setRight(sideMenu);
        root.setCenter(buttonLocation);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}