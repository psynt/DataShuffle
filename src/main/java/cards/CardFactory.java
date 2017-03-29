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

    public static Card createCard(Item item, String type, int g, SideMenuItems sidebarSubject){
		final Card card;
		switch (type) {
            case "Ebay":
                card = new EbayCard(item, g, sidebarSubject);
                break;
            default:
                card = new Card(item, g, sidebarSubject);
                break;
        }
        return card;

    }

}
