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
	//latitude positive is north, negative is south
	//longitude positive is east, negative is west
	private float latitude;
	private float longitude;
	
	public Coordinates() {}

	public Coordinates(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Retrieve this.latitude
	 * @pre -
	 * @post -
	 * @return Latitude as float
	 */
	public float getLatitude(){
		return this.latitude;
	}
	
	/**
	 * Retrieve this.longitude
	 * @pre -
	 * @post -
	 * @return Longitude as float
	 */
	public float getLongitude(){
		return this.longitude;
	}
}
