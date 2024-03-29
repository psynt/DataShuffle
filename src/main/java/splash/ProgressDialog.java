package splash;

import javafx.concurrent.Task;
import javafx.css.Styleable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Data;
import webscraper.reterivers.ModuleGetter;

/**
 * This is just a vbox containing a progress bar, an ok and a cancel button.
 * Give it a task to bind the progress bar too
 * Created by nichita on 11/05/17.
 */
public class ProgressDialog extends VBox {
    public ProgressDialog(Task<Data> t) {
        Label l = new Label();
        l.setPrefWidth(300);

        l.textProperty().bind(t.titleProperty());

        ProgressBar pb = new ProgressBar();
        pb.getStyleClass().add("Probar");
        pb.setPrefSize(150, 24);   
        pb.progressProperty().bind(t.progressProperty());
     
        pb.setPrefWidth(300);
        pb.progressProperty().bind(t.progressProperty());
        HBox box = new HBox();

        Button ok = new Button("ok");
        ok.getStyleClass().add("Probut");
        ok.setOnAction(e-> ok.getScene().getWindow().hide());
        ok.disableProperty().bind(t.runningProperty());
        Button cancel = new Button("cancel");
        cancel.setOnAction(e -> {
            t.cancel();
            cancel.getScene().getWindow().hide();
        });
        cancel.getStyleClass().add("Probut");
        box.getChildren().addAll(ok,cancel);
        getChildren().addAll(l,pb,box);
    }
}
