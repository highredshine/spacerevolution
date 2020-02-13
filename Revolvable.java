package Cartoon;

/**
 * This is the Revolvable interface used by Planet and Satellite class.
 * Planets revolve around stars, and satellites revolve around planets.
 * The interface has the mutator method for the orbit
 * that the celestial body follows. Polymorphism was applied
 * to access different types of shapes for the orbit (i.e. Satellites
 * have circular orbit in this graphics program).
 */

public interface Revolvable {

	public void setOrbit(double x);

}
