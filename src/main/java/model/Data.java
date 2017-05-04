package model;

import content.Group;

import java.util.ArrayList;

/**
 * Created by nichita on 04/05/17.
 */

public class Data extends ArrayList<Group> {//implements GetData{

    private String type;

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
}
