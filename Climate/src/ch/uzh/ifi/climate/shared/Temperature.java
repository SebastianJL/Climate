package ch.uzh.ifi.climate.shared;


/**
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages a single temperature value and its transofmation between units.
 */
public class Temperature {
	private float temperatureInKelvin;
	
	
	public Temperature(float temperatureInKelvin) {
		this.temperatureInKelvin = temperatureInKelvin;
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Kelvin
	 * @throws NotImplementedException 
	 */
	public float kelvin() throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Celsius
	 * @throws NotImplementedException 
	 */
	public float celsius() throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	
	/**
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Fahrenheit
	 * @throws NotImplementedException 
	 */
	public float fahrenheit() throws NotImplementedException {
		throw new NotImplementedException();
	}
}
