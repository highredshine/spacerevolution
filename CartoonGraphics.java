package Cartoon;

import java.net.URL;

/**
 * This is CartoonGraphics class. Noting the fact that all graphical
 * elements in this GUI uses image_urls, as well as the background,
 * it was useful to have a small class that has this URL checking method.
 * In case the URL does not work, in occasions where no Internet is possible,
 * or the image has been deleted, I can take other forms of action
 * to decorate the UI. This method is not only used in the spheres,
 * but also the cartoon pane to make the background.
 */

public abstract class CartoonGraphics {

	public boolean checkURLValid(String imageURL) {
		// Try and Exception method used to return different boolean.
		try {
			new URL(imageURL);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}