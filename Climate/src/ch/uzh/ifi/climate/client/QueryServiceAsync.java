package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

/**
 * This is the asynchronous interface for RPC's (remote procedure calls)
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class defines the asynchronous interface for RPC's from client to server.
 */
public interface QueryServiceAsync {
	
	/**
	 * For full documentation see RPC Implementation in src (ch.uzh.ifi.climate.server.QueryServiceImpl)
	 */
	void temperatureMeasurements(String city, Date sdate, Date edate,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurements(String city, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsCountry(String country, Date sdate, Date edate,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsCountry(String country, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsCityCountry(String country, String city, 
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void clearMeasurements(AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsOfAllCitiesAtDate(Date date,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void temperatureMeasurementsOfAllCitiesAtYear(Date date,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void getCities(AsyncCallback<ArrayList<String>> callback);
	void getCountries(AsyncCallback<ArrayList<String>> callback);
	void removeCity(String city, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void removeCountry(String country, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
}
