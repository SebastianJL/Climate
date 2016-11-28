package ch.uzh.ifi.climate.server;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

import ch.uzh.ifi.climate.shared.Coordinates;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class FilterTest {
	private final TemperatureMeasurement TEST_MEASUREMENT_1 = new TemperatureMeasurement(Temperature.createFromCelsius(12.4f),Temperature.createFromKelvin(0.2f), new Date(), "TestCity1", "TestCountry1", new Coordinates(10.3f,-54.2f));
	private final TemperatureMeasurement TEST_MEASUREMENT_2 = new TemperatureMeasurement(Temperature.createFromCelsius(-2.3f),Temperature.createFromKelvin(0.34f), new Date(), "TestCity2", "TestCountry2", new Coordinates(-32.7f, 24.6f));
	
	@Test
	public void testFilterCity(){
		String testCity = "TestCity1";
		ArrayList<TemperatureMeasurement> data = new ArrayList<TemperatureMeasurement>();
		data.add(TEST_MEASUREMENT_1);
		data.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQuery = new QueryServiceImpl(data);
		assertEquals(true,testQuery.temperatureMeasurements(testCity).contains(TEST_MEASUREMENT_1));
		assertEquals(false, testQuery.temperatureMeasurements(testCity).contains(TEST_MEASUREMENT_2));
	}
	
	public void testFilterCityDates(){
		String testCity = "TestCity1";
		Date testStartDate = new Date();
		Date testEndDate = new Date();
		ArrayList<TemperatureMeasurement> data = new ArrayList<TemperatureMeasurement>();
		data.add(TEST_MEASUREMENT_1);
		data.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQuery = new QueryServiceImpl(data);
		assertEquals(true,testQuery.temperatureMeasurements(testCity, testStartDate, testEndDate).contains(TEST_MEASUREMENT_1));
		assertEquals(true, testQuery.temperatureMeasurements(testCity, testStartDate, testEndDate).contains(TEST_MEASUREMENT_2));
	
	}
}
