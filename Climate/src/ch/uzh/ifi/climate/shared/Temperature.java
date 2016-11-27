package ch.uzh.ifi.climate.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class manages a single temperature value and its transformation between units.
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a single temperature value and its transformation between units.
 */
public class Temperature implements Serializable{
	private double temperatureInKelvin;
	
	public Temperature(){
		
	}
	
	private Temperature(double temperatureInKelvin) {
		this.temperatureInKelvin = temperatureInKelvin;
	}
	
	public static Temperature createFromKelvin(double temperatureInKelvin) {
		return new Temperature(temperatureInKelvin);
	}
	
	public static Temperature createFromCelsius(float temperatureInCelcius) {
		float temperatureInKelvin = temperatureInCelcius + 273.15f;
		return new Temperature(temperatureInKelvin);
	}
	
	public double getTemperatureInKelvin(){
		return this.temperatureInKelvin;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Kelvin
	 */
	public double kelvin() {
		return this.temperatureInKelvin;
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Celsius
	 */
	public double celsius(){
		return this.temperatureInKelvin - 273.15f;
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Fahrenheit
	 */
	public double fahrenheit(){
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
		double sum;
		
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
		double difference;
		
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
	public static Temperature divide(Temperature dividend, double divisor) {
		double quotient;
		
		quotient = dividend.kelvin() / divisor;
		return Temperature.createFromKelvin(quotient);
	}
	
	/**
	 * Sums an array of Temperatures.
	 * @pre 	temps != null && temps.length != 0 
	 * 			&& temps.getClass().getComponentType().equals(Temperature.class)
	 * @post 	-
	 * @param	temps Array of Temperatures to sum.
	 * @return	sum Result of the summation as a Temperature.
	 */
	public static Temperature sum(Temperature[] temps) {
		double sum = 0;
		
		for (Temperature temp : temps) {
			sum += temp.kelvin();
		}
		return Temperature.createFromKelvin(sum);
	}

	/**
	 * Sums an ArrayList of Temperatures.
	 * @pre 	temps != null && temps.size() != 0 
	 * @post 	-
	 * @param	temps Array of Temperatures to sum.
	 * @return	sum Result of the summation as a Temperature.
	 */
	public static Temperature sum(ArrayList<Temperature> temps) {
		return Temperature.sum(temps.toArray(new Temperature[temps.size()]));
//		double sum = 0;
//		for (Temperature temp : temps) {
//			sum += temp.kelvin();
//		}
//		return Temperature.createFromKelvin(sum);
	}
}
