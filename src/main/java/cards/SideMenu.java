package cards;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


    class SideMenu extends VBox
    {
        // function to return the button that displays or hides the side menu
        public Button getDisplayMenuButton()
        {
            return displayMenuButton;
        }
        private final Button displayMenuButton;

        // create a side menu containing a accordian menu list
   
        SideMenu(final double newWidth, Node... nodes)
        {
            getStyleClass().add("sideMenu");
            this.setPrefWidth(newWidth);
            this.setMinWidth(0);

            // panel created to hide and show.
            setAlignment(Pos.CENTER);
            getChildren().addAll(nodes);

            // button created to toggle if the side menu is displayed.
            displayMenuButton = new Button("Close");
            displayMenuButton.getStyleClass().add("hide-left");
            displayMenuButton.setRotate(270);

            // apply the animations when the button is pressed.
            displayMenuButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                	// animation to close side menu
                    final Animation hideSideMenu = new Transition()
                    {
                        {
                            setCycleDuration(Duration.millis(250));
                        }

                        @Override
                        protected void interpolate(double frac)
                        {
                            final double currentWidth = newWidth * (1.0 - frac);
                            setPrefWidth(currentWidth);
                            setTranslateY(currentWidth - newWidth);
                        }
                    };
                    hideSideMenu.onFinishedProperty().set(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            setVisible(false);
                            displayMenuButton.setText("Open");
                            displayMenuButton.getStyleClass().remove("hide-left");
                            displayMenuButton.getStyleClass().add("show-right");
                        }
                    });
                    // animation to display side menu
                    final Animation displaySideMenu = new Transition()
                    {
                        {
                            setCycleDuration(Duration.millis(250));
                        }

                        @Override
                        protected void interpolate(double frac)
                        {
                            final double currentWidth = newWidth * frac;
                            setPrefWidth(currentWidth);
                            setTranslateY(currentWidth - newWidth );
                        }
                    };
                    displaySideMenu.onFinishedProperty().set(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            displayMenuButton.setText("Collapse");
                            displayMenuButton.getStyleClass().add("hide-left");
                            displayMenuButton.getStyleClass().remove("show-right");
                        }
                    });
                    if (menuAnimationFinished(hideSideMenu, displaySideMenu))
                    {
                        if (isVisible())
                        {
                            hideSideMenu.play();
                        }
                        else
                        {
                            setVisible(true);
                            displaySideMenu.play();
                        }
                    }
                }

				private boolean menuAnimationFinished(final Animation hideSideMenu, final Animation showSideMenu) {
					return showSideMenu.statusProperty().get() == Animation.Status.STOPPED && hideSideMenu.statusProperty().get() == Animation.Status.STOPPED;
				}
            });
        }
    }
}
