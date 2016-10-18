package climate.containers;

import climate.exceptions.NotImplementedException;

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
