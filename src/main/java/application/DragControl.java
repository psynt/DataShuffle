package application;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DragControl {
	
	 double orginalSceneX, orginalSceneY;
	 double orginalTranslateX, orginalTranslateY;

	void DragControlTest(){
    final Text source = new Text(100, 100, "DRAG ME");
    final Text target = new Text(300, 100, "DROP HERE");
    source.setCursor(Cursor.HAND);
   
    Main.root.getChildren().addAll(target, source);
    
    /* drag was detected, start a drag-and-drop gesture*/
    /* allow any transfer mode */
 /*source.setOnDragDetected(event ->{
 			Dragboard db = source.startDragAndDrop(TransferMode.ANY);
	        /* Put a string on a dragboard */
	       /* ClipboardContent content = new ClipboardContent();
	        content.putString(source.getText());
	        db.setContent(content);
	        
	        event.consume();
	    }
	);*/
 
 source.setOnMouseClicked(e ->{
	 orginalSceneX = e.getSceneX();
     orginalSceneY = e.getSceneY();
     orginalTranslateX = ((Text)(e.getSource())).getTranslateX();
     orginalTranslateY = ((Text)(e.getSource())).getTranslateY();

 });
 
 
source.setOnMouseDragged(e ->{
	 double offsetX = e.getSceneX() - orginalSceneX;
     double offsetY = e.getSceneY() - orginalSceneY;
     double newTranslateX = orginalTranslateX + offsetX;
     double newTranslateY = orginalTranslateY + offsetY;
         
      ((Text)(e.getSource())).setTranslateX(newTranslateX);
      ((Text)(e.getSource())).setTranslateY(newTranslateY);

	
});


target.setOnMouseEntered(e ->{
        /* the drag-and-drop gesture entered the target */
        //System.out.println("onDragEntered");
        /* show to the user that it is an actual gesture target */
            target.setFill(Color.GREEN);
});


target.setOnDragEntered(new EventHandler <DragEvent>() {
    public void handle(DragEvent event) {
        /* the drag-and-drop gesture entered the target */
        System.out.println("onDragEntered");
        //*show to the user that it is an actual gesture target */
           target.setFill(Color.GREEN);
        }
        
});

    
	}
}
