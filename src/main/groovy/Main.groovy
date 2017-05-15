import javafx.application.Application
import javafx.concurrent.Task
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ProgressBar
import javafx.scene.layout.VBox
import javafx.stage.Stage
import model.Data
import webscraper.reterivers.EbayGetter
import webscraper.reterivers.ModuleGetter

/**
 * Created by nichita on 11/05/17.
 */
class Main extends Application{
    @Override
    void start(Stage primaryStage) throws Exception {

//        Thread t = new Thread(new ModuleGetter().getTask(["code":"g4g1"]))
//        t.start()
//        t.join()
//        println
//        println ModuleGetter.d
        primaryStage.setScene(new Scene(new Mainc()));
        primaryStage.show()
    }
}

class Mainc extends VBox{
    Mainc(){
        VBox v = new VBox()
        ProgressBar pb = new ProgressBar();
        Task<Data> t = new ModuleGetter().getTask(["code":"G4G1"])
        pb.progressProperty().bind(t.progressProperty())
        Button ok = new Button("ok")
        ok.setOnAction{
            println t.get()
            ok.getScene().getWindow().hide()

        }

        ok.disableProperty().bind(t.runningProperty())
        getChildren().addAll(pb,ok);

        Thread th = new Thread(t)
        th.start()
        th.join()
        println t.get()

    }
}