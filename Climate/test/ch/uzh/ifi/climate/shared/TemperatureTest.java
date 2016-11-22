package ch.uzh.ifi.climate.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class TemperatureTest{
	
//	@Override
//	public String getModuleName() {
//		return "ch.uzh.ifi.climate.Climate";
//	}

	@Test
	public void testCreateFromKelvin() {
		Temperature temp = Temperature.createFromKelvin(273.15f);
		assertNotNull(temp);
	}

	@Test
	public void testCreateFromCelsius() {
		Temperature temp = Temperature.createFromCelsius(0);
		assertNotNull(temp);
	}

	@Test
	public void testKelvin() {
		Temperature temp1 = Temperature.createFromKelvin(273.15f);
		Temperature temp2 = Temperature.createFromKelvin(0);
		assertEquals(273.15f, temp1.kelvin(), 0.001);
		assertEquals(0, temp2.kelvin(), 0.001);
	}

	@Test
	public void testCelsius() {
		Temperature temp1 = Temperature.createFromKelvin(273.15f);
		Temperature temp2 = Temperature.createFromKelvin(0);
		assertEquals(0, temp1.celsius(), 0.001);
		assertEquals(-273.15f, temp2.celsius(), 0.001);
	}

	@Test
	public void testFahrenheit() {
		Temperature temp1 = Temperature.createFromKelvin(273.15f);
		Temperature temp2 = Temperature.createFromKelvin(0);
		assertEquals(32, temp1.fahrenheit(), 0.001);
		assertEquals(-459.67, temp2.fahrenheit(), 0.001);
	}


}
