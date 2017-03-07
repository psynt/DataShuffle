import webscraper.clever.POSTRequest

/**
 * Created by nichita on 06/03/17.
 */


def req = new POSTRequest('http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp')
def res =  req.getTheGoodies(['ucas_course':'g400','year_id':'000116'],
        'http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=Ucas').parse()

res.select("tr:has(td)").each {
    println " ${it.text()} => ${it.select("a").attr ('abs:href')} "
}

//println res

