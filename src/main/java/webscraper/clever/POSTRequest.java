package webscraper.clever;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nichita on 06/03/17.
 * Base class to deal with websites that require POST in order to get to the goodies
 */
public class POSTRequest{

    protected Map<String,String> cookie;
    protected Response from;
    private String ref;


    /**
     * Constructs a POSTRequest to be fired at a later date... time... to be fired later
     * @param url url of the website that contains the POST form
     * @param referrer the website that "sent you here"... heee, hee, hee
     * @throws IOException if stuff goes wrong
     */
    public POSTRequest(String url, String referrer) {
        ref = url;
        try {
            from =
                    Jsoup.connect(url)
                            .referrer(referrer)
                            .userAgent("Mozilla/5.0")
                            .timeout(10 * 1000)
                            .followRedirects(true)
                            .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a POSTRequest to be fired at a later date... time... to be fired later
     * @param url url of the website that contains the POST form
     * @throws IOException if stuff goes wrong
     */
    public POSTRequest(String url) {
        ref = url;
        try {
            from =
                    Jsoup.connect(url)
                            .userAgent("Mozilla/5.0")
                            .timeout(10 * 1000)
                            .followRedirects(true)
                            .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Allows you to chain these types of objects together. Uses the cookie form the previous response for the next one
     * @param r previous request. Because chaining is nice.
     * @param referrer POST referrer
     */
    public POSTRequest(Response r, String referrer){
        ref = referrer;
        from = r;
    }

    /**
     * Allows you to chain these types of objects together. Uses the cookie form the previous response for the next one
     * @param r previous request. Because chaining is nice.
     */
    public POSTRequest(Response r){
        ref = "null";
        from = r;
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
     *
     * @throws IOException if something fails
     */
    protected Response getTheGoodies(Map<String,String> args, String secondUrl){

        try {
            Response response = Jsoup.connect(secondUrl)
                    //referrer will be the login page's URL
                    .referrer(ref)
                    //user agent
                    .userAgent("Mozilla/5.0")
                    //connect and read time out
                    .timeout(10 * 1000)
                    //post parameters
                    .data(args)
                    //cookies received from login page
                    .cookies(from.cookies())
                    //many websites redirects the user after login, so follow them (module catalog also redirects)
                    .followRedirects(true)
                    .execute();

            ref = secondUrl;

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}


