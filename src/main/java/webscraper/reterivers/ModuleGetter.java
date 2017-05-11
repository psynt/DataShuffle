package webscraper.reterivers;

import content.Attribute;
import content.Item;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
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

import static splash.Controller.getData;

/**
 * Abstracts away the complexity of looking up details about a course
 * Created by nichita on 06/05/17.
 */
public class ModuleGetter implements Getter {


    static Data d;

    static class MyTask extends Task<Data>{

        final List<String> modules;
        static Data d;

        public MyTask(List<String> modules) {
            this.modules = modules;
        }
        public Data getData(){
            return d;
        }

        @Override
        protected Data call() throws Exception {

            System.out.println("started");
            Group currentGroup = new Group();
            currentGroup.setColour("red");
            Data d = new Data("Module");
            for (int i = 0 ; i<modules.size() ; i++ ) {
                String e = modules.get(i);
                if(e.matches("G5\\d...")){
                    try {
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
                    d.add(currentGroup);
                    currentGroup = new Group(e + " " + d.size());
                    if (e.contains("all")){
                        currentGroup.setColour("red");
                    }else {
                        currentGroup.setColour("yellow");
                    }
                }
                updateProgress(i,modules.size());
//                    System.out.println(i);
            }
//                System.out.println("returning");
            d.add(currentGroup);
            d.remove(0);
            ModuleGetter.d=d;
            return d;
//            return null;
        }
    }

    private List<String> decide(Map<String, String> args) throws MalformedURLException {
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

//        String selCourse = res.get(courses.get(0));

        if (selCourse.length()<2){
            throw new NullPointerException("User selected nothing");
        }


        CourseScraper cs = new CourseScraper(DocumentLoader.load(new URL(selCourse)));
        return cs.getReqModules();

    }

    private void getMeModules(final List<String> modules) throws InterruptedException {

//        Task<Data> getter = new Task<Data>() {
//            Data d;
//
//        };

//        getter.setOnSucceeded(e -> {
////            System.out.println(getter.getValue());
//            d= getter.getValue();
//        });

//        getter.stateProperty().addListener(e ->{
//            d = getter.getValue();
//        });

        MyTask getter = new MyTask(modules);
        Thread th = new Thread(getter);
        th.setDaemon(true);
        th.start();
//        new Thread(getter).start();
        th.join();
//        System.out.println(getter.getData());

//        th.
//
//        Data d = getter.getValue();
////        System.out.println(d);
//
//        return d;
    }


    public Task<Data> getTask(Map<String, String> args) throws MalformedURLException {
        return new MyTask(decide(args));
    }

    @Override
    public Data getTheStuff(Map<String, String> args) throws Exception {

        List<String> modules = decide(args);

        getMeModules(modules);
//        return getMeModules(modules);
//        while (d == null);
//        System.out.println(d);
        return d;
    }
}
