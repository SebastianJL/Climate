package ch.uzh.ifi.climate.shared;

import java.io.Serializable;

/**
 * This class manages a single temperature value and its transformation between units.
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a single temperature value and its transformation between units.
 */
public class Temperature implements Serializable{
	private float temperatureInKelvin;
	
	public Temperature(){
		
	}
	
	private Temperature(float temperatureInKelvin) {
		this.temperatureInKelvin = temperatureInKelvin;
	}
	
	public static Temperature createFromKelvin(float temperatureInKelvin) {
		return new Temperature(temperatureInKelvin);
	}
	
	public static Temperature createFromCelsius(float temperatureInCelcius) {
		float temperatureInKelvin = temperatureInCelcius + 273.15f;
		return new Temperature(temperatureInKelvin);
	}
	
	public float getTemperatureInKelvin(){
		return this.temperatureInKelvin;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Kelvin
	 */
	public float kelvin() {
		return this.temperatureInKelvin;
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Celsius
	 */
	public float celsius(){
		return this.temperatureInKelvin - 273.15f;
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Fahrenheit
	 */
	public float fahrenheit(){
		return (this.temperatureInKelvin * 9/5) - 459.67f;
	}
	
	/**
	 * Adds two temperatures
	 * @pre 	summandOne != null && summandTwo != null
	 * @post 	-
	 * @param 	summandOne First Temperature to add.
	 * 			summandTwo Second Temperaure to add
	 * @return 	sum Added Temperature
	 */
	public static Temperature add(Temperature summandOne, Temperature summandTwo) {
		float sum;
		
		sum = summandOne.kelvin() + summandTwo.kelvin();
		return Temperature.createFromKelvin(sum);
	}

	
	/**
	 * Subtracts one temperature from another.
	 * @pre 	minuend != null && subtrahend != null
	 * @post 	-
	 * @param 	minuend Temperature to subtract from.
	 * 			subtrahend Temperature to subtract.
	 * @return 	difference Subtracted Temperature
	 */
	public static Temperature subtract(Temperature minuend, Temperature subtrahend) {
		float difference;
		
		difference = minuend.kelvin() - subtrahend.kelvin();
		return Temperature.createFromKelvin(difference);
	}

	/**
	 * Divides a Temperature by a real number.
	 * @pre 	temp != null && tempTwo != null
	 * @post 	-
	 * @param	dividend Temperature to divide.
	 * 			divisor Number to divde by.
	 * @return	quotient Result of the division.
	 */
	public static Temperature subtract(Temperature dividend, float divisor) {
		float quotient;
		
		quotient = dividend.kelvin() - divisor;
		return Temperature.createFromKelvin(quotient);
	}
}
