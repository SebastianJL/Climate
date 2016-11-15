package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;


@RemoteServiceRelativePath("query")
public interface QueryService extends RemoteService {
	
	ArrayList<TemperatureMeasurement> temperatureMeasurements(String city, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> temperatureMeasurements(String city);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> clearMeasurements();
	ArrayList<String> getCities();
	ArrayList<String> getCountries();
	ArrayList<TemperatureMeasurement> temperatureMeasurementsOfAllCitiesAtDate(ArrayList<String> cities, Date date);
	ArrayList<TemperatureMeasurement> removeCity(String city);
	ArrayList<TemperatureMeasurement> removeCountry(String country);
}
