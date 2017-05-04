import webscraper.CourseScraper
import webscraper.DocumentLoader
import webscraper.ModuleScraper
import webscraper.clever.CoursePOSTReq
import webscraper.clever.ModulePOSTReq

import java.util.stream.Collectors

/**
 * Created by nichita on 06/03/17.
 */

//
//def req = new POSTRequest('http://programmespec.nottingham.ac.uk/nottingham/asp/course_search.asp')
//def res =  req.getTheGoodies(['ucas_course':'g400','year_id':'000116'],
//        'http://programmespec.nottingham.ac.uk/nottingham/asp/search_courses.asp?Type=Ucas').parse()
//
////res.select("tr:has(td)").each {
////    println " ${it.text()} => ${it.select("a").attr ('abs:href')} "
////}
//
////println res
//
def courses =  new CoursePOSTReq()
def res  = courses. ucasCode("g4g1") values() asList()
println new CourseScraper(DocumentLoader.load(new URL(res.get(0)))).getReqModules()
//
//println new ModuleScraper(new ModulePOSTReq().courseCode("g52afp")).scrapeDocument()//.map(ModuleScraper::new).map(ModuleScraper::scrapeDocument).collect(Collectors.toList())