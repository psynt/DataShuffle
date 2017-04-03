package sidebar;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import cards.Card;
import content.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import saver.ExcelSaver;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import splash.Main;

public class SideMenuController extends SideMenuItems
{

	Parent root;
	Stage stage;
	int YES=0, NO=1, MAYBE=2, NULL=3;
	public static int addToGroup;
	// apply the animations when the button is pressed.
	
	public void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane, ArrayList<Item> results){	
	
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
			//JFileChooser fileChooser = new JFileChooser();
			//fileChooser.setDialogTitle("Choose a file to save to"); 
			//if (fileChooser.showSaveDialog(Component) == JFileChooser.APPROVE_OPTION) {
			 // File file = fileChooser.getSelectedFile();
			  // save to file
			

			System.out.print("Save as test");
		});

		openButton.setOnAction(event -> {
			System.out.print("open test");
		});

		yesButton.setOnAction(event -> {
			addToGroup=YES;
			notifyAllObservers();
			System.out.print("yes test");
		});

		maybeButton.setOnAction(event -> {
			addToGroup=NO;
			notifyAllObservers();
			System.out.print("maybe test");
		});

		noButton.setOnAction(event -> {
			addToGroup=MAYBE;
			notifyAllObservers();
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
			ExcelSaver calc = new ExcelSaver(results);
			System.out.print("excel test");
		});

	}
				
				
			
}