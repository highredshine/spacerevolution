package Cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * This is the ULTIMATE Cartoon class. All of the managements and integration of the nodes created happens here. 
 * This is divided into sections for various methods.
 * 1. Graphics creation: Creates separate panes for different planets to hide or show. Each pane
 * 	  contains the planet, the orbit, and the satellites if exist.
 * 2. Control creation: Radio buttons to hide or show the panes, time update, and explanations
 * 3. Private classes: This program has the radio button listener, Event Handler for the timeline, 
 *    and Event Handlers for keyboard and mouse interactions, as well as the quit button listener.
 */

public class Cartoon {

	private GridPane _labelPane;
	private KeyFrame _keyframe;
	private Label _timeLabel, _speedLabel, _dataLabel;
	private Pane _cartoonPane, _mercuryPane,_venusPane, _earthPane, _marsPane;
	private PathTransition _mercuryTransition, _venusTransition, _earthTransition, _marsTransition;
	private Planet _mercury, _venus, _earth, _mars;
	private Rotate _starRotation, _planetRotation, _satRotation;
	private Rotate _sat1Revolution, _sat2Revolution;
	private Star _sun;
	private String _timeMercury, _timeVenus, _timeEarth, _timeMars;
	private Timeline _timeline;
	private int _timeToggle;

	public Cartoon(Pane cartoonPane, GridPane labelPane) {
		// This cartoon "know"s about the pane where all the graphics will be added.
		_cartoonPane = cartoonPane;
		_labelPane = labelPane;
		// Rotations
		_starRotation = new Rotate();
		_planetRotation = new Rotate();
		_satRotation = new Rotate();
		// Revolutions
		_mercuryTransition = new PathTransition();
		_venusTransition = new PathTransition();
		_earthTransition = new PathTransition();
		_marsTransition = new PathTransition();
		_sat1Revolution = new Rotate();
		_sat2Revolution = new Rotate();
		// Sets up the star
		_sun = new Star(_starRotation);
		_sun.setStar(Constants.SUN_RADIUS,Constants.SUN_X,Constants.START_Y,Constants.SUN_URL,Color.ORANGERED);
		_cartoonPane.getChildren().add(_sun.getStar());
	}
	
	/**
	 * Graphics creation section. Several methods with the same name was implemented
	 * in order to increase readability from numerous parameters. Those planets without
	 * satellites or less number of satellites will call shorter method with optional parameters omitted.
	 */
	// For Mercury and Venus: no satellites
	public Pane createPlanetPane(Planet planet, PathTransition planetRevolution,
			double r, double o, String url, Color color, double speed) {
		return this.createPlanetPane(planet, 0, planetRevolution,
				r, o, url, color, speed,
				0,"",null);
	}

	// For Earth: one satellite
	public Pane createPlanetPane(Planet planet, int numofSat, PathTransition planetRevolution,
			double r, double o, String url, Color color, double speed,
			double rSat, String urlSat, Color colorSat) {
		return this.createPlanetPane(planet, numofSat, planetRevolution,
				r, o, url, color, speed,
				rSat, urlSat, colorSat,
				0,"",null);
	}

	// The general method
	public Pane createPlanetPane(Planet planet, int numofSat, PathTransition planetRevolution,
			double r, double o, String url, Color color, double speed,
			double rSat1, String urlSat1, Color colorSat1,
			double rSat2, String urlSat2, Color colorSat2) {
		// Create the pane to add all the elements of a system
		Pane planetPane = new Pane();
		// Planet section
		planet = new Planet(_sun,_planetRotation, planetRevolution);
		planet.setPlanet(r, o, url, color, speed);
		planetPane.getChildren().addAll(planet.getOrbit(),planet.getPlanet());
		// Satellite section
		if (numofSat == 1) {
			Satellite satellite = new Satellite(planet, _satRotation, _sat1Revolution);
			satellite.setSatellite(rSat1, urlSat1, colorSat1, Constants.SATELLITE_DISTANCE_1);
			planetPane.getChildren().add(satellite.getBox());
		} else if (numofSat == 2) {
			Satellite satellite1 = new Satellite(planet, _satRotation, _sat1Revolution);
			satellite1.setSatellite(rSat1, urlSat1, colorSat1, Constants.SATELLITE_DISTANCE_1);
			Satellite satellite2 = new Satellite(planet, _satRotation, _sat2Revolution);
			satellite2.setSatellite(rSat1, urlSat1, colorSat1, Constants.SATELLITE_DISTANCE_2);
			planetPane.getChildren().addAll(satellite1.getBox(),satellite2.getBox());
		}
		_cartoonPane.getChildren().addAll(planetPane);
		return planetPane;
	}

	// A single method to instantiate all lower classes and create the graphics. Used in the PaneOrganizer.
	public void createPlanets() {
		_mercuryPane = this.createPlanetPane(_mercury, _mercuryTransition,
				Constants.MERCURY_RADIUS, Constants.MERCURY_X,
				Constants.MERCURY_URL, Color.LIGHTGREY, Constants.MERCURY_SPEED);
		_venusPane = this.createPlanetPane(_venus, _venusTransition,
				Constants.VENUS_RADIUS, Constants.VENUS_X,
				Constants.VENUS_URL, Color.DARKSALMON, Constants.VENUS_SPEED);
		_earthPane = this.createPlanetPane(_earth, 1, _earthTransition,
				Constants.EARTH_RADIUS, Constants.EARTH_X,
				Constants.EARTH_URL, Color.DEEPSKYBLUE, Constants.EARTH_SPEED,
				Constants.MOON_RADIUS, Constants.MOON_URL, Color.LIGHTGREY);
		_marsPane = this.createPlanetPane(_mars, 2, _marsTransition,
				Constants.MARS_RADIUS, Constants.MARS_X,
				Constants.MARS_URL, Color.SANDYBROWN, Constants.MARS_SPEED,
				Constants.PHOBOS_RADIUS, Constants.PHOBOS_URL, Color.DARKKHAKI,
				Constants.DEIMOS_RADIUS, Constants.DEIMOS_URL, Color.BEIGE);
	}
	
	/**
	 * Control Pane Section
	 */
	
	// Generic method to setup each cell of the grid.
	public VBox makePane(int col, int row, int colNum, int rowNum) {
		VBox pane = new VBox();
		_labelPane.add(pane, col, row, colNum, rowNum);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	// Make Radio Buttons for controlling what to show up.
	public void makeRadioButtons() {
		//Buttons
	    RadioButton mercury = new RadioButton("Mercury");
	    mercury.setStyle("-fx-text-fill: white");
	    RadioButton venus = new RadioButton("Venus");
	    venus.setStyle("-fx-text-fill: white");
	    RadioButton earth = new RadioButton("Earth");
	    earth.setStyle("-fx-text-fill: white");
	    RadioButton mars = new RadioButton("Mars");
	    mars.setStyle("-fx-text-fill: white");
	    RadioButton allPlanets = new RadioButton("All");
	    allPlanets.setStyle("-fx-text-fill: white");
	    //Setting up the ToggleGroup and UserData to access its data
	    ToggleGroup group = new ToggleGroup();
        mercury.setToggleGroup(group);
        mercury.setUserData("mercury");
        venus.setToggleGroup(group);
        venus.setUserData("venus");
        earth.setToggleGroup(group);
        earth.setUserData("earth");
        mars.setToggleGroup(group);
        mars.setUserData("mars");
        allPlanets.setToggleGroup(group);
        allPlanets.setUserData("all");
        group.selectedToggleProperty().addListener(new ToggleListener(group));
        group.selectToggle(earth);
        //Sets up the cell of the grid.
		VBox buttonPane = this.makePane(0,0,1,2);
		buttonPane.setPadding(new Insets(0,0,0,80));
		buttonPane.setSpacing(8);
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		buttonPane.getChildren().addAll(mercury, venus, earth, mars, allPlanets);
		//Important section to make other interactions like mouse and key work well.
		mercury.setFocusTraversable(false);
		venus.setFocusTraversable(false);
		earth.setFocusTraversable(false);
		mars.setFocusTraversable(false);
		allPlanets.setFocusTraversable(false);
	}

	// Sets up the cell for constant change of time based on the planets' movement
	public void makeTimeDataSection() {
		VBox timeDataPane = this.makePane(1,0,2,1);
		Label startTime = new Label("The Universe.. has begun!  01 Jan Winter 2019");
		startTime.setStyle("-fx-text-fill: white");
		_timeLabel = new Label();
		_timeLabel.setStyle("-fx-text-fill: white");
		timeDataPane.getChildren().addAll(startTime,_timeLabel);
	}

	// Sets up the explanation and change of time speed based on keyboard input.
	public void makeTimeSpeedSection() {
		VBox timeSpeedPane = this.makePane(1,1,1,1);
		Label label = new Label("You have the power to control time! \n"
				+ "Use the arrow keys to speed up or down the universe \n"
				+ "or press Space Bar if you want to stop or resume the world.");
		label.setStyle("-fx-text-fill: white");
		_speedLabel = new Label("The Current Time Speed is: 1");
		_speedLabel.setStyle("-fx-text-fill: white");
		timeSpeedPane.getChildren().addAll(label,_speedLabel);
	}

	// Sets up the explanation about creating stars (small polygons) with mouse clicks.
	public void makeClickDataSection() {
		VBox clickDataPane = this.makePane(2,1,1,1);
		Label label = new Label("You also have the power to create new stars! \n"
				+ "Click anywhere in the universe to make a new star.");
		label.setStyle("-fx-text-fill: white");
		_dataLabel = new Label();
		_dataLabel.setStyle("-fx-text-fill: white");
		clickDataPane.getChildren().addAll(label,_dataLabel);
	}

	// Quit Button
	public void makeQuitSection() {
		VBox quitPane = this.makePane(3, 0, 1, 2);
		Button button = new Button("Quit");
		button.setOnAction(new ButtonHandler());
		quitPane.getChildren().add(button);
	}

	/*
	 * Sets up the timeline. The final method to be called for this program.
	 * I used milliseconds instead of seconds to make the movements smooth as much as possible.
	 */
	public void setupTimeline() {
		_keyframe = new KeyFrame(Duration.millis(0.1),new TimeHandler());
		_timeline = new Timeline(_keyframe);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
		// the pane that has the graphics is added with the input handlers as well.
		_cartoonPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
		_cartoonPane.requestFocus();
		_cartoonPane.setFocusTraversable(true);
		_cartoonPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new MouseHandler());
	}

	/**
	 * Inner private classes of Event Handlers: RadioButton, Timeline, Key, Mouse, and Quit
	 */
	// Radio Buttons
	private class ToggleListener implements ChangeListener<Toggle> {

		private ToggleGroup _group;

		// The button listener knows about the toggle group that contains the buttons.
		private ToggleListener(ToggleGroup group) {
			_group = group;
		}

		// Used switch methods to call different methods for different planets
		// The variable timeToggle is used in the timehandler to check which label data to show.
		@Override
		public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
            String name = _group.getSelectedToggle().getUserData().toString();
            switch(name) {
            case "mercury":
            	this.hideChildren(_mercuryPane);
            	// To avoid NullPointerException
            	_timeToggle = 0;
            	break;
			case "venus":
				this.hideChildren(_venusPane);
    			_timeToggle = 1;
				break;
			case "earth": default:
				this.hideChildren(_earthPane);
    			_timeToggle = 2;
				break;
			case "mars":
				this.hideChildren(_marsPane);
    			_timeToggle = 3;
				break;
			case "all":
				this.showChildren();
    			_timeToggle = 4;
            }
        }
		
		// Method used in this private class to hide all other planets except the chosen one.
		public void hideChildren(Pane planetPane) {
			int i = _cartoonPane.getChildren().size()-1;
			while (i > 0) {
				if (_cartoonPane.getChildren().get(i) == planetPane){
					_cartoonPane.getChildren().get(i).setOpacity(1);
				} else if (_cartoonPane.getChildren().get(i) instanceof Pane){
					_cartoonPane.getChildren().get(i).setOpacity(0);
				}
				i--;
			}
		}

		// Method used in this private class to show all children when All radio button is clicked.
		public void showChildren() {
			int i = _cartoonPane.getChildren().size()-1;
			while (i > 0) {
				_cartoonPane.getChildren().get(i).setOpacity(1);
				i--;
			}
		}
	}

	// Time Handler Section
	private class TimeHandler implements EventHandler<ActionEvent> {

		private double  _starAngle, _planetAngle, _satAngle, _sat1RevAngle, _sat2RevAngle;
		private int _monthCheckerMercury, _monthMercury, _yearMercury, _monthCheckerVenus, _monthVenus, _yearVenus;
		private int _monthCheckerEarth, _monthEarth, _yearEarth, _monthCheckerMars, _monthMars, _yearMars;

		public TimeHandler() {
			// Rotation Section
			_starAngle = 0;
			_planetAngle = 0;
			_satAngle = 0;
			// Revolution Section
			_sat1RevAngle = 0;
			_sat2RevAngle = 0;
			// Time Data Section
			_monthMercury = 0;
			_monthCheckerMercury = 0;
			_yearMercury = Constants.START_YEAR;
			_monthVenus = 0;
			_monthCheckerVenus = 0;
			_yearVenus = Constants.START_YEAR;
			_monthEarth = 0;
			_monthCheckerEarth = 0;
			_yearEarth = Constants.START_YEAR;
			_monthMars = 0;
			_monthCheckerMars = 0;
			_yearMars = Constants.START_YEAR;
			
		}

		@Override
		public void handle(ActionEvent event) {
			/*
			 * Rotation Section
			 */
			_starAngle = _starAngle + Constants.ROTATE_STAR_SPEED;
			_starRotation.setAngle(_starAngle);
			_planetAngle = _planetAngle + Constants.ROTATE_PLANET_SPEED;
			_planetRotation.setAngle(_planetAngle);
			_satAngle = _satAngle + Constants.ROTATE_SATELLITE_SPEED;
			_satRotation.setAngle(_satAngle);
			/*
			 * Revolution Section
			 */
			_mercuryTransition.play();
			_venusTransition.play();
			_earthTransition.play();
			// moon
			_sat1RevAngle = _sat1RevAngle + Constants.REVOLUTION_SATELLITE_SPEED;
			_sat1Revolution.setAngle(_sat1RevAngle);
			_marsTransition.play();
			// Mars moons
			_sat1RevAngle = _sat1RevAngle + Constants.REVOLUTION_SATELLITE_SPEED;
			_sat1Revolution.setAngle(_sat1RevAngle);
			_sat2RevAngle = _sat2RevAngle + Constants.REVOLUTION_SATELLITE_SPEED;
			_sat2Revolution.setAngle(_sat2RevAngle);
			/*
			 * Time Change Section
			 */
			Calculator calculator = new Calculator();
			//Mercury (Code explained; other planets have the same algorithm with different variables)
			/*
			 * Obtains the current time(=position of the planet), and divide that into the total cycle
			 * length to get the fraction data. Multiply that to 360 to obtain the day data.
			 */
			int dayMercury = (int) (_mercuryTransition.getCurrentTime().toSeconds()
					/_mercuryTransition.getTotalDuration().toSeconds()*360);
			// Month checker is used to check if the year has changed or not.
			_monthCheckerMercury = _monthMercury%12;
			// Month number from day number. -1 was necessary to make modulo and year check work.
			_monthMercury = calculator.updateMonth(dayMercury)-1;
			// Check if year has passed or not using modulo
			if ( _monthCheckerMercury == 11 & _monthMercury == 0) {
				_yearMercury = _yearMercury + 1;
			}
			// If day is a single-digit quantity, put 0 in front of it.
			String showDayMercury = String.valueOf(dayMercury%30);
			if (dayMercury%30 < 10) {
				showDayMercury = "0" + showDayMercury;
			}
			// Update the whole string.
			_timeMercury = showDayMercury + " " + calculator.getMonth(_monthMercury) + " " 
					+ calculator.mapSeason(_monthMercury+1) + " " + String.valueOf(_yearMercury);
			//Venus
			int dayVenus = (int) (_venusTransition.getCurrentTime().toSeconds()
					/_venusTransition.getTotalDuration().toSeconds()*360);
			_monthCheckerVenus = _monthVenus%12;
			_monthVenus = calculator.updateMonth(dayVenus)-1;
			if ( _monthCheckerVenus == 11 & _monthVenus == 0) {
				_yearVenus = _yearVenus + 1;
			}
			String showDayVenus = String.valueOf(dayVenus%30);
			if (dayVenus%30 < 10) {
				showDayVenus = "0" + showDayVenus;
			}
			_timeVenus = showDayVenus + " " + calculator.getMonth(_monthVenus) + " " 
					+ calculator.mapSeason(_monthVenus+1) + " " + String.valueOf(_yearVenus);
			//Earth
			int dayEarth = (int) (_earthTransition.getCurrentTime().toSeconds()
					/_earthTransition.getTotalDuration().toSeconds()*360);
			_monthCheckerEarth = _monthEarth%12;
			_monthEarth = calculator.updateMonth(dayEarth)-1;
			if ( _monthCheckerEarth == 11 & _monthEarth == 0) {
				_yearEarth = _yearEarth + 1;
			}
			String showDayEarth = String.valueOf(dayEarth%30);
			if (dayEarth%30 < 10) {
				showDayEarth = "0" + showDayEarth;
			}
			_timeEarth = showDayEarth + " " + calculator.getMonth(_monthEarth) + " " 
					+ calculator.mapSeason(_monthEarth+1) + " " + String.valueOf(_yearEarth);
			//Mars
			int dayMars = (int) (_marsTransition.getCurrentTime().toSeconds()
					/_marsTransition.getTotalDuration().toSeconds()*360);
			_monthCheckerMars = _monthMars%12;
			_monthMars = calculator.updateMonth(dayMars)-1;
			if ( _monthCheckerMars == 11 & _monthMars == 0) {
				_yearMars = _yearMars + 1;
			}
			String showDayMars = String.valueOf(dayMars%30);
			if (dayMars%30 < 10) {
				showDayMars = "0" + showDayMars;
			}
			_timeMars = showDayMars + " " + calculator.getMonth(_monthMars) + " " 
			+ calculator.mapSeason(_monthMars+1) + " " + String.valueOf(_yearMars);
			// Using _timeToggle updated from the radio button listener, update the text accordingly.
			switch(_timeToggle) {
            case 0:
    			_timeLabel.setText("At your planet Mercury, the current time is " + _timeMercury);
            	break;
			case 1:
    			_timeLabel.setText("At your planet Venus, the current time is " + _timeVenus);
				break;
			case 2: default:
    			_timeLabel.setText("At your planet Earth, the current time is " + _timeEarth);
				break;
			case 3:
    			_timeLabel.setText("At your planet Mars, the current time is " + _timeMars);
				break;
			case 4:
    			_timeLabel.setText("At your planet Earth, our home, the current time is " + _timeEarth);

            }
		}
	}

	// Keyboard
	private class KeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
			KeyCode keyPressed = e.getCode();
			double increment = 0.1;
			switch(keyPressed) {
			// Pause or Resume the animation
			case SPACE:
				if (_timeline.getStatus() == Status.PAUSED) {
					_timeline.play();
					_mercuryTransition.play();
					_venusTransition.play();
					_earthTransition.play();
					_marsTransition.play();
				} else if (_timeline.getStatus() == Status.RUNNING) {
					_timeline.pause();
					_mercuryTransition.pause();
					_venusTransition.pause();
					_earthTransition.pause();
					_marsTransition.pause();
				}
				break;
			// Slow down the animation
			case LEFT:
				_timeline.setRate(_timeline.getRate()-increment);
				double rateL = _timeline.getRate();
				String speedL = String.valueOf(rateL);
				if (-0.1 <rateL & rateL < 0.1) {
					rateL = 0;
					speedL = "0";
				}
				_mercuryTransition.setRate(rateL);
				_venusTransition.setRate(rateL);
				_earthTransition.setRate(rateL);
				_marsTransition.setRate(rateL);
				// Cut down the double length into readable number.
				if (speedL.length() > 3) {
					if (rateL > 0) {
						speedL = speedL.substring(0, 3);
					} else if (rateL < 0) {
						speedL = speedL.substring(0, 4);
					}
				}
				_speedLabel.setText("The Current Time Speed is: " + speedL);
				break;
			// Speed up the animation.
			case RIGHT:
				_timeline.setRate(_timeline.getRate()+increment);
				double rateR = _timeline.getRate();
				_mercuryTransition.setRate(rateR);
				_venusTransition.setRate(rateR);
				_earthTransition.setRate(rateR);
				_marsTransition.setRate(rateR);
				String speedR = String.valueOf(rateR);
				if (-0.1 <rateR & rateR < 0.1) {
					speedR = "0";
				}
				// Cut down the double length into readable number
				if (speedR.length() > 3) {
					if (rateR > 0) {
						speedR = speedR.substring(0, 3);
					} else if (rateR < 0) {
						speedR = speedR.substring(0, 4);
					}
				}
				_speedLabel.setText("The Current Time Speed is: " + speedR);
				break;
			default:
				break;
			}
			e.consume();

		}
	}

	// Mouse interaction
	private class MouseHandler implements EventHandler<MouseEvent> {

		// Star is made every time the cartoonPane is clicked, using the position of the click.
		@Override
		public void handle(MouseEvent e) {
			if (e.getX() < Constants.CARTOON_PANE_PREF_WIDTH & e.getY() < Constants.CARTOON_PANE_PREF_HEIGHT) {
				_dataLabel.setText("Location of the new star: " + String.valueOf(e.getX())+ ", " + String.valueOf(e.getY()));
				Polygon star = new Polygon();
				// Star shape is created using the points. 
				star.getPoints().addAll(new Double[]{
						10.0, 1.0,
						11.0, 6.0,
						14.0, 6.0,
						12.0, 8.0,
						13.0, 11.0,
						5.0, 9.0,
						7.0, 11.0,
						8.0, 8.0,
						6.0, 6.0,
						9.0, 6.0});
				star.setFill(Color.WHITE);
				star.setTranslateX(e.getX());
				star.setTranslateY(e.getY());
				// Star has the glowing effect on it.
				star.setEffect(new Glow(10));
		        _cartoonPane.getChildren().add(star);
			}
			e.consume();
		}
	}

	// Quit Button setup
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	}
}
