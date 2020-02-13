package Cartoon;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 * This is the Satellite class. All celestial classes, Star, Planet, and Satellite have
 * unique associations according to their properties. As Satellite rotates as well as
 * revolve around the sun, it knows about its Rotate for its own rotation, and another Rotate
 * for the revolution. As Planet moves around the screen, it is impossible to setup
 * the same PathTransition for Satellites, as the path is not set to one place.
 */

public class Satellite extends Celestial implements Revolvable{

	private Sphere _satellite;
	private Planet _planet;
	private Rotate _revolution;
	private Circle _orbit;

	public Satellite(Planet planet, Rotate rotation, Rotate revolution) {
		// Association to the designated planet
		_planet = planet;
		// Instantiates the main object.
		_satellite = new Sphere();
		// Sets up the rotation
		rotation.setAxis(Rotate.Y_AXIS);
		_satellite.getTransforms().add(rotation);
		// Sets up the revolution
		_revolution = revolution;
		_revolution.setAxis(Rotate.Z_AXIS);
		_orbit = new Circle();
	}

	// Sets up the path that this satellite will follow.
	@Override
	public void setOrbit(double r) {
		_orbit.centerXProperty().bind(_planet.getPlanet().translateXProperty());
        _orbit.centerYProperty().bind(_planet.getPlanet().translateYProperty());
		_orbit.setRadius(r);
		_orbit.setFill(Color.TRANSPARENT);
	}

	// Calls in all the setting methods into one single method to be called in Cartoon.
	public void setSatellite(double r, String url, Color color, double increment) {
		this.setSettings(_satellite, r, url, color);
		this.setOrbit(r+increment);
	}

	/*
	 * Because planet moves around the sun, the satellite's transitions and movements
	 * are dynamic. It was thus impossible to use PathTransition, which runs along
	 * a certain duration to a fixed path, and using a style where single timeline is used
	 * for all graphics was not compatible with this. Therefore, it was more suitable
	 * to create a small pane that is bound to the orbit and the planet's position 
	 * on the screen, and put the satellite into that pane, and rotate that pane.
	 */
	public StackPane getBox() {
		StackPane box = new StackPane();
		box.translateXProperty().bind(_orbit.centerXProperty());
		box.translateYProperty().bind(_orbit.centerYProperty());
		double size = _orbit.getRadius()*Math.sqrt(2);
		box.setPrefSize(size,size);
		box.getChildren().add(_satellite);
		box.getTransforms().add(_revolution);
		return box;
	}
}
