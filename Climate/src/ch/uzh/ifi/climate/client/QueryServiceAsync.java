package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public interface QueryServiceAsync {

	void temperatureMeasurements(String city, Date sdate, Date edate,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
	void getCities(AsyncCallback<ArrayList<String>> callback);
=======
>>>>>>> Stashed changes
	void temperatureMeasurements(String city, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	
	void temperatureMeasurementsCountry(String country, Date sdate, Date edate,
			AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	
	void temperatureMeasurementsCountry(String country, AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	
	void clearMeasurements(AsyncCallback<ArrayList<TemperatureMeasurement>> callback);
	
	void getCities(AsyncCallback<ArrayList<String>> callback);
	
<<<<<<< Updated upstream
=======
>>>>>>> origin/master
>>>>>>> Stashed changes
	void getCountries(AsyncCallback<ArrayList<String>> callback);
}
