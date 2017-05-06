package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nichita on 04/05/17.
 */

public class Data extends ArrayList<Group> {

    private String type;

    public Data(String s){
        type = s;
    }

    public Data(Data d){
        super(d);
        type = d.type;
    }

    public Data() {
    }

    public Data(Collection<? extends Group> c) {
        super(c);
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

    public Group last(){
        return get(size()-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        forEach(it -> sb.append(it.toString()));
        return "\n" + type + " : [" + sb.toString() + "]\n";
    }

}
