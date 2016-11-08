package ch.uzh.ifi.climate.shared;

import java.util.Date;

public class TemperatureMeasurement{
	private Temperature temperature;
	private Temperature uncertainty;
	private Date date;
	private String city;
	private String country;
	
	public TemperatureMeasurement(Temperature temperature, Temperature uncertainty, Date date, String city, String country) {
		this.temperature = temperature;
		this.uncertainty = uncertainty;
		this.date = date;
		this.city = city;
		this.country = country;
	}
}
