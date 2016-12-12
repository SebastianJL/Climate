package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;


/**
 * This is the interface for RPC's (remote procedure calls)
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class defines the interface for RPC's from client to server.
 */
@RemoteServiceRelativePath("query")
public interface QueryService extends RemoteService {
	
	/**
	 * For full documentation see RPC Implementation in src (ch.uzh.ifi.climate.server.QueryServiceImpl)
	 */
	ArrayList<TemperatureMeasurement> temperatureMeasurements(String city, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> temperatureMeasurements(String city);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCountry(String country);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate);
	ArrayList<TemperatureMeasurement> clearMeasurements();
	ArrayList<String> getCities();
	ArrayList<String> getCountries();
	ArrayList<TemperatureMeasurement> temperatureMeasurementsOfAllCitiesAtDate(Date date);
	ArrayList<TemperatureMeasurement> temperatureMeasurementsOfAllCitiesAtYear(Date date);
	ArrayList<TemperatureMeasurement> removeCity(String city);
	ArrayList<TemperatureMeasurement> removeCountry(String country);
}
