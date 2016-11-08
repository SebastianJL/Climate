package ch.uzh.ifi.climate.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;


@RemoteServiceRelativePath("query")
public interface QueryService extends RemoteService {
	
	public TemperatureMeasurement[] temperatureMeasurements(String city, Date sdate, Date edate);
	
}
