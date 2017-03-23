package sidebar;

import java.util.ArrayList;
import java.util.Collection;

import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SideMenuItems {
			//project buttons
			static Button newProjectButton = new Button("New");
			static Button saveButton = new Button("Save");
			static Button saveAsButton = new Button("Save As..");
			static Button openButton = new Button("Open");
			
			//add to buttons
			static Button yesButton = new Button("Yes");
			static Button noButton = new Button("No");
			static Button maybeButton = new Button("Maybe");
			
			//ebay show only buttons
			static Button showPriceButton = new Button("Price");
			static Button showTitleButton = new Button("Title");
			static Button showImageButton = new Button("Image");
			static Button showRemainingTimeButton = new Button("Remaining Time");
			
			static //export to buttons
			Button exportExcelButton = new Button("Excel");
	
	public static BorderPane createSidebarItems()
    {
final BorderPane menuPane = new BorderPane();
		/*This function creates the item inside the side menu and places them in a pane, the menupane. This menupane is 
		 * returned when the function is called, to be put inside the side menu.
		 */

		//Create project button layout and add buttons
		VBox projectButtonLayout = new VBox(newProjectButton,saveButton , saveAsButton, openButton );
		projectButtonLayout.setAlignment(Pos.CENTER);
		
		//Create layout for add card to button in side menu and add buttons
		VBox addCardButtonLayout = new VBox(yesButton, noButton, maybeButton);
		addCardButtonLayout.setAlignment(Pos.CENTER);
		
		//Create layout for select items to show button and add buttons
		VBox showItemsButtonLayout = new VBox(showPriceButton, showTitleButton, showImageButton, showRemainingTimeButton);
		showItemsButtonLayout.setAlignment(Pos.CENTER);
		
		//Create layout for export to button in side menu and add buttons
		VBox exportToButtonLayout = new VBox(exportExcelButton);
		exportToButtonLayout.setAlignment(Pos.CENTER);
	
		//add submenu buttons to parent menu buttons
		TitledPane t1 = new TitledPane("Project", projectButtonLayout);
		TitledPane t2 = new TitledPane("Add card to...",  addCardButtonLayout);
		TitledPane t3 = new TitledPane("Show only..", showItemsButtonLayout);
		TitledPane t4 = new TitledPane("Export to...", exportToButtonLayout);

		//Add Parent menu buttons to accordian menu
		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(t1, t2, t3, t4);
		
		menuPane.setTop(accordion);
		return menuPane;
        
		
    }
}