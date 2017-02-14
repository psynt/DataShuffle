package cards;/**
 * Created by edwardbutcher on 2/5/17.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class Card extends BorderPane {



    //Mock fields
    private String id ;
    private String cost ;// "£577";
    private String condition ;// "Good as New";
    private String remainingTime ;// "5 days";
    private String shippingCost ;// "£25";


    private Label name = new Label();
    private Label price = new Label();
    private Label cond = new Label();
    private Label remTime = new Label();
    private Label shipping = new Label();

    public Card(String id, String cost,String  condition, String remainingTime, String shippingCost){
        this.id = id;
        this.cost = cost;
        this.condition = condition;
        this.remainingTime = remainingTime;
        this.shippingCost = shippingCost;

        name.setText(getID());
        price.setText(getCost());
        cond.setText(getCondition());
        remTime.setText(getRemainingTime());
        shipping.setText(getShippingCost());




        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();
        Tab tab = new Tab();
        tab.setClosable(false);


        tab.setText(getID());
        VBox tabBox = new VBox();
        tabBox.setAlignment(Pos.TOP_CENTER);
        tabBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        tabBox.getChildren().addAll(
                name,
                price,
                cond,
                remTime,
                shipping
        );


        tab.setContent(tabBox);
        tabPane.getTabs().add(tab);









        borderPane.setCenter(tabPane);
       // root.getChildren().add(borderPane);



    }


    public String getCost(){
        return cost;
    }
    public String getID(){
        return id;
    }
    public String getCondition(){
        return condition;
    }
    public String getRemainingTime(){
        return remainingTime;
    }
    public String getShippingCost(){
        return shippingCost;
    }


    public void start() {

        //setCardProperties("Acoustic Guitar", "£577", "Good as New", "5 days", "£25");



        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.WHITE);

        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();
        Tab tab = new Tab();
        tab.setClosable(false);


        tab.setText(getID());
        VBox tabBox = new VBox();
        tabBox.setAlignment(Pos.TOP_CENTER);
        tabBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        tabBox.getChildren().addAll(
                name,
                price,
                cond,
                remTime,
                shipping
        );


        tab.setContent(tabBox);
        tabPane.getTabs().add(tab);


        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());






        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);


    }
}
