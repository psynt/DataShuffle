package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * One of the model classes. Represents the entire data of the app, in one object
 * Created by nichita on 04/05/17.
 */

public class Data extends ArrayList<Group> implements Serializable {
	private static final long serialVersionUID = 7492858336091135634L;
	private String type;

    public Data(String s){
        type = s;
    }

    public Data() { }

    /**
     * copy constructor :)
     * @param d other data
     */
    public Data(Data d) {
        super(d);
        type = d.type;
    }


    @Override
    public void clear() {
        super.clear();
        type = null;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @return returns the last group. Useful if you quickly want to change its colour, or name, or something else
     */
    public Group last(){
        return get(size()-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        forEach(it -> sb.append(it.toString()));
        return "\n" + type + " : [" + sb.toString() + "]\n";
    }

    @Override
    public int size() {
        return super.size();
    }
}
