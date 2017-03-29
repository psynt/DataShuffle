package cards;

import content.Item;
import javafx.scene.image.ImageView;
import sidebar.SideMenuItems;

/**
 * Cards for the ebay module... thingy... functionality Thsese will support
 * adding the images in listings
 *
 */
public class EbayCard extends Card {

	public EbayCard(Item cardItem, int numDeck, SideMenuItems sidebarSubject) {
		super(cardItem, numDeck, sidebarSubject);

		ImageView cardImg = new ImageView(cardItem.get("image"));
		cardImg.setFitHeight(150);
		cardImg.setFitWidth(150);

		super.addComponent(cardImg);

	}

}
