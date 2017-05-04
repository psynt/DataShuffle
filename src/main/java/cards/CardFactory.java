package cards;

import content.Group;
import content.Item;
import sidebar.SideMenuItems;

/**
 * Created by nichita on 06/03/17.
 *
 * This class will handle the logic regarding which type of card gets created upon running our app
 *
 */
public class CardFactory {

    public static final String[] VERSIONS = { "Ebay", "Module" };

    public static Card createCard(Group g, Item item, String type, SideMenuItems sidebarSubject){
		final Card card;
		switch (type) {
            case "Ebay":
                card = new EbayCard(g, item,  sidebarSubject);
                break;
            case "Module":
                card = new ModuleCard(g, item,sidebarSubject);
                break;
            default:
                card = new Card(g, item,  sidebarSubject, "Name");
                break;
        }
        return card;

    }

}
