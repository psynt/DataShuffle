package cards;

import content.Item;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends TabPane {


    private ArrayList<Label> card = new ArrayList<>();

    public Card(Item i){
        i.getAttributes().stream().filter(e -> !e.getName().matches("(i|I)mage")).forEach(e ->
                card.add(new Label(e.getName() + "\t:\t" + e.getValue()))
        );


        Tab tab = new Tab();
        tab.setClosable(false);
        VBox thingy = new VBox();


        thingy.getChildren().addAll(card);
        tab.setContent(thingy);
        tab.setClosable(false);

        super.getTabs().add(tab);
    }




    public Card(Item i, String URL){
        i.getAttributes().stream().filter(e -> !e.getName().matches("(i|I)mage")).forEach(e ->
                card.add(new Label(e.getName() + "\t:\t" + e.getValue()))
        );


        Tab tab = new Tab();
        tab.setClosable(false);
        VBox thingy = new VBox();
        ImageView cardImg = new ImageView(URL) ;
        cardImg.setFitHeight(150);
        cardImg.setFitWidth(150);





        thingy.getChildren().addAll(card);
        thingy.getChildren().add(cardImg);

        tab.setContent(thingy);
        tab.setClosable(false);

        super.getTabs().add(tab);
    }

}
