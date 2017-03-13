import org.jsoup.helper.HttpConnection

/**
 * Created by nichita on 06/03/17.
 */

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

//grab login form page first
Response loginPageResponse =
    Jsoup.connect("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp")
//        .referrer("https://www.nottingham.ac.uk/academicservices/informationforformerstudents/programme-specifications.aspx")
        .userAgent("Mozilla/5.0")
        .timeout(10 * 1000)
        .followRedirects(true)
        .execute();

System.out.println("Fetched page with form");

//get the cookies from the response, which we will post to the action URL
//Map<String, String> mapLoginPageCookies = loginPageResponse.cookies();

//println mapLoginPageCookies
def mapLoginPageCookies = ["ASPSESSIONIDSACBSRTT":"LKEECMFCGJOACOJCCDGKHKFC"];

//lets make data map containing all the parameters and its values found in the form
Map<String, String> mapParams = new HashMap<String, String>();
mapParams.put("ucas_course", "g400");
mapParams.put("year_id", "000116");
//mapParams.put("seclogin", "on");
//mapParams.put("login", "YOUR_USER_ID");
//mapParams.put("passwd", "YOUR_PASSWORD");
//mapParams.put("remember", "1");
//mapParams.put("proceed", "Go");

//URL found in form's action attribute
String strActionURL = "http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=UCAS";

Response responsePostLogin = Jsoup.connect(strActionURL)
        //referrer will be the login page's URL
        .referrer("http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp")
        //user agent
        .userAgent("Mozilla/5.0")
        //connect and read time out
        .timeout(10 * 1000)
        //post parameters
        .data(mapParams)
        //cookies received from login page
        .cookies(mapLoginPageCookies)
        //many websites redirects the user after login, so follow them
        .followRedirects(true)
        .execute();

System.out.println("HTTP Status Code: " + responsePostLogin.statusCode());

//parse the document from response
Document document = responsePostLogin.parse();
System.out.println(document);

//get the cookies
Map<String, String> mapLoggedInCookies = responsePostLogin.cookies();

/*
* For all the subsequent requests, you need to send
* the mapLoggedInCookies containing cookies
*/


//[ASPSESSIONIDSACBSRTT:LKEECMFCGJOACOJCCDGKHKFC]
//[ASPSESSIONIDSACBSRTT:MKEECMFCJNGKEDMFEMIFPLMI]

