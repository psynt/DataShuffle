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
    private int col;

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

    public void setColour(int c){
        col=c;
    }
    public int getColour(){
        return col;
    }
    public void setColor(int c){
        col=c;
    }
    public int getColor(){
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
