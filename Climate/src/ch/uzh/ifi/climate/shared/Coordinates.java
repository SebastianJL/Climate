package ch.uzh.ifi.climate.shared;

import java.io.Serializable;

/**
 * This class manages a coordinate pair (longitude+latitude)
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a coordinate pair (longitude+latitude)
 */

public class Coordinates implements Serializable{

	private float latitude;
	private float longitude;
	
	public Coordinates(){
		
	}

	//latitude positive is north, negative is south
	//longitude positive is east, negative is west
	public Coordinates(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public float getLatitude(){
		return this.latitude;
	}
	
	public float getLongitude(){
		return this.longitude;
	}
}
