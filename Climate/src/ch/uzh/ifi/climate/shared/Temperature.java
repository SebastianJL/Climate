package ch.uzh.ifi.climate.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This class manages a single temperature value and its transofmation between units.
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a single temperature value and its transofmation between units.
 */
public class Temperature implements IsSerializable{
	private float temperatureInKelvin;
	
	
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
		return (this.temperatureInKelvin + 459.67f) * 5/9;
	}
}
