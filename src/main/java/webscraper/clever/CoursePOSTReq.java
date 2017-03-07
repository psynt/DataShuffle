package webscraper.clever;


import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by nichita on 06/03/17.
 */
public class CoursePOSTReq extends POSTRequest {


    public CoursePOSTReq() {
        super("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp");

    }

    public Map<String,String> ucasCode(String code){
        Map<String,String> args = new HashMap<>();
        String secondUrl = "http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=Ucas";
        args.put("ucas_code",code);
        args.put("year_id", "000116");
        Map<String,String> res = new HashMap<>();

        try {
            Document doc = getTheGoodies(args,secondUrl).parse();
            doc.select("tr:has(td)").forEach(it -> {
                res.put(it.text(),it.select("a").attr("abs:href"));
            });
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
