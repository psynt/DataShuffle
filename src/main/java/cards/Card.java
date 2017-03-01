package cards;

import content.Attribute;
import content.Item;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends TabPane{

    private ArrayList<Label> card = new ArrayList<>();

    public Card(Item i){
        i.getAttributes().stream().forEach(e ->
                card.add(new Label(e.getName() + ":" + e.getValue()))
        );


        Tab tab = new Tab();
        tab.setClosable(false);
        VBox thingy = new VBox();


        thingy.getChildren().addAll(card);
        tab.setContent(thingy);
        tab.setClosable(false);

        super.getTabs().add(tab);

    }

}
