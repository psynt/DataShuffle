package webscraper.clever;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nichita on 20/03/17.
 */
public class ModulePOSTReq extends POSTRequest {
    public ModulePOSTReq() {
        super("http://modulecatalogue.nottingham.ac.uk/nottingham/asp/main_search.asp");
    }

    public Connection.Response courseCode(String code) throws IOException {
        Map<String,String> args = new HashMap<>();
        String secondUrl = "http://modulecatalogue.nottingham.ac.uk/nottingham/asp/FindModule.asp";

        args.put("mnem",code);

        return getTheGoodies(args,secondUrl);

    }
}
