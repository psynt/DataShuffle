package content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Item will be the interface that all the different types of entries that we
 * will have to deal with will implement All Items should have a list of
 * properties and a state - either selected, unselected or maybe
 * 
 * @author nichita
 *
 */

public class Item extends HashMap<String, String> {
	private Selected sel;

	public Item(Selected sel) {
		super();
		this.sel = sel;
	}

	public Item() {
		this(Selected.Maybe);
	}

	/**
	 * 
	 * @return User's classification of this item, either yes, no, or maybe
	 */
	public Selected getSelected() {
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

	private void addAttribute(String k, String v) {
		put(k, v);
	}

	/**
	 * Adds attributes to list
	 * 
	 * @param a
	 *            Attributes to be added
	 */
	public void addAttributes(List<Map.Entry<String, String>> a) {
		a.parallelStream().forEach(e -> addAttribute(e));
	}

	public void addAttributes(Map<String, String> a) {
		a.entrySet().parallelStream().forEach(e -> addAttribute(e));
	}
}
