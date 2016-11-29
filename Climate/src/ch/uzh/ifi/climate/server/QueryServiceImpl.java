package ch.uzh.ifi.climate.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.uzh.ifi.climate.client.QueryService;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

/**
 * This class manages the filtering of the data.
 * @author		Pascal Siemon
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				Splitting the data in useful subsets which have a size that can be
 * 				sent to the client in a acceptable time.
 */

public class QueryServiceImpl extends RemoteServiceServlet implements QueryService {
	
	private String CSVFileName = "GlobalLandTemperaturesByMajorCity_v1.csv";
	private CSVParser parser = new CSVParser();
	private ArrayList<TemperatureMeasurement> data;
	private ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
	private ArrayList<TemperatureMeasurement> sliderData = new ArrayList<TemperatureMeasurement>();
	
	
	public QueryServiceImpl() {
		try {
			data = parser.parseCSV(CSVFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Filters data with respect to city, startDate, endDate
	 * @pre 	filteredData != null && city != null && sdate != null && edate != null && data != null
	 * @param 	city, sdate, edate are the values for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (city between startDate and endDate)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
	 */
	@Override
	public ArrayList<TemperatureMeasurement> temperatureMeasurements(String city, Date sdate, Date edate) {
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCity().equals(city) && 
				Measurement.getDate().getTime()>=sdate.getTime() && 
				Measurement.getDate().getTime()<=edate.getTime()+4000000 &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Filters data with respect to city
	 * @pre 	filteredData != null && city != null && data != null
	 * @param 	city is the value for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (all measurements of one city)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurements(String city) {
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Filters data with respect to country, startDate, endDate
	 * @pre 	filteredData != null && country != null && sdate != null && edate != null && data != null
	 * @param 	country, sdate, edate are the values for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (country between startDate and endDate)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country, Date sdate, Date edate) {
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCountry().equals(country) && 
				Measurement.getDate().getTime()>=sdate.getTime() && 
				Measurement.getDate().getTime()<=edate.getTime()+4000000 &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Filters data with respect to country
	 * @pre 	filteredData != null && country != null && data != null
	 * @param 	country is the value for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (all measurements of one country)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country) {
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCountry().equals(country) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**Filters data dependent on city and country between sdate and edate
	 * @pre		filteredData != null && data != null && country != null && city != null && sdate != null && edate != null
	 * @param	defines data for city in country between sdate and edate
	 * @post	filteredData contains data for a specific city in a specific country between startDate and EndDate
	 * @return	ArrayList filteredData containing the above defined measurements
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate){
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				Measurement.getCountry().equals(country) &&
				Measurement.getDate().getTime() >= sdate.getTime() &&
				Measurement.getDate().getTime() <= edate.getTime()+4000000 &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**Filters data dependent on city and country
	 * @pre		filteredData != null && data != null && country != null && city != null
	 * @param	defines data for city in country
	 * @post	filteredData contains data for a specific city in a specific country
	 * @return	ArrayList filteredData containing the above defined measurements
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city){
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				Measurement.getCountry().equals(country) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Clears the ArrayList filteredData which is used to send the data to the client
	 * @pre 	filteredData != null
	 * @post 	ArrayList is empty
	 * @return 	Empty ArrayList of TemperatureMeasurements
	 */
	public ArrayList<TemperatureMeasurement> clearMeasurements(){
		this.filteredData.clear();
		return this.filteredData;
	}
	
	/**Gives all the names the cities stored in an ArrayList
	 * @pre		data != null
	 * @post	ArrayList cities contains all cities from the data as Strings
	 * @return	ArrayList with all names of the cities (each just once)
	 */
	public ArrayList<String> getCities(){
		ArrayList<String> cities = new ArrayList<String>();
		for(TemperatureMeasurement Measurement:this.data){
			if(!cities.contains(Measurement.getCity())){
				cities.add(Measurement.getCity());
			}
		}
		return cities;
	}
	
	/**Gives all the names the countries stored in an ArrayList
	 * @pre		data != null
	 * @post	ArrayList countries contains all countries from the data as Strings
	 * @return	ArrayList with all names of the countries (each just once)
	 */
	public ArrayList<String> getCountries(){
		ArrayList<String> countries = new ArrayList<String>();
		for(TemperatureMeasurement Measurement:this.data){
			if(!countries.contains(Measurement.getCountry())){
				countries.add(Measurement.getCountry());
			}
		}
		return countries;
	}
	
	/**Filters data for all city at a specific time
	 * @pre 	data != null && filteredData != null && date != null
	 * @param	cities contains all cities for which the data should be extracted
	 * 			date is the date for all the measurements
	 * @post	the asked data is stored in an ArrayList
	 * @return	ArrayList sliderData contains all measurements for all cities at a specific time (and nothing else)
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsOfAllCitiesAtDate(Date date){
		sliderData.clear();
		for(TemperatureMeasurement Measurement:this.data){
			if(Measurement.getDate().getTime() >= date.getTime() &&
				Measurement.getDate().getTime() <= date.getTime()+4000000){
				this.sliderData.add(Measurement);
			}
		}
		return this.sliderData;
	}
	
	/**Removes all measurements of one specific city
	 * @pre		data != null && filteredData != null && city != null
	 * @param	city for which the measurements should be removed
	 * @post	filteredData does not contain measurements of the city anymore
	 * @return	ArrayList filteredData not containing measurements of the city anymore
	 */
	public ArrayList<TemperatureMeasurement> removeCity(String city){
		for(TemperatureMeasurement Measurement:this.data){
			if(Measurement.getCity().equals(city)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**Removes all measurements of one specific county
	 * @pre		data != null && filteredData != null && country != null
	 * @param	country for which the measurements should be removed
	 * @post	filteredData does not contain measurements of the country anymore
	 * @return	ArrayList filteredData not containing measurements of the country anymore
	 */
	public ArrayList<TemperatureMeasurement> removeCountry(String country){
		for(TemperatureMeasurement Measurement:this.data){
			if(Measurement.getCountry().equals(country)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
}
