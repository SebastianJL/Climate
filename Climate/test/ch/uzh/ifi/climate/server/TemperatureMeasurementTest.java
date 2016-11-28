package ch.uzh.ifi.climate.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ch.uzh.ifi.climate.shared.Coordinates;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class TemperatureMeasurementTest {
	private final Temperature TEST_TEMPERATURE = Temperature.createFromCelsius(10.5f);
	private final Temperature TEST_UNCERTAINTY = Temperature.createFromCelsius(0.4f);
	private final String TEST_CITY = "Zurich";
	private final String TEST_COUNTRY = "Switzerland";
	private final Date TEST_DATE = new Date();
	private final Coordinates TEST_COORDINATES = new Coordinates(17.3f, -36.7f);
	
	@Test
	public void testMeasurementEmpty(){
		TemperatureMeasurement measurement = new TemperatureMeasurement();
		assertNotNull(measurement);
	}
	@Test
	public void testMeasurementDefault() {
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertNotNull(measurement);
	}
	
	@Test
	public void testGetTemperature(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_TEMPERATURE, measurement.getTemperature());
	}
	
	@Test
	public void testGetUncertainty(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_UNCERTAINTY, measurement.getUncertainty());
	}
	
	@Test
	public void testGetDate(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_DATE, measurement.getDate());
	}
	
	@Test
	public void testGetCity(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_CITY, measurement.getCity());
	}
	
	@Test
	public void testGetCountry(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_COUNTRY, measurement.getCountry());
	}
	
	public void testGetCoordinates(){
		TemperatureMeasurement measurement = new TemperatureMeasurement(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_COORDINATES);
		assertEquals(TEST_COORDINATES, measurement.getCoordinates());
	}
}
