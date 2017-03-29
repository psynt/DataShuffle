package sidebar;

import java.util.ArrayList;

import cards.Card;
import javafx.scene.layout.Pane;

public class SideMenuController extends SideMenuItems
{

	
	// apply the animations when the button is pressed.
	
	public void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane){	
	
		//setup observer pattern
		
		SideMenu.displayMenuButton.setOnAction(event -> {
			sideMenu.collapseSideMenu(sideMenuWidth, menuPane);
		});


		newProjectButton.setOnAction(event -> {
			System.out.print("NewProject test");
		});

		saveButton.setOnAction(event -> {
			System.out.print("save test");
		});

		saveAsButton.setOnAction(event -> {
			System.out.print("Save as test");
		});

		openButton.setOnAction(event -> {
			System.out.print("open test");
		});

		yesButton.setOnAction(event -> {
			System.out.print("yes test");
		});

		maybeButton.setOnAction(event -> {
			System.out.print("maybe test");
		});

		noButton.setOnAction(event -> {
			System.out.print("no test");
		});
		
		showPriceCheckBox.setOnAction(event -> {
			System.out.print("price test");
			notifyAllObservers();
		});

		showTitleCheckBox.setOnAction(event -> {
			System.out.print("title test");
			notifyAllObservers();
		});

		showImageCheckBox.setOnAction(event -> {
			System.out.print("image test");
			notifyAllObservers();
		});

		showRemainingTimeCheckBox.setOnAction(event -> {
			System.out.print("time test");
			notifyAllObservers();
		});

		exportExcelButton.setOnAction(event -> {
			System.out.print("excel test");
		});

	}
				
				
			
}