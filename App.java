package Cartoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is an App class that extends from the Application to start up the program.
 * The top-level object PaneOrganizer is called, and the root pane is associated
 * with the scene of this GUI. 
 * Cartoon project is to integrate JavaFX graphical elements into creative
 * work of art and data. I chose to implement the solar system where stars,
 * planets, and satellites rotate around.
 */

public class App extends Application {

	@Override
	public void start(Stage stage){
		// Calls the top-level object.
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot());
		// Sets up the scene of this application.
		stage.setScene(scene);
		stage.setTitle("Cartoon");
		stage.show();
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}
