package Cartoon;

/**
 * This is the Constants class. A lot of constant values are used in this graphics
 * to enable smooth work of movements and to be looked organized.
 * Constants are categorized into similar usages as below.
 */

public class Constants {
	
	//Screen Size Section
    public static final double CARTOON_PANE_PREF_WIDTH = 1280;
    public static final double CARTOON_PANE_PREF_HEIGHT = 640;
	public static final int INFO_PANE_PREF_WIDTH = 1280;
	public static final int INFO_PANE_PREF_HEIGHT = 150;

	//Celestial Bodies Radius
	public static final double SUN_RADIUS = 70;
	public static final double MERCURY_RADIUS = 10;
	public static final double VENUS_RADIUS = 25;
	public static final double EARTH_RADIUS = 30;
	public static final double MARS_RADIUS = 20;
	public static final double MOON_RADIUS = 10;
	public static final double PHOBOS_RADIUS = 7;
	public static final double DEIMOS_RADIUS = 5;

	//Celestial Bodies Positions
	public static final double SUN_X = CARTOON_PANE_PREF_WIDTH/2;
	public static final double MERCURY_X = CARTOON_PANE_PREF_WIDTH/3;
	public static final double VENUS_X = CARTOON_PANE_PREF_WIDTH/4;
	public static final double EARTH_X = CARTOON_PANE_PREF_WIDTH/7;
	public static final double MARS_X = CARTOON_PANE_PREF_WIDTH/23;
	public static final double START_Y = CARTOON_PANE_PREF_HEIGHT/2;
	public static final double SATELLITE_DISTANCE_1 = 40;
	public static final double SATELLITE_DISTANCE_2 = 55;

	//Image URLs
	public static final String BACKGROUND = "https://upload.wikimedia.org/wikipedia/commons/6/60/ESO_-_Milky_Way.jpg";
	public static final String EARTH_URL = "http://planetmaker.wthr.us/img/earth_gebco8_texture_8192x4096.jpg";
	public static final String SUN_URL = "https://3c1703fe8d.site.internapcdn.net/newman/gfx/news/hires/2012/2-stereoreache.jpg";
	public static final String MARS_URL = "https://upload.wikimedia.org/wikipedia/commons/1/1c/Picture_taken_by_the_High_Resolution_Stereo_Camera_%28HRSC%29_onboard_ESA%27s_Mars_Express_orbiter_on_14_January_2004_ESA206444.jpg";
	public static final String MERCURY_URL = "https://c1.staticflickr.com/7/6238/6283633049_d7ca6d7561_b.jpg";
	public static final String VENUS_URL = "https://www.jpl.nasa.gov/spaceimages/images/largesize/PIA00256_hires.jpg";
	public static final String MOON_URL = "https://mars.nasa.gov/mer/gallery/press/spirit/20050419a/Spirit_450_prog_base-A461R1.jpg";
	public static final String PHOBOS_URL = "https://upload.wikimedia.org/wikipedia/commons/5/5c/Phobos_colour_2008.jpg";
	public static final String DEIMOS_URL = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Deimos-MRO.jpg";

	//Rotation Data
	public static final double ROTATE_STAR_SPEED = 0.003;
	public static final double ROTATE_PLANET_SPEED = 0.01;
	public static final double ROTATE_SATELLITE_SPEED = 0.005;
	
	//Revolution Data
	public static final double ORBIT_RATIO = 2.3;
	public static final double MERCURY_SPEED = 7;
	public static final double VENUS_SPEED = 12;
	public static final double EARTH_SPEED = 15;
	public static final double MARS_SPEED = 30;
	public static final double REVOLUTION_SATELLITE_SPEED = 0.01;
	
	//Time Data
	public static final int START_YEAR = 2019;
}
