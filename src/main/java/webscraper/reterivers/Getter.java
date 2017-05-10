package webscraper.reterivers;

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
     * @return returns a Data object, which is the Model of this application
     * @throws MalformedURLException if the url can't be accessed
     */
    Data getTheStuff(Map<String,String> args) throws MalformedURLException;
}
