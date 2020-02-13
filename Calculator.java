package Cartoon;

/**
 * This is a calculator class. Calculator class has several methods to
 * micro-manage the data received from the PathTransition of the planets.
 * This mainly has methods for updating the month with the input of date,
 * mapping the month and season string with the input of month number.
 */

public class Calculator {

	/*
	 * This method is an helper method used for updating the month.
	 * If the date is between a certain interval, that designates to
	 * the certain month.
	 */
	public boolean between(int x, int lower, int upper) {
		if (lower <= x & x <= upper) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This method utilizes the switch conditional method to update month variable.
	 * For each interval of 30 from 0 to 360 (using 365 with different number of days
	 * for month had to be simplified), the number of month is updated.
	 */
	public int updateMonth(int day) {
		int month = 0;
		if (between(day,0,30)) {
			month = 1;
		} else if (between(day,31,60)) {
			month = 2;
		} else if (between(day,61,90)) {
			month = 3;
		} else if (between(day,91,120)) {
			month = 4;
		} else if (between(day,121,150)) {
			month = 5;
		} else if (between(day,151,180)) {
			month = 6;
		} else if (between(day,181,210)) {
			month = 7;
		} else if (between(day,211,240)) {
			month = 8;
		} else if (between(day,241,270)) {
			month = 9;
		} else if (between(day,271,300)) {
			month = 10;
		} else if (between(day,301,330)) {
			month = 11;
		} else if (between(day,330,360)) {
			month = 12;
		}
		return month;
	}
	
	/*
	 * With the input of the month number, the string that is designated to the number,
	 * i.e. Jan for 1, is returned.
	 */
	public String getMonth(int mon) {
		String month = null;
		switch(mon%12) {
		case 0:
			month = "Jan";
			break;
		case 1:
			month = "Feb";
			break;
		case 2:
			month = "Mar";
			break;
		case 3:
			month = "Apr";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "Jun";
			break;
		case 6:
			month = "Jul";
			break;
		case 7:
			month = "Aug";
			break;
		case 8:
			month = "Sep";
			break;
		case 9:
			month = "Oct";
			break;
		case 10:
			month = "Nov";
			break;
		case 11:
			month = "Dec";
			break;
		default:
			break;
		}
		return month;
	}

	/*
	 * For each interval of three months, the season is updated
	 * in a similar way.
	 */
	public String mapSeason(int month) {
		String season = "";
		switch(month) {
		case 1: case 2: case 12:
			season = "Winter";
			break;
		case 3: case 4: case 5:
			season = "Spring";
			break;
		case 6: case 7: case 8:
			season = "Summer";
			break;
		case 9: case 10: case 11:
			season = "Fall";
			break;
		case 0: default:
			break;
		}
		return season;
	}
}
