package sidebar;

import content.Attribute;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Data;
import saver.ExcelSaver;

import static splash.Controller.getData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import cards.CardState;



public class SideMenuController extends SideMenuItems
{

	Stage fileMenu;

	public void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane, Data d){

		//setup observer pattern
		
		SideMenu.displayMenuButton.setOnAction(event -> sideMenu.collapseSideMenu(sideMenuWidth, menuPane));


		newProjectButton.setOnAction(event -> {
			splash.Controller.reset();
			Stage stage = (Stage) newProjectButton.getScene().getWindow();

			stage.setScene(splash.Main.getScene());
			
		});

		saveAsButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save file as..");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

			File file = fileChooser.showSaveDialog(fileMenu);

			if(file != null){
				CardState saveState = new CardState();
				saveState.setData(getData());
				try {
					ObjectOutputStream obj_out;
					obj_out = new ObjectOutputStream (new FileOutputStream(file));
					obj_out.writeObject ( saveState );
					obj_out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.print("Save as test");
		});

		exportExcelButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save file as..");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Excel spreadsheets", "*.xls")
					);
			File file = fileChooser.showSaveDialog(fileMenu);
			try {
				new ExcelSaver(d).writeToFile(file);
				System.out.println("Exported");
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

	public void addShowTickBox(String key) {
		// TODO Auto-generated method stub
		CheckBox newCheckBox = new CheckBox(key);
		newCheckBox.setSelected(true);
		newCheckBox.setOnAction(event -> {
			Attribute.setSel(key, ((CheckBox)event.getSource()).isSelected());
			notifyAllObservers();
		});
		
		showItemsCheckBoxLayout.getChildren().add(newCheckBox);
		
	}


}