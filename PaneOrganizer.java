package Cartoon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * This is the PaneOrganizer class, the top-level object of this package. If the Cartoon
 * class creates, manages, and integrates all the different elements of the program,
 * this class calls all these elements into one screen. 
 */

public class PaneOrganizer extends CartoonGraphics{

	private BorderPane _root;

	public PaneOrganizer() {
		// root
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: black");
		// Panes
		Pane cartoonPane = this.createCartoonPane();
		GridPane labelPane = this.createLabelPane();
		// Cartoon
		Cartoon cartoon = new Cartoon(cartoonPane, labelPane);
		cartoon.createPlanets();
		cartoon.makeRadioButtons();
		cartoon.makeTimeDataSection();
		cartoon.makeTimeSpeedSection();
		cartoon.makeClickDataSection();
		cartoon.makeQuitSection();
		
		// READY, SET, GO!
		cartoon.setupTimeline();
	}

	// Pane for the graphics to show up.
	public Pane createCartoonPane() {
		Pane cartoonPane = new Pane();
		cartoonPane.setPrefSize(Constants.CARTOON_PANE_PREF_WIDTH, Constants.CARTOON_PANE_PREF_HEIGHT);
		String imageURL = Constants.BACKGROUND;
		if (this.checkURLValid(imageURL)) {
			cartoonPane.setStyle("-fx-background-image: url('"+imageURL+"');"
					+ "-fx-background-size:100%;"
					+ "-fx-background-position: center;"
					+ "-fx-background-repeat: no-repeat");
		}
		else {
			cartoonPane.setStyle("-fx-background-color: black");
		}
		_root.setTop(cartoonPane);
		return cartoonPane;

	}

	// Pane for various types of labels and controls. The GridPane is set up to fit the screen.
	public GridPane createLabelPane() {
		GridPane labelPane = new GridPane();
		labelPane.setPrefSize(Constants.INFO_PANE_PREF_WIDTH, Constants.INFO_PANE_PREF_HEIGHT);
		labelPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
	    labelPane.setPadding(new Insets(0, 5, 0, 5));
	    labelPane.setAlignment(Pos.CENTER);
	    //Auto-sizing grid proportionally to the screen
	    ColumnConstraints col1 = new ColumnConstraints();
	    ColumnConstraints col2 = new ColumnConstraints();
	    ColumnConstraints col3 = new ColumnConstraints();
	    ColumnConstraints col4 = new ColumnConstraints();
	    col1.setPercentWidth(15);
	    col2.setPercentWidth(45);
	    col3.setPercentWidth(35);
	    col4.setPercentWidth(5);
	    RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(40);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(60);
	    labelPane.getColumnConstraints().addAll(col1,col2,col3,col4);
	    labelPane.getRowConstraints().addAll(row1,row2);
		_root.setBottom(labelPane);
		return labelPane;
	}

	public Parent getRoot() {
		return _root;
	}

	public void setWindow(Stage stage) {
		
	}
}
