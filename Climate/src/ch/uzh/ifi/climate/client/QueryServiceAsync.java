package ch.uzh.ifi.climate.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public interface QueryServiceAsync {

	void temperatureMeasurements(String city, Date sdate, Date edate, AsyncCallback<TemperatureMeasurement[]> callback);

}
