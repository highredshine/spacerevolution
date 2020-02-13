package Cartoon;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * This is the Planet class. All celestial classes, Star, Planet, and Satellite have
 * unique associations according to their properties. As Planet rotates as well as
 * revolve around the sun, it knows about its Rotate and PathTransition.
 */

public class Planet extends Celestial implements Revolvable{

	// Declares the main planet, and maximum two satellites (for Mars)
	private Sphere _planet;
	private Star _star;
	private Ellipse _orbit;
	private Rotate _rotation;
	private PathTransition _orbitPath;
	private double _speed;

	public Planet(Star star, Rotate rotation, PathTransition orbitPath) {
		// Associates this planet system into the main star.
		_star = star;
		_planet = new Sphere();
		// Self-Rotation
		_rotation = rotation;
		_rotation.setAxis(Rotate.Y_AXIS);
		_planet.getTransforms().add(_rotation);
		// Instantiate the orbit that this planet will follow through.
		_orbit = new Ellipse();
		_orbitPath = orbitPath;
		_speed = 0;
	}

	// Sets up the PathTransition into this planet's node and speed.
	public void setRevolution(double speed) {
		_speed = speed;
		_orbitPath.setDuration(Duration.seconds(_speed));
		_orbitPath.setInterpolator(Interpolator.LINEAR);
		_orbitPath.setPath(_orbit);
        _orbitPath.setNode(_planet);
	}

	// Sets up the path that this planet's PathTransition will follow.
	@Override
	public void setOrbit(double x) {
		_orbit.centerXProperty().bind(_star.getStar().translateXProperty());
        _orbit.centerYProperty().bind(_star.getStar().translateYProperty());
		_orbit.setRadiusX(_star.getStar().getTranslateX() - x);
		_orbit.setRadiusY((_star.getStar().getTranslateX()-x)/Constants.ORBIT_RATIO);
		_orbit.setStroke(Color.WHITE);
		_orbit.setFill(Color.TRANSPARENT);
		_orbit.setOpacity(0.1);
	}

	// Calls in all the setting methods into one single method to be called in Cartoon.
	public void setPlanet(double r, double o, String url, Color color, double speed) {
		this.setSettings(_planet, r, url, color);
		this.setOrbit(o);
		this.setRevolution(speed);
	}

	public Sphere getPlanet() {
		return _planet;
	}

	public Ellipse getOrbit() {
		return _orbit;
	}

}
