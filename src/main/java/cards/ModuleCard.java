package cards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


//The 'card'
public class ModuleCard extends TabPane {

    private String moduleName ;
    private String moduleCode ;
    private String preRequisites ;
    private String numCredits ;
    private String moduleSemester ;

    private Label name = new Label();
    private Label code = new Label();
    private Label preReqs = new Label();
    private Label credits = new Label();
    private Label semester = new Label();

    public ModuleCard(String moduleName, String moduleCode, String  preRequisites, String numCredits, String moduleSemester){


        super();

        super.setPrefSize(250,200);

        this.moduleName= moduleName;
        this.moduleCode = moduleCode;
        this.preRequisites = preRequisites;
        this.numCredits = numCredits;
        this.moduleSemester = moduleSemester;

        name.setText(getModuleName());
        code.setText(getModuleCode());
        preReqs.setText(getPreRequisites());
        credits.setText(getNumCredits());
        semester.setText(getModuleSemester());

        Tab tab = new Tab();
        tab.setClosable(false);

        tab.setText(getModuleName());
        VBox tabBox = new VBox();
        tabBox.setAlignment(Pos.TOP_CENTER);
        tabBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        tabBox.getChildren().addAll(
            name,
            code,
            preReqs,
            credits,
            semester
        );

        tab.setContent(tabBox);


        super.getTabs().add(tab);

    }


    public String getModuleName(){

        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleSemester() {
        return moduleSemester;
    }

    public String getNumCredits() {
        return numCredits;
    }

    public String getPreRequisites() {
        return preRequisites;
    }




}
