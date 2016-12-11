package content;

import java.util.ArrayList;

/**
 * Item will be the interface that all the different types of entries that we will have to deal with will implement
 * All Items should have a list of properties and a state - either selected, unselected or maybe
 * 
 * @author nichita
 *
 */

public class Item {
	private Selected sel;
	private ArrayList<Attribute> attributes;

	public Item(Selected sel) {
		this.sel = sel;
		this.attributes = new ArrayList<>();
	}
	public Item() { this(Selected.Maybe); }
	/**
	 * 
	 * @return User's classification of this item, either yes, no, or maybe
	 */
	public Selected getSelected(){
		return sel;
	}
	
	/**
	 * 
	 * @return List containing all of the item's Attributes
	 */
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}

	/**
	 * Adds attribute to list
	 * @param a Attribute to be added
	 */
	public void addAttribute(Attribute a){
		attributes.add(a);
	}

}
