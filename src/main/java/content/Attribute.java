package content;


/**
 * Represents each attribute of an Item, along with a getSelected
 * @author nichita
 *
 */
public interface Attribute<T> {
	public Selected getSelected();
	public T getValue();
	public String getName();

}
