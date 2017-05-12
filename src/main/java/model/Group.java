package model;

import content.Item;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by nichita on 04/05/17.
 */
public class Group extends ArrayList<Item> implements Serializable {
	private static final long serialVersionUID = 2154804434493761172L;
	private String name = null;
    private String col;

    public Group(String name){
        super();
        this.name = name;
    }

    public Group(String name, String colour){
        super();
        this.name = name;
        col = colour;
    }

    public Group() {}

    public Group(Collection<? extends Item> c, String s) {
        super(c);
        this.name = s;
    }

    /**
     * Colour is used as a css class for this group's deck
     * @param c string representing the colour
     */
    public void setColour(String c){
        col=c;
    }
    public String getColour(){
        return col;
    }
    public void setColor(String c){
        col=c;
    }
    public String getColor(){
        return col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        forEach(it -> sb.append(it.toString()));
        return "\n\t[" + sb.toString() + "]\n";
    }

    /**
     * if no items are selected, this group might as well not even be selected.
     * @return true if at least one item is selected, false otherwise
     */
    public boolean isSelected() {
        return stream().anyMatch(Item::isSelected);
    }


}
