package cards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


//The 'card'
public class ebayCard2 extends TabPane {

    private String id ;//guitar
    private String cost ;// "£577";
    private String condition ;// "Good as New";
    private String remainingTime ;// "5 days";
    private String shippingCost ;// "£25";

    private Label name = new Label();
    private Label price = new Label();
    private Label cond = new Label();
    private Label remTime = new Label();
    private Label shipping = new Label();

    public ebayCard2(String id, String cost, String  condition, String remainingTime, String shippingCost){


        super();

        super.setPrefSize(250,200);

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


        super.getTabs().add(tab);

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


}
