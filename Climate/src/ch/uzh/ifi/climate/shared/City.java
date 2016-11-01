package ch.uzh.ifi.climate.shared;

import java.util.ArrayList;
import java.util.Date;


public class City {
	private String name;
	private Coordinates coordinates; 
	private ArrayList<TemperatureMeasurement> averageTemperatures = new ArrayList<TemperatureMeasurement>();
	
	
	public City(String name, float longitude, float latitude) {
		this.name = name;
		this.coordinates = new Coordinates(longitude, latitude);	
	}
	
	
}
