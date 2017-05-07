package content;

import java.io.Serializable;
import java.util.ArrayList;
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

public class Item extends HashMap<String, String> implements Serializable {
	private static final long serialVersionUID = 5922686108268054895L;
	private Selected sel;

	public Item(Selected sel) {
		super();
		this.sel = sel;
	}

	public Item() {
		this(Selected.Yes);
	}


	public void unSelect(){
		sel = Selected.Never;
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
		a.parallelStream().forEach(this::addAttribute);
	}

	public void addAttributes(Map<String, String> a) {
		a.entrySet().parallelStream().forEach(this::addAttribute);
	}

    public ArrayList<String> keys() {
		return new ArrayList<>(super.keySet());
    }

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		forEach((key, value) -> sb.append("[").append(key).append(":").append(value).append("], "));
		return "\n\t\t[" + sb.toString() + "]\n";
	}

    public boolean isSelected() {
		return sel.value()>Selected.Maybe.value();
    }
}
