package Cartoon;

import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 * This is the Star class. All celestial classes, Star, Planet, and Satellite have
 * unique associations according to their properties. As Star does not revolve
 * around any other celestial bodies, Star only knows about its own Rotate.
 */

public class Star extends Celestial{

	private Sphere _star;

	public Star(Rotate rotation) {
		// sets up the node and associates it with the rotation
		_star = new Sphere();
		rotation.setAxis(Rotate.Y_AXIS);
		_star.getTransforms().add(rotation);
	}

	// setting up the star sphere, using superclass method and positioning
	public void setStar(double r, double x, double y, String url, Color color) {
		this.setSettings(_star, r, url, color);
		_star.setTranslateX(x);
		_star.setTranslateY(y);
	}
	
	public Sphere getStar() {
		return _star;
	}
}
