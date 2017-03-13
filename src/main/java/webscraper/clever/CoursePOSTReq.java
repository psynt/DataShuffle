package webscraper.clever;


import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by nichita on 06/03/17.
 */
public class CoursePOSTReq extends POSTRequest {

    private String year;

    public CoursePOSTReq() {
        super("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp");

        try {
            year = from.parse().select("select option").attr("value");
        } catch (IOException e) {
            year = "000116";
            e.printStackTrace();
        }

    }

    private Map<String,String> search(Map<String,String> args, String secondUrl){

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


    public Map<String,String> ucasCode(String code){
        Map<String,String> args = new HashMap<>();
        String secondUrl = "http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=Ucas";
        args.put("ucas_course",code);
        args.put("year_id", year);

        return search(args,secondUrl);
    }

    public Map<String,String> keyword(String kw){
        Map<String,String> args = new HashMap<>();
        String secondUrl = "http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=Keyword";
        args.put("keyword",kw);
        args.put("year_id", year);

        return search(args,secondUrl);
    }

}
