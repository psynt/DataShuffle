package webscraper.clever;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import webscraper.DocumentLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nichita on 06/03/17.
 * Base class to deal with websites that require POST in order to get to the goodies
 */
public class POSTRequest {

    Map<String,String> cookie;


    /**
     * Constructs a POSTRequest to be fired at a later date... time... to be fired later
     * @param url url of the website that contains the POST form
     */
    public POSTRequest(String url) {
        try {
            //grab login form page first
            Response loginPageResponse =
                    Jsoup.connect("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp")
//                            .referrer("https://www.nottingham.ac.uk/academicservices/informationforformerstudents/programme-specifications.aspx")
                            .userAgent("Mozilla/5.0")
                            .timeout(10 * 1000)
                            .followRedirects(true)
                            .execute();

//            System.out.println("Fetched page with form");

            //get the cookies from the response, which we will post to the action URL
            cookie = loginPageResponse.cookies();


            //lets make data map containing all the parameters and its values found in the form
//            Map<String, String> mapParams = new HashMap<>();
//            mapParams.put("ucas_course", "g400");
//            mapParams.put("year_id", "000116");

            //URL found in form's action attribute
//            String secondUrl = "http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=UCAS";


//            System.out.println("HTTP Status Code: " + responsePostLogin.statusCode());

            //parse the document from response
//            Document document = responsePostLogin.parse();
//            System.out.println(document);

            //get the cookies
//            Map<String, String> mapLoggedInCookies = responsePostLogin.cookies();

            /*
            * For all the subsequent requests, you need to send
            * the mapLoggedInCookies containing cookies
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the POST request to the site hidden behind the pesky form.
     *
     * @param args map containing args filled out the way they should be in the form:
     *             "field_name":"field_value"
     *             don't forget that some forms might have hidden controls -
     *              omitting these might mean you don't get a meaningful response.
     *             also, it is field NAME, not ID in case they differ
     * @param secondUrl URL where the form posts to. found in the "action" data field
     * @return the response from the webpage.
     *          Either response.parse() it to get a Document, or use it to build another one of these objects
     */
    public Document getTheGoodies(Map<String,String> args, String secondUrl){

        try {
            Response response = Jsoup.connect(secondUrl)
                    //referrer will be the login page's URL
                    .referrer("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp")
                    //user agent
                    .userAgent("Mozilla/5.0")
                    //connect and read time out
                    .timeout(10 * 1000)
                    //post parameters
                    .data(args)
                    //cookies received from login page
                    .cookies(cookie)
                    //many websites redirects the user after login, so follow them
                    .followRedirects(true)
                    .execute();

            return response.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}


