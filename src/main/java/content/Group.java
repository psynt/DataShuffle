package content;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by nichita on 04/05/17.
 */
public class Group extends ArrayList<Item>{
    private String name = null;
    private Color col;

    public Group(String name){
        super();
        this.name = name;
    }

    public Group(int initialCapacity) {
        super(initialCapacity);
    }

    public Group() {
    }

    public Group(Collection<? extends Item> c) {
        super(c);
    }

    public void setColour(Color c){
        col=c;
    }
    public Color getColour(){
        return col;
    }
    public void setColor(Color c){
        col=c;
    }
    public Color getColor(){
        return col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public Stream<Item> stream() {
//        return super.stream();
//    }
//
//    @Override
//    public Stream<Item> parallelStream() {
//        return super.parallelStream();
//    }
}
