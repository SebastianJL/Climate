package ch.uzh.ifi.climate.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.uzh.ifi.climate.client.QueryService;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class QueryServiceImpl extends RemoteServiceServlet implements QueryService {

	@Override
	public TemperatureMeasurement[] temperatureMeasurements(String city, Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return null;
	}

}
