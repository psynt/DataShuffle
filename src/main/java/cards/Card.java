package cards;

import content.Item;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends TabPane{

    private ArrayList<Label> card = new ArrayList<>();

    public Card(Item i){
        i.getAttributes().entrySet().stream().filter(e -> !e.getKey().matches("(i|I)mage")).forEach(e ->
                card.add(new Label(e.toString()))
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
