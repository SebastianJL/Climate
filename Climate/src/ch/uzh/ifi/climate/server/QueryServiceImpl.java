package ch.uzh.ifi.climate.server;

import java.util.ArrayList;
import java.util.Date;

//import com.google.appengine.repackaged.com.google.protobuf.TextFormat.Parser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.uzh.ifi.climate.client.QueryService;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class QueryServiceImpl extends RemoteServiceServlet implements QueryService {
	
	private String CSVFileName = "/Climate/src/ch/uzh/ifi/climate/data/GlobalLandTemperaturesByMajorCity_v1.csv";
	CSVParser parser = new CSVParser();
	ArrayList<TemperatureMeasurement> data = parser.parseCSV(CSVFileName);
	
	@Override
	public ArrayList<TemperatureMeasurement> temperatureMeasurements(String city, Date sdate, Date edate) {
		ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
		//Filtering
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCity().equals(city) && 
				Measurement.getDate().getTime()>=sdate.getTime() && 
				Measurement.getDate().getTime()<=edate.getTime()){
					filteredData.add(Measurement);
			}
		}
		return filteredData;
	}
	
	public ArrayList<TemperatureMeasurement> temperatureMeasurements(String city) {
		ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
		//Filtering
		for(TemperatureMeasurement Measurement:this.data){
			if(Measurement.getCity().equals(city)){
					filteredData.add(Measurement);
			}
		}
		return filteredData;
	}
	
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country, Date sdate, Date edate) {
		ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
		//Filtering
		for(TemperatureMeasurement Measurement:this.data){
			if(	Measurement.getCountry().equals(country) && 
				Measurement.getDate().getTime()>=sdate.getTime() && 
				Measurement.getDate().getTime()<=edate.getTime()){
					filteredData.add(Measurement);
			}
		}
		return filteredData;
	}
	
	public ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country) {
		ArrayList<TemperatureMeasurement> filteredData = new ArrayList<TemperatureMeasurement>();
		//Filtering
		for(TemperatureMeasurement Measurement:this.data){
			if(Measurement.getCountry().equals(country)){
					filteredData.add(Measurement);
			}
		}
		return filteredData;
	}
}
