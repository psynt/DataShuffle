package sidebar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Scanner;


import javax.swing.JFileChooser;
import cards.Main;
import cards.Card;
import content.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saver.ExcelSaver;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class SideMenuController extends SideMenuItems
{

	private TextArea textArea;
	File dataFile = null;
	Parent root;
	Stage fileMenu;
	int YES=0, NO=1, MAYBE=2, NULL=3;
	public static int addToGroup;
	
	public void Initialize(int sideMenuWidth, SideMenu sideMenu, Pane menuPane, ArrayList<Item> results){	
	
		//setup observer pattern
		
		SideMenu.displayMenuButton.setOnAction(event -> {
			sideMenu.collapseSideMenu(sideMenuWidth, menuPane);
		});


		newProjectButton.setOnAction(event -> {
			Stage stage;
			Parent root;
			stage=(Stage) newProjectButton.getScene().getWindow();

				try {
					root= FXMLLoader.load(getClass().getResource("/MyMenu.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
				      stage.setScene(scene);
				      stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			System.out.print("NewProject test");
			
		});

		/*save button disabled until save as button has been pressed at least once*/
		saveButton.setOnAction(event -> {
			try (PrintStream ps = 
			        new PrintStream(dataFile)) {
			      
				  String text = ("goodbye");
				
			      ps.print(text);
			      
			    } catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }

		});

		saveAsButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save file as..");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			
			String text = ("hello");

			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Text Files", "*.txt"),
					new FileChooser.ExtensionFilter("Java Files", "*.java")
					);
			
			File file = fileChooser.showSaveDialog(fileMenu);
			
			if(file != null)
			{
			try (PrintStream ps = 
			          new PrintStream(file)) 
					{

			        ps.print(text);
			        //ps.print(textArea.getText());
			        
			        // saving the file
			        dataFile = file;
			                
			        // enabling save as button
			        saveButton.setDisable(false);

			      } catch (FileNotFoundException e)
					{
			    	  e.printStackTrace();
					}
			}
			System.out.print("Save as test");
		});

		openButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose file to open");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			
			/*Filter out all files except text files*/
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Text Files", "*.txt"),
					new FileChooser.ExtensionFilter("Java Files", "*.java")
					);
			
			File file = fileChooser.showOpenDialog(fileMenu);
			
			
			if(file != null){
				System.out.println("File opened: "+ file);
			      try (Scanner scan = 
			          new Scanner(file)) {
			        // grabbing the file data
			        String content = 
			            scan
			            .useDelimiter("\\z")
			            .next();
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

		exportExcelButton.setOnAction(event -> {
			ExcelSaver calc = new ExcelSaver(results);
			System.out.print("excel test");
		});

	}

	public void addShowTickBox(String key) {
		// TODO Auto-generated method stub
		CheckBox newCheckBox = new CheckBox(key);
		newCheckBox.setSelected(true);
		newCheckBox.setOnAction(event -> {
			notifyAllObservers();
		});
		
		showItemsCheckBoxLayout.getChildren().add(newCheckBox);
		
	}
				
				
			
}