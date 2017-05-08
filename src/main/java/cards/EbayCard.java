package cards;

import java.io.Serializable;

import model.Group;
import content.Item;
import javafx.scene.image.ImageView;
import sidebar.SideMenuItems;

/**
 * Cards for the ebay module... thingy... functionality Thsese will support
 * adding the images in listings
 *
 */
public class EbayCard extends Card implements Serializable {

	private static final long serialVersionUID = 3385480495404743216L;

	public EbayCard(Group g, Item cardItem, SideMenuItems sidebarSubject) {
		super(g, cardItem, sidebarSubject, cardItem.get("name"));

		ImageView cardImg = new ImageView(cardItem.get("image"));
		cardImg.setFitHeight(150);
		cardImg.setFitWidth(150);

		super.addComponent(cardImg);


	}

}