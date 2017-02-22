import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import webscraper.DocumentLoader

/**
 * Created by nichita on 22/02/17.
 */


Document doc  = DocumentLoader.load(this.getClass().getResource("/practice/view_specification.asp.html").toURI())

def atts = new ArrayList<String>()

for (Element table : doc.select(":contains(Compulsory)~tr:not(:contains(restricted))")) {
    for (Element row : table.select("tr")) {
        Elements tds = row.select("td");
        if(tds.size()>0) {
            String s = tds.get(0).text();
            if (s.matches("[A-Z]\\d\\d[A-Z][A-Z][A-Z]")) {
                atts.add(s);
            }
        }
    }
}

println doc.select(":contains(Compulsory)~tr").not(":contains(Restricted)~tr")


println atts
