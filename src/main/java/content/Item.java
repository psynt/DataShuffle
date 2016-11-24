package content;

import java.util.ArrayList;

/**
 * Item will be the interface that all the different types of entries that we will have to deal with will implement
 * All Items should have a list of properties and a state - either selected, unselected or maybe
 * 
 * @author nichita
 *
 */

public interface Item {
	/**
	 * 
	 * @return User's classification of this item, either yes, no, or maybe
	 */
	Selected getSelected();
	
	/**
	 * 
	 * @return List containing all of the item's Attributes that are to be saved to file
	 */
	ArrayList<Attribute> getAttributes();

}
