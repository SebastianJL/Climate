package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public interface QueryServiceAsync {

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
	void temperatureMeasurementsOfAllCitiesAtDate(Date date, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void getCities(AsyncCallback<ArrayList<String>> callback);
	void getCountries(AsyncCallback<ArrayList<String>> callback);
	void removeCity(String city, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	void removeCountry(String country, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
}
