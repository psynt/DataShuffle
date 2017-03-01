package cards;

import content.Item;

//The 'card'
public class EbayCard extends Card {


    public EbayCard(Item cardItem){


        super(cardItem, cardItem.getAttributes().get(5).toString());



    }


}
