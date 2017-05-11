import javafx.application.Application
import javafx.concurrent.Task
import javafx.stage.Stage
import model.Data
import webscraper.reterivers.ModuleGetter

/**
 * Created by nichita on 11/05/17.
 */
class Main extends Application{
    @Override
    void start(Stage primaryStage) throws Exception {
//        println new ModuleGetter().getTheStuff(["code":"g404"])
        Task<Data> t = new ModuleGetter().getTask(["code":"g404"])
        Thread th = new Thread(t)
        th.start()
        th.join()
        println t.get()
//        println ModuleGetter.d
    }
}
