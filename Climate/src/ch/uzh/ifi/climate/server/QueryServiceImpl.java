package ch.uzh.ifi.climate.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.uzh.ifi.climate.client.QueryService;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class QueryServiceImpl extends RemoteServiceServlet implements QueryService {
	
	private String CSVFileName = "/Climate/src/ch/uzh/ifi/climate/data/GlobalLandTemperaturesByMajorCity_v1.csv";
	CSVParser parser = new CSVParser();
	
	@Override
	public ArrayList<TemperatureMeasurement> temperatureMeasurements(String city, Date sdate, Date edate) {
		ArrayList<TemperatureMeasurement> Data = new ArrayList<TemperatureMeasurement>();
		try {
			Data = parser.parseCSV(CSVFileName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return Data;
	}

}
