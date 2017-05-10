package webscraper.reterivers;

import content.Attribute;
import content.Item;
import model.Group;
import javafx.scene.control.ChoiceDialog;
import model.Data;
import org.jsoup.Connection;
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
            userChoice.setSelectedItem("");
            userChoice.showAndWait();
        }
        String selCourse = res.get(userChoice.getSelectedItem());

//        String selCourse = new ArrayList<>(res.values()).get(0);

        if (selCourse.length()<2){
            throw new NullPointerException("User selected nothing");
        }


        CourseScraper cs = new CourseScraper(DocumentLoader.load(new URL(selCourse)));
        List<String> modules = cs.getReqModules();

        Group currentGroup = new Group();
        currentGroup.setColour("red");
        for (String e:modules ) {
            if(e.matches("G5\\d...")){
                try {
//                    System.out.println(currentGroup.getName() + " << " + e);
                    Connection.Response r = new ModulePOSTReq().courseCode(e);
                    Item it = new ModuleScraper(r.parse()).scrapeDocument();

                    Item act = new Item(true);

                    act.addAttribute(new Attribute<>("Module Code",it.get("Module Code")));
                    it.entrySet().stream().filter(el -> !el.getKey().equals("Module Code")).forEachOrdered(el -> act.addAttribute(new Attribute<>(el.getKey(),el.getValue())));

                    act.addAttribute(new Attribute<>("link", r.url()));

                    currentGroup.add(act);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else{
                if(e.matches("Students completing .*")) continue;
//                System.out.println("New group on " + e);
                d.add(currentGroup);
                currentGroup = new Group(e + " " + d.size());
                if (e.contains("all")){
                    currentGroup.setColour("red");
                }else {
                    currentGroup.setColour("yellow");
                }
            }
        }
        d.add(currentGroup);
        d.remove(0);
//        d.forEach(e -> System.out.println(e.getName()));
//        System.err.println(d.size());
        return d;
    }
}
