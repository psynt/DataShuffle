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

	File dataFile = null;
	Stage fileMenu;

	public void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane, Data d){
	
		//setup observer pattern
		
		SideMenu.displayMenuButton.setOnAction(event -> sideMenu.collapseSideMenu(sideMenuWidth, menuPane));


		newProjectButton.setOnAction(event -> {
			splash.Controller.reset();
			Stage stage = (Stage) newProjectButton.getScene().getWindow();

			stage.setScene(splash.Main.getScene());
			
		});

		/*save button disabled until save as button has been pressed at least once*/
		saveButton.setOnAction(event -> {
			saveData("cards");
		});

		saveAsButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save file as..");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Data Files", "*.data")
					);

			File file = fileChooser.showSaveDialog(fileMenu);

			if(file != null){
				CardState saveState = new CardState();
				saveState.setData(getData());
				try {
					ObjectOutputStream obj_out = null;
					obj_out = new ObjectOutputStream (new FileOutputStream(file));
					obj_out.writeObject ( saveState );
					obj_out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.print("Save as test");
			System.out.println(d);
		});

		openButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose file to open");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			
			/*Filter out all files except text files*/
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Text Files", "*.txt")
					//new FileChooser.ExtensionFilter("Java Files", "*.java")
					);
			
			File file = fileChooser.showOpenDialog(fileMenu);
			
			
			if(file != null){
				System.out.println("File opened: "+ file);
					try (Scanner scan = new Scanner(file)) {
						// grabbing the file data
						String content = scan.useDelimiter("\\z").next();
						System.out.println("file content:"+ content);
						// saving the file
						dataFile = file;

						// enabling saveMI
						saveButton.setDisable(false);

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
			    }
			System.out.print("open test");
			
		});
		exportExcelButton.setOnAction(event -> {
//			System.out.println(d);
			try {
				new ExcelSaver(d).writeToFile(new File("cards.xls"));
				System.out.println("Exported");
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.print("excel test");

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
	
	private CardState saveData(String fileName){

		CardState saveState = new CardState();
		saveState.setData(getData());

		// Write to disk with FileOutputStream
		FileOutputStream f_out = null;
		ObjectOutputStream obj_out = null;
		try {
			f_out = new FileOutputStream(fileName + ".data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Write object with ObjectOutputStream
		try {
			obj_out = new ObjectOutputStream (f_out);
			obj_out.writeObject ( saveState );
			f_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveState;
	}
				
				
			
}