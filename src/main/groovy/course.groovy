import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import webscraper.DocumentLoader

/**
 * Created by nichita on 22/02/17.
 */


Document doc  = DocumentLoader.load(this.getClass().getResource("/practice/view_specification.asp.html").toURI())

def atts = new ArrayList<String>()

for (Element row : doc.select(":contains(Compulsory)~tr:not(:contains(Assessment criteria)~tr)")) {
    //for (Element row : table.select("tr")) {
        Elements tds = row.select("td");
        if(tds.size()>0) {
            String s = tds.forEach {
                if (it.text().matches("[A-Z]\\d\\d[A-Z][A-Z][A-Z]") || it.text().matches("Students.*")) {
                    atts.add(it.text());
                }
            }
        }
   // }
}

//println doc.select(":contains(Compulsory)~tr:not(:contains(Assessment criteria)~tr)")

//println doc.select( ":contains(Restricted)~tr:not(:contains(Assessment criteria)~tr)")

//println doc.select(":contains(Assessment criteria)")


doc.select(":contains(Compulsory)~tr:not(:contains(Assessment criteria)~tr)").forEach{ println it }

println atts