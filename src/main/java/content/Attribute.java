package content;


/**
 * Represents each attribute of an Item, along with a getSelected
 * @author nichita
 *
 */
public class Attribute< T > {
	
	private T t;
	private String name;
	
	public Attribute(String name, T t){
		this.name = name;
		this.t = t;
	}
	
	public Selected getSelected() {
		return null;
	}
	public void setValue(T t){
		this.t = t;
	}
	public T getValue() {
		return t;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName() {
		return null;
	}

}