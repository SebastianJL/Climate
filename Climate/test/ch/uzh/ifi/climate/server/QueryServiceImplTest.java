package ch.uzh.ifi.climate.server;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

import ch.uzh.ifi.climate.shared.Coordinates;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;
import ch.uzh.ifi.climate.server.QueryServiceImpl;

public class QueryServiceImplTest{
	private final TemperatureMeasurement TEST_MEASUREMENT_1 = 
			new TemperatureMeasurement(	Temperature.createFromCelsius(10.3f),
										Temperature.createFromCelsius(0.2f),
										new Date(),
										"TestCity1",
										"TestCountry1",
										new Coordinates(10.2f, -40.32f));
	private final TemperatureMeasurement TEST_MEASUREMENT_2 = 
			new TemperatureMeasurement(	Temperature.createFromCelsius(20.2f),
										Temperature.createFromCelsius(1.3f),
										new Date(),
										"TestCity2",
										"TestCountry2",
										new Coordinates(-32.6f, 52.9f));
	
	private ArrayList<TemperatureMeasurement> data = new ArrayList<TemperatureMeasurement>();
	
	public ArrayList<TemperatureMeasurement> setUpData(){
		this.data.add(TEST_MEASUREMENT_1);
		this.data.add(TEST_MEASUREMENT_2);
		return this.data;
	}
	
	@Test
	public void testFilterCity(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurements("TestCity1"),compareData);
	}
	
	@Test
	public void testFilterCountry(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsCountry("TestCountry2"),compareData);
	}
	
	@Test
	public void testFilterCityDate(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurements("TestCity1", new Date(), new Date()), compareData);
	}
	
	@Test
	public void testFilterCountryDate(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsCountry("TestCountry2", new Date(), new Date()), compareData);
	}
	
	@Test
	public void testGetCities(){
		ArrayList<String> compareData = new ArrayList<String>();
		compareData.add("TestCity1");
		compareData.add("TestCity2");
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.getCities(),compareData);
	}
	
	@Test
	public void testGetCountries(){
		ArrayList<String> compareData = new ArrayList<String>();
		compareData.add("TestCountry1");
		compareData.add("TestCountry2");
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.getCountries(),compareData);
	}
	
	@Test
	public void testFilterCityCountry(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsCityCountry("TestCountry1", "TestCity1"), compareData);
	}
	
	@Test
	public void testFilterCityCountryDate(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsCityCountry("TestCountry2", "TestCity2", new Date(), new Date()), compareData);
	}
	
	@Test
	public void testSliderDataDate(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		compareData.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsOfAllCitiesAtDate(new Date()),compareData);
	}
	
	@Test
	public void testSliderDataYear(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		compareData.add(TEST_MEASUREMENT_2);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		assertEquals(testQueryService.temperatureMeasurementsOfAllCitiesAtYear(new Date()),compareData);
	}
	
	@Test
	public void testRemoveCity(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		testQueryService.temperatureMeasurements("TestCity1");
		testQueryService.temperatureMeasurements("TestCity2");
		assertEquals(testQueryService.removeCity("TestCity2"),compareData);
	}
	
	@Test
	public void testRemoveCountry(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		compareData.add(TEST_MEASUREMENT_1);
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		testQueryService.temperatureMeasurementsCountry("TestCountry1");
		testQueryService.temperatureMeasurementsCountry("TestCountry2");
		assertEquals(testQueryService.removeCountry("TestCountry2"), compareData);
	}
	
	@Test
	public void testClearMeasurements(){
		ArrayList<TemperatureMeasurement> compareData = new ArrayList<TemperatureMeasurement>();
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		testQueryService.temperatureMeasurements("TestCity1");
		testQueryService.temperatureMeasurements("TestCity2");
		assertEquals(testQueryService.clearMeasurements(), compareData);
	}
	
	@Test
	public void testSliderDataReferenced(){
		QueryServiceImpl testQueryService = new QueryServiceImpl(setUpData());
		testQueryService.temperatureMeasurements("TestCity1");
		testQueryService.temperatureMeasurements("TestCity2");
		assert(testQueryService.referencedDataForSliderBar(new Date()).isEmpty());
	}
}