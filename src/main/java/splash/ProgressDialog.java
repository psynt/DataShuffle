package splash;

import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import model.Data;
import webscraper.reterivers.ModuleGetter;

/**
 * Created by nichita on 11/05/17.
 */
public class ProgressDialog extends VBox {
    public ProgressDialog(Task<Data> t) {
        ProgressBar pb = new ProgressBar();
        pb.progressProperty().bind(t.progressProperty());
        Button ok = new Button("ok");
        ok.setOnAction(e-> ok.getScene().getWindow().hide());
        ok.disableProperty().bind(t.runningProperty());
        getChildren().addAll(pb,ok);
    }
}
