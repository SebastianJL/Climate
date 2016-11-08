package ch.uzh.ifi.climate.shared;

import java.util.Date;


/**
 * This class manages a temperature measurement (temperature+uncertainty) together with its
 * metadata (date, location, etc).
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a temperature measurement (temperature+uncertainty)
 * 				together with its metadata (date, location, etc).
 */
public class TemperatureMeasurement{
	private Temperature temperature;
	private Temperature uncertainty;
	private Date date;
	private String city;
	private String country;
	private Coordinates coordinates;
	
	public TemperatureMeasurement(Temperature temperature, Temperature uncertainty, Date date, String city, String country, Coordinates coordinates) {
		this.temperature = temperature;
		this.uncertainty = uncertainty;
		this.date = date;
		this.city = city;
		this.country = country;
		this.coordinates = coordinates;
	}
}
