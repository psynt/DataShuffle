package content;

import java.io.Serializable;
import java.util.*;

/**
 * Item will be the interface that all the different types of entries that we
 * will have to deal with will implement All Items should have a list of
 * properties and a state - either selected, unselected or maybe
 * 
 * @author nichita
 *
 */

public class Item extends HashMap<String, String> implements Serializable {
	private static final long serialVersionUID = 5922686108268054895L;
	private boolean sel;

	public Item(boolean sel) {
		super();
		this.sel = sel;
	}

	public Item() {
		this(true);
	}


	public void unSelect(){
		sel = false;
	}

	/**
	 * 
	 * @return User's classification of this item, either yes, no, or maybe
	 */
	public boolean getSelected() {
		return sel;
	}

	/**
	 * 
	 * @return List containing all of the item's Attributes
	 */
	public Map<String, String> getAttributes() {
		return this;
	}

	/**
	 * Adds attribute to list
	 * 
	 * @param a
	 *            Attribute to be added
	 */
	public void addAttribute(Attribute a) {
		put(a.getKey(), a.getValue().toString());
	}

	private void addAttribute(Map.Entry<String, String> a) {
		put(a.getKey(), a.getValue());
	}

	/**
	 * Adds attributes to list
	 * 
	 * @param a
	 *            Attributes to be added
	 */
	public void addAttributes(List<Map.Entry<String, String>> a) {
		a.parallelStream().forEach(this::addAttribute);
	}


	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		forEach((key, value) -> sb.append("[").append(key).append(":").append(value).append("], "));
		return "\n\t\t[" + sb.toString() + "]\n";
	}

	/**
	 *
	 * @return true if this item is selected
	 */
    public boolean isSelected() {
		return sel;
    }
}
