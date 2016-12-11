package ch.uzh.ifi.climate.server;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

import ch.uzh.ifi.climate.shared.Coordinates;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;
import ch.uzh.ifi.climate.server.QueryServiceImpl;

public class QueryServiceImplTest{
	//private QueryServiceImpl testQueryService = new QueryServiceImpl();
	
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
	@Test
	public void testFilterCity(){
		
	}
}
