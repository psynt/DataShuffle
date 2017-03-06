package cards;

import content.Attribute;
import content.Item;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;

/**
 * The 'card'
 *
 * Created by nichita on 01/03/17.
 *
 */
public class Card extends Tab {

	
	private VBox layoutManager;
    private ArrayList<Label> labels = new ArrayList<>();

    public Card(Item i){
    	
        layoutManager = new VBox();
        layoutManager.setMinHeight(200);
        layoutManager.setMinWidth(400);
        
        i.entrySet().stream().filter(e -> !e.getKey().matches("(i|I)mage")).forEach(e ->
        		addLabel(e)
        );
        

        layoutManager.getChildren().addAll(labels);
        this.setContent(layoutManager);
        this.setClosable(true);
        
        //this.setStyle("-fx-border-color:red; -fx-background-color: blue;");
    }
    
    private void addLabel(Map.Entry<String,String> e) {
        labels.add(new Label(e.getKey() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n){
    	layoutManager.getChildren().add(n);
    }

}
