package cards;

import content.Item;
import javafx.scene.image.ImageView;

/**
 * Cards for the ebay module... thingy... functionality Thsese will support
 * adding the images in listings
 *
 */
public class EbayCard extends Card {

	public EbayCard(Item cardItem) {

		super(cardItem);

		ImageView cardImg = new ImageView(cardItem.get("image"));
		cardImg.setFitHeight(150);
		cardImg.setFitWidth(150);

		super.addComponent(cardImg);

	}

}
