package ch.uzh.ifi.climate.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class TemperatureTest{
	
	private final float KELVIN_1 = 273.15f;
	private final float KELVIN_2 = 0f;
	private final float DELTA = 0f;

	
	@Test
	public void testTemperature() {
		Temperature temp = new Temperature();
		assertNotNull(temp);
	}
	
	@Test
	public void testCreateFromKelvin() {
		Temperature temp = Temperature.createFromKelvin(KELVIN_1);
		assertNotNull(temp);
	}

	@Test
	public void testCreateFromCelsius() {
		Temperature temp = Temperature.createFromCelsius(KELVIN_1);
		assertNotNull(temp);
	}

	@Test
	public void testKelvin() {
		Temperature temp1 = Temperature.createFromKelvin(KELVIN_1);
		Temperature temp2 = Temperature.createFromKelvin(KELVIN_2);
		assertEquals(KELVIN_1, temp1.kelvin(), DELTA);
		assertEquals(KELVIN_2, temp2.kelvin(), DELTA);
	}

	@Test
	public void testCelsius() {
		Temperature temp1 = Temperature.createFromKelvin(KELVIN_1);
		Temperature temp2 = Temperature.createFromKelvin(KELVIN_2);
		assertEquals(0, temp1.celsius(), DELTA);
		assertEquals(-273.15f, temp2.celsius(), DELTA);
	}

	@Test
	public void testFahrenheit() {
		Temperature temp1 = Temperature.createFromKelvin(KELVIN_1);
		Temperature temp2 = Temperature.createFromKelvin(KELVIN_2);
		assertEquals(32, temp1.fahrenheit(), DELTA+0.0001);
		assertEquals(-459.67, temp2.fahrenheit(), DELTA+0.0001);
	}


}
