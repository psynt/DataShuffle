package sidebar;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SideMenuItems {
	
	private List<Card> observerCards = new ArrayList<>();
	
	VBox showItemsCheckBoxLayout;
	
	//project buttons
	Button newProjectButton = new Button("New");
	Button saveAsButton = new Button("Save As..");

	//export to buttons
	Button exportExcelButton = new Button("Excel");
	
	public BorderPane createSidebarItems()
    {
		final BorderPane menuPane = new BorderPane();
		/*This function creates the item inside the side menu and places them in a pane, the menupane. This menupane is 
		 * returned when the function is called, to be put inside the side menu.
		 */

		//Create project button layout and add buttons
		VBox projectButtonLayout = new VBox(newProjectButton, saveAsButton);
		projectButtonLayout.setAlignment(Pos.CENTER);
		
		//Create layout for add card to button in side menu and add buttons
//		VBox addCardButtonLayout = new VBox(yesButton, noButton, maybeButton);
//		addCardButtonLayout.setAlignment(Pos.CENTER);
		
		//Create layout for select items to show button and add buttons
		showItemsCheckBoxLayout = new VBox();
		showItemsCheckBoxLayout.setAlignment(Pos.CENTER_LEFT);
		
		//Create layout for export to button in side menu and add buttons
		VBox exportToButtonLayout = new VBox(exportExcelButton);
		exportToButtonLayout.setAlignment(Pos.CENTER);
	
		//add submenu buttons to parent menu buttons
		TitledPane t1 = new TitledPane("Project", projectButtonLayout);
//		TitledPane t2 = new TitledPane("Add card to...",  addCardButtonLayout);
		TitledPane t3 = new TitledPane("Show only..", showItemsCheckBoxLayout);
		TitledPane t4 = new TitledPane("Export to...", exportToButtonLayout);

		//Add Parent menu buttons to accordian menu
		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(t1, /*t2,*/ t3, t4);
		
		menuPane.setTop(accordion);
		return menuPane;
        	
    }

	public void attach(Card observer){
		observerCards.add(observer);
	}

	/**
	 * Notifies all cards to update their fields based on changes to tick boxes.
	 */
	public void notifyAllObservers(){
		System.out.println("Update all cards");
		for (Card observer : observerCards) {
			observer.update();
		}
	}
	
	public VBox getShowItemsCheckBoxLayout(){
		return showItemsCheckBoxLayout;
	}
}