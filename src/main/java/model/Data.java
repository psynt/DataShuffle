package model;

import content.Group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by nichita on 04/05/17.
 */

public class Data extends ArrayList<Group> {//implements GetData{

    private String type;

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

//    public Data groupBy(String thing){
//        HashMap<String,Group> gs = new HashMap<>();
//
//        get(0).forEach(it -> {
//            String s = it.get(thing);
//            gs.putIfAbsent(s,new Group(s));
//            gs.get(s).add(it);
//        });
//
//        Data d = new Data(gs.values());
//        d.setType(type);
//        return d;
//    }
}
