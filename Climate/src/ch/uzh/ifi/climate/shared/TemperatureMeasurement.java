package ch.uzh.ifi.climate.shared;

import java.util.Date;

public class TemperatureMeasurement{
	private Temperature temperature;
	private Temperature uncertainty;
	private Date date;
	private City city;
	
	public TemperatureMeasurement(Temperature temperature, Temperature uncertainty, Date date, City city) {
		this.temperature = temperature;
		this.uncertainty = uncertainty;
		this.date = date;
		this.city = city;
	}
}
