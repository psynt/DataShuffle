package cards;

import java.util.ArrayList;

import content.Attribute;
import content.Item;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

//The 'card'
public class EbayCard extends Card {


    public EbayCard(Item cardItem){
    	
    	super(cardItem);

    	ArrayList<Attribute> x = cardItem.getAttributes();
    	
        ImageView cardImg = new ImageView(x.get(x.size()-1).toString()) ;
        cardImg.setFitHeight(150);
        cardImg.setFitWidth(150);

        //super.addComponent(cardImg);

    }

    


}
