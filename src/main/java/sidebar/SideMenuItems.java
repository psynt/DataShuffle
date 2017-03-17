package sidebar;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class SideMenuItems {
	public static BorderPane createSidebarItems()
    {
    	// buttons created and added to side menu
        /*final Button addCard = new Button("Add card to...");
        addCard.getStyleClass().add("addCard");
        addCard.setMaxWidth(Double.MAX_VALUE);
        addCard.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("i like big buttons and can not lie");
            }
        });
        addCard.fire();*/
        
        final BorderPane menuPane = new BorderPane();
        TitledPane t1 = new TitledPane("add card to...", new Button("add card to..."));
        TitledPane t2 = new TitledPane("Show only..", new Button("Title"));
        TitledPane t3 = new TitledPane("Export to...", new Button("Excel"));
        final Button price = new Button("Price");
        
        
        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(t1, t2, t3);
        
     
        menuPane.setTop(accordion);
        return menuPane;
    }
}