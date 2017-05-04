package cards;

import java.io.Serializable;

import content.Group;
import content.Item;
import sidebar.SideMenuItems;

/**
 * Created by nichita on 02/04/17.
 */
public class ModuleCard extends Card implements Serializable {
	private static final long serialVersionUID = 8845074631388615041L;

	public ModuleCard(Group g, Item i, SideMenuItems subjectSidebar) {
        super(g, i, subjectSidebar, i.get("Module Code"));
    }

}
