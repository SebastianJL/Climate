package ch.uzh.ifi.climate.server;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.uzh.ifi.climate.client.QueryService;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class QueryServiceImpl extends RemoteServiceServlet implements QueryService {
	
	private String CSVFileName = "/Climate/src/ch/uzh/ifi/climate/data/GlobalLandTemperaturesByMajorCity_v1.csv";
	private CSVParser parser = new CSVParser();
	private ArrayList<TemperatureMeasurement> data = parser.parseCSV(CSVFileName);
	private ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
	
	/**
	 * Filters data with respect to city, startDate, endDate
	 * @pre 	filteredData != null && city != null && sdate != null && edate != null
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
				Measurement.getDate().getTime()<=edate.getTime() &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Filters data with respect to city
	 * @pre 	filteredData != null && city != null
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
	 * @pre 	filteredData != null && country != null && sdate != null && edate != null
	 * @param 	country, sdate, edate are the values for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (country between startDate and endDate)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
	 */
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country, Date sdate, Date edate) {
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCountry().equals(country) && 
				Measurement.getDate().getTime()>=sdate.getTime() && 
				Measurement.getDate().getTime()<=edate.getTime() &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * Filters data with respect to country
	 * @pre 	filteredData != null && country != null
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
}
