package webscraper.reterivers;

import javafx.concurrent.Task;
import model.Data;

import java.net.MalformedURLException;
import java.util.Map;

/**
 * These classes will interact with the back-end webscrapers to abstract away their entire workflow
 * Created by nichita on 06/05/17.
 */
public interface Getter {

    /**
     * This method goes and gathers all of the needed data
     * @param args a map containing all of the arguments that are to help in getting the desired results
     * @return returns a Task object, which when run will return the model of the app
     * @throws Exception
     */
    Task<Data> getTask(Map<String,String> args) throws Exception;
}
