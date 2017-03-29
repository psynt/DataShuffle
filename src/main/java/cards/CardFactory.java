package cards;

import content.Item;
import sidebar.SideMenuItems;

/**
 * Created by nichita on 06/03/17.
 *
 * This class will handle the logic regarding which type of card gets created upon running our app
 *
 */
public class CardFactory {

    public static final String[] VERSIONS = { "Ebay" };

    public static Card createCard(Item item, String type, int numDeck, SideMenuItems sidebarSubject){
		final Card card;
		switch (type) {
            case "Ebay":
                card = new EbayCard(item, numDeck, sidebarSubject);
                break;
            default:
                card = new Card(item, numDeck, sidebarSubject);
                break;
        }
        return card;

    }

}
