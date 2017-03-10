package cards;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class sideBarTest extends Application
{
    BorderPane mainScreen = new BorderPane();

    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception
    {
        stage.setTitle("Side menu test");

// create a WebView to show to the right of the SideBar.
        mainScreen.setStyle("-fx-background-color: #2f4f4f;");
        mainScreen.setPrefSize(800, 600);

// create a sidebar with some content in it.
        final Pane menuPane = createSidebarItems();
        SideMenu sideMenu = new SideMenu(250, menuPane);
        VBox.setVgrow(menuPane, Priority.ALWAYS);

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

    private BorderPane createSidebarItems()
    {
    	// buttons created and added to side menu
        /*final Button addCard = new Button("Add card to...");
        addCard.getStyleClass().add("addCard");
        addCard.setMaxWidth(Double.MAX_VALUE);
        addCard.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("i like big buttons and can not lie");
            }
        });
        addCard.fire();*/
        
        final BorderPane menuPane = new BorderPane();
        TitledPane t1 = new TitledPane("add card to...", new Button("add card to..."));
        TitledPane t2 = new TitledPane("Show only..", new Button("Title"));
        TitledPane t3 = new TitledPane("Export to...", new Button("Excel"));
        final Button price = new Button("Price");
        
        
        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(t1, t2, t3);
        
     
        menuPane.setTop(accordion);
        return menuPane;
    }
}