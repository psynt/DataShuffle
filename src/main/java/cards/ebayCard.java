package cards;

import content.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//The 'card'
public class ebayCard extends TabPane {

    private String id ;//guitar
    private String cost ;// "£577";
    private String condition ;// "Good as New";
    private String remainingTime ;// "5 days";
    private String shippingCost ;// "£25";
    private String imgurl; // image url

    private ArrayList<Label> card = new ArrayList<>();
    public ebayCard(Item cardItem){


        super();

        super.setPrefSize(250,200);

        this.id = cardItem.
        this.cost = cost;
        this.condition = condition;
        this.remainingTime = remainingTime;
        this.shippingCost = shippingCost;
        this.imgurl = imgurl;



        name.setText(getID());
        price.setText(getCost());
        cond.setText(getCondition());
        remTime.setText(getRemainingTime());
        shipping.setText(getShippingCost());

        imageurl = new ImageView(imgurl);



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
            shipping,
                imageurl
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
