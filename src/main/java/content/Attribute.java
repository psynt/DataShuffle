package content;


/**
 * Represents each attribute of an Item, along with a getSelected
 * @author nichita
 *
 */
public class Attribute< T > {

    private Selected sel;
	private T t;
	private String name;
	
	public Attribute(String name, T t, Selected s){
		this.name = name;
		this.t = t;
		sel = s;
	}
	public Attribute(String name, T t){ this(name,t,Selected.Maybe); }

	public Selected getSelected() {
		return sel;
	}

	public void setSelected(Selected s){
	    sel=s;
    }

	public T getValue() {
		return t;
	}


	public String getName() {
		return null;
	}

}