package sidebar;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sidebar.SideMenu;
import sidebar.SideMenuItems;

public class SideMenuController extends SideMenuItems
{

	
	// apply the animations when the button is pressed.
	
	public static void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane){	
	
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
	 

	 showPriceButton.setOnAction(event -> {
			System.out.print("price test");
			});
	 
	 showTitleButton.setOnAction(event -> {
			System.out.print("title test");
			});
	 
	 showImageButton.setOnAction(event -> {
			System.out.print("image test");
			});
	 
	 showRemainingTimeButton.setOnAction(event -> {
			System.out.print("time test");
			});
	 
	 exportExcelButton.setOnAction(event -> {
			System.out.print("excel test");
			});
	 
	
	}
				
				
			
}