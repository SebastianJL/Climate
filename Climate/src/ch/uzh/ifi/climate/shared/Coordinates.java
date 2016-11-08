package ch.uzh.ifi.climate.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This class manages a coordinate pair (longitude+latitude)
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a coordinate pair (longitude+latitude)
 */
public class Coordinates implements IsSerializable{
	private float longitude;
	private float latitude;


	public Coordinates(float longitude, float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
}
