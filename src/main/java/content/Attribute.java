package content;


import java.util.HashMap;
import java.util.Map;

/**
 * Represents each attribute of an Item, along with a getSelected
 * @author nichita, zane
 *
 */
public class Attribute<T> implements Map.Entry<String,T>{
	private T t;
	private String name;
	private static Map<String,Selected> selectedAtts = new HashMap<>();
	
	public Attribute(String name, T t, Selected s){
		this.name = name;
		this.t = t;
		selectedAtts.putIfAbsent(name,s);
	}
	public Attribute(String name, T t){
		this(name,t,Selected.Maybe);
	}
	public Attribute(Map.Entry<String,T> x){
		this(x.getKey(),x.getValue());
	}

	public static Selected getSel(String k){
		return selectedAtts.get(k);
	}

	public static boolean isSel(String k, Selected x){
		return selectedAtts.getOrDefault(k,Selected.Never).value() >= x.value();
	}

	public static boolean isSel(String k, int x){
		return selectedAtts.getOrDefault(k,Selected.Never).value() >= x;
	}

	public static void setSel(String k, Selected v){
		if(selectedAtts.containsKey(k)) {
			selectedAtts.replace(k, v);
		} else {
			selectedAtts.put(k,v);
		}
	}


	@Override
	public String getKey() {
		return getName();
	}

	public T getValue() {
		return t;
	}

	@Override
	public T setValue(T value) {
		return t=value;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getKey() + ":" + getValue();
	}
}