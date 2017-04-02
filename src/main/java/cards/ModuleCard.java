package cards;

import content.Item;
import sidebar.SideMenuItems;

/**
 * Created by nichita on 02/04/17.
 */
public class ModuleCard extends Card {
    public ModuleCard(Item i, int numDeck, SideMenuItems subjectSidebar) {
        super(i, numDeck, subjectSidebar, i.get("Module Code"));
    }
}
