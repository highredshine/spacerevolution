package Cartoon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

/**
 * Celestial class is a superclass for Star, Planet, and Satellite classes. 
 * All celestial bodies represented in the graphics has be set to its radius
 * and image url (or color if the url does not work), so it was more efficient
 * to create a superclass with codes made before used by various subclasses.
 */

public abstract class Celestial extends CartoonGraphics{

	public void setSettings(Sphere celestial, double r, String url, Color color) {
		// Radius
		celestial.setRadius(r);
		// Image File Rendering
		PhongMaterial mat = new PhongMaterial();
		if (this.checkURLValid(url)) {
			mat.setDiffuseMap(new Image(url));
		} else {
			mat.setDiffuseColor(color);
		}
		celestial.setMaterial(mat);
	}
}
