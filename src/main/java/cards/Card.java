package cards;

import content.Attribute;
import content.Item;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
        
        i.getAttributes().stream().filter(e -> !e.getName().matches("(i|I)mage")).forEach(e ->
        		addLabels(e)
        );
        

        layoutManager.getChildren().addAll(labels);
        this.setContent(layoutManager);
        this.setClosable(false);
        
        
    }
    
    private void addLabels(Attribute e) {
        labels.add(new Label(e.getName() + "\t:\t" + e.getValue()));
	}

	public void addComponent(Node n){
    	layoutManager.getChildren().add(n);
    }

}
