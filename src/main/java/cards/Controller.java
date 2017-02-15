package cards;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {



    @FXML Pane mainPane;
    @FXML VBox cardStackLeft;
    @FXML VBox cardStackRight;



    @FXML
    public void initialize(){

        // Creating a card for each column of the two vbox's
        ebayCard c1 = new ebayCard("01","12.00","good","2 hours", "12.00");
        ebayCard c2 = new ebayCard("01","12.00","good","2 hours", "12.00");
        ebayCard c3 = new ebayCard("01","12.00","good","2 hours", "12.00");
        ebayCard c4 = new ebayCard("01","12.00","good","2 hours", "12.00");
        ebayCard c5 = new ebayCard("01","12.00","good","2 hours", "12.00");
        ebayCard c6 = new ebayCard("01","12.00","good","2 hours", "12.00");

        //add the left cards to the left vbox
        cardStackLeft.getChildren().add(0,c1);
        cardStackLeft.getChildren().add(1,c2);
        cardStackLeft.getChildren().add(2,c3);

        //add the right cards to the right vbox
        cardStackRight.getChildren().add(0,c4);
        cardStackRight.getChildren().add(1,c5);
        cardStackRight.getChildren().add(2,c6);





    }

}
