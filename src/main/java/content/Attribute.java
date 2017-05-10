package content;


import java.io.Serializable;
import java.util.*;

/**
 * Represents each attribute of an Item, along with storing the selected/not selected state of each of the attributes
 * @author nichita, zane
 *
 */
public class Attribute<T> implements Map.Entry<String,T>, Serializable {
	private static final long serialVersionUID = 393482718211080790L;
	private T t;
	private String name;
	private static Map<String,Boolean> selectedAtts = new HashMap<>();
	
	public Attribute(String name, T t, Boolean s){
		this.name = name;
		this.t = t;
		selectedAtts.putIfAbsent(name,s);
	}
	public Attribute(String name, T t){
		this(name,t,true);
	}

	public static boolean isSel(String k){
		selectedAtts.putIfAbsent(k,true);
		return selectedAtts.getOrDefault(k,false);
	}

	public static void setSel(String k, boolean v){
		if(selectedAtts.containsKey(k)) {
			selectedAtts.replace(k, v);
		} else {
			selectedAtts.put(k, v);
		}
	}

	@Override
	public String getKey() {
		return name;
	}

	public T getValue() {
		return t;
	}

	@Override
	public T setValue(T value) {
		return t=value;
	}


	@Override
	public String toString() {
		return getKey() + ":" + getValue();
	}

	public static void reset() {
		selectedAtts.clear();
	}

	public static Map<String,Boolean> getAtts() {
		return selectedAtts;
	}

	public static void addAtts(Collection<String> arr){
		arr.forEach(it -> selectedAtts.put(it,true));
	}
}