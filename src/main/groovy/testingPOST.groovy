import webscraper.clever.POSTRequest

/**
 * Created by nichita on 06/03/17.
 */


def req = new POSTRequest(url:'http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp')
println req.getTheGoodies(['ucas_course':'g400'],['year_id':'000116'],)

