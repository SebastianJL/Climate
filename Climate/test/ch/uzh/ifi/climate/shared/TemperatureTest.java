package ch.uzh.ifi.climate.shared;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TemperatureTest{
	
	@Test
	public void testCreateFromKelvin() {
		Temperature temp = Temperature.createFromKelvin(273.15f);
		assertEquals(273.15f, temp.kelvin(), 0.001);
	}

	@Test
	public void testCreateFromCelsius() {
		Temperature temp = Temperature.createFromCelsius(0);
		assertEquals(273.15f, temp.kelvin(), 0.001);
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

	@Test
	public void testAdd() {
		Temperature temp1 = Temperature.createFromKelvin(53.2);
		Temperature temp2 = Temperature.createFromKelvin(26.7);
		Temperature sum = Temperature.add(temp1, temp2);
		assertEquals(53.2+26.7, sum.kelvin(), 0);		
	}

	@Test
	public void testSubtract() {
		Temperature temp1 = Temperature.createFromKelvin(53.2);
		Temperature temp2 = Temperature.createFromKelvin(26.7);
		Temperature difference = Temperature.subtract(temp1, temp2);
		assertEquals(53.2-26.7, difference.kelvin(), 0);		
	}
	
	@Test
	public void testDivide() {
		Temperature temp = Temperature.createFromKelvin(53.2);
		double number = 3.75;
		Temperature result = Temperature.divide(temp, number);
		assertEquals(53.2/3.75, result.kelvin(), 0);
		
	}
	
	@Test
	public void testSumArray() {
		ArrayList<Temperature> temps = new ArrayList<Temperature>();
		double sumDouble = 0;
		Temperature sumTemp;
		final double[] SUMMANDS = {13, 12, 484.2, 45, 99, 111, 8};
		
		for (int i = 0; i < SUMMANDS.length; i++) {
			temps.add(Temperature.createFromKelvin(SUMMANDS[i]));
			sumDouble += SUMMANDS[i];
		}
		sumTemp = Temperature.sum(temps.toArray(new Temperature[temps.size()]));
		
		assertEquals(sumDouble, sumTemp.kelvin(), 0);
	}

	@Test
	public void testSumArrayList() {
		ArrayList<Temperature> temps = new ArrayList<Temperature>();
		double sumDouble = 0;
		Temperature sumTemp;
		final double[] SUMMANDS = {13, 12, 484.2, 45, 99, 111, 8};
		
		for (int i = 0; i < SUMMANDS.length; i++) {
			temps.add(Temperature.createFromKelvin(SUMMANDS[i]));
			sumDouble += SUMMANDS[i];
		}
		sumTemp = Temperature.sum(temps);
		
		assertEquals(sumDouble, sumTemp.kelvin(), 0);
	}

}
