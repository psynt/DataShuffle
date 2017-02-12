package cards;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ebayController {
    //Mock fields
    String id ; //"Acoustic Guitar";
    String cost ;// "£577";
    String condition ;// "Good as New";
    String remainingTime ;// "5 days";
    String shippingCost ;// "£25";




    @FXML private Label name;
    @FXML private Label price;
    @FXML private Label cond;
    @FXML private Label timeLeft;
    @FXML private Label shipping;


    @FXML
    public void setCardProperties(String idx, String cost,String  condition, String remainingTime, String shippingCost){
        this.id=id;
        this.cost = cost;
        this.condition = condition;
        this.remainingTime = remainingTime;
        this.shippingCost = shippingCost;

    }

    @FXML
    private void  initialize() {
        price.setText("Price: "+ cost);
        name.setText("Name: " + id);
        cond.setText("Condition: "+ condition);
        timeLeft.setText("Time Remaining: "+ remainingTime);
        shipping.setText("Shipping: " + shippingCost);
    }









}
