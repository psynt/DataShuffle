package cards;

import model.Group;
import content.Item;
import sidebar.SideMenuItems;

/**
 * Created by nichita on 06/03/17.
 *
 * This class will handle the logic regarding which type of card gets created upon running our app
 *
 */
public class CardFactory {

    /**
     * Creates a new card with the appropriate type
     * @param g group that the card will belong to
     * @param item Item that will serve as the card's model
     * @param type the type of card
     * @param sidebarSubject the sidebar, so that the cards can observe it
     * @return returns a new card that is ready to be added to its appropriate Deck
     */
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
