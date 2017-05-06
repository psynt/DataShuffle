package webscraper.reterivers;

import model.Data;

import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by nichita on 06/05/17.
 */
public interface Getter {
    Data getTheStuff(Map<String,String> args) throws MalformedURLException;
}
