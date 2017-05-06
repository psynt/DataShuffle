package webscraper.reterivers;

import content.Group;
import content.Item;
import debug.Debug;
import javafx.scene.control.ChoiceDialog;
import model.Data;
import webscraper.CourseScraper;
import webscraper.DocumentLoader;
import webscraper.ModuleScraper;
import webscraper.clever.CoursePOSTReq;
import webscraper.clever.ModulePOSTReq;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by nichita on 06/05/17.
 */
public class ModuleGetter implements Getter {
    @Override
    public Data getTheStuff(Map<String, String> args) throws MalformedURLException {
        System.err.println(args);

        Data d = new Data("Module");

        CoursePOSTReq courseGetter = new CoursePOSTReq();

        Map<String, String> res;
        res = courseGetter.ucasCode(args.get("code"));
        if(res.size()<1) {
            res = courseGetter.keyword(args.get("keyword"));
        }

        List<String> courses = new ArrayList<>(res.keySet());
        ChoiceDialog<String> userChoice = new ChoiceDialog<>(courses.get(0), courses);
        userChoice.setContentText("Please choose your course");


        if (res.size() > 1) {
            userChoice.showAndWait();
        }

        String selCourse = res.get(userChoice.getSelectedItem());


        CourseScraper cs = new CourseScraper(DocumentLoader.load(new URL(selCourse)));
        List<String> modules = cs.getReqModules();
        List<Item> results = modules.stream().filter(e -> e.matches("G5\\d...")).map(e -> {
//			if(e.matches("G5\\d..."))
            try {
                return new ModulePOSTReq().courseCode(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }).map(ModuleScraper::new).map(ModuleScraper::scrapeDocument).filter(e -> !e.get("Module Code")
                .matches("G\\d\\dTUT")).collect(Collectors.toList());
        d.add(new Group(results));
        return d;
    }
}
