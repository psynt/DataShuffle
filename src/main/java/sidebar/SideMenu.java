package sidebar;

import java.util.ArrayList;

import cards.Card;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SideMenu extends VBox {
	
	// function to return the button that displays or hides the side menu
	public Button getDisplayMenuButton() {
		return displayMenuButton;
	}

	//toggle side menu button created
	final static Button displayMenuButton = new Button("Collapse");

	// create a Vbox panel to contain the side menu, set to the width passed in
	public SideMenu(final double newWidth, Node... nodes) {
		getStyleClass().add("sideMenu");//for CSS styling
		this.setPrefWidth(newWidth);
		this.setMinWidth(0);

		// Set the panel in the centre(of the right side of the borderpane) and add sidemenu items
		setAlignment(Pos.CENTER);
		getChildren().addAll(nodes);

		// menu toggle button style added and orientation changed.
		displayMenuButton.getStyleClass().add("hide-left");
		displayMenuButton.setRotate(270);
		displayMenuButton.setMinWidth(75);
		
				
	}

	// apply the animations when the button is pressed.
	final void collapseSideMenu(double newWidth, Node...sideMenu ){

		final Animation hideSideMenu = new Transition() {
			{
				setCycleDuration(Duration.millis(250));
			}

			@Override
			protected void interpolate(double frac) {
				final double currentWidth = newWidth * (1.0 - frac);
				setPrefWidth(currentWidth);
				setTranslateY(currentWidth - newWidth);
			}
		};
		hideSideMenu.onFinishedProperty().set(actionEvent -> {
			setVisible(false);
			displayMenuButton.setText("Open");
			displayMenuButton.getStyleClass().remove("hide-left");
			displayMenuButton.getStyleClass().add("show-right");
			displayMenuButton.setMinWidth(40);
		});
		// animation to display side menu
		final Animation displaySideMenu = new Transition() {
			{
				setCycleDuration(Duration.millis(250));
			}

			@Override
			protected void interpolate(double frac) {
				final double currentWidth = newWidth * frac;
				setPrefWidth(currentWidth);
				setTranslateY(currentWidth - newWidth);
			}
		};
		displaySideMenu.onFinishedProperty().set(actionEvent -> {
			displayMenuButton.setText("Collapse");
			displayMenuButton.getStyleClass().add("hide-left");
			displayMenuButton.getStyleClass().remove("show-right");
			displayMenuButton.setMinWidth(75);
		});
		if (menuAnimationFinished(hideSideMenu, displaySideMenu)) {
			if (isVisible()) {
				hideSideMenu.play();
			} else {
				setVisible(true);
				displaySideMenu.play();
			}
		}
	}
	
	private boolean menuAnimationFinished(final Animation hideSideMenu, final Animation showSideMenu) {
		return showSideMenu.statusProperty().get() == Animation.Status.STOPPED
				&& hideSideMenu.statusProperty().get() == Animation.Status.STOPPED;
	}
		 
}