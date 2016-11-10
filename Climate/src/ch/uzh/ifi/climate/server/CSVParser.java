package ch.uzh.ifi.climate.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ch.uzh.ifi.climate.shared.Coordinates;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class CSVParser{
	
	private ArrayList<TemperatureMeasurement> Data = new ArrayList<TemperatureMeasurement>();

	public ArrayList<TemperatureMeasurement> parseCSV(String csvFileName) throws ParseException{
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
			br = new BufferedReader(new FileReader(csvFileName));
			while ((line = br.readLine()) != null) {
				
                // use comma as separator
				String[] Measurement = line.split(cvsSplitBy);
                
				if(Measurement[0].equals("dt") != true){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	Date date =  df.parse(Measurement[0]);
					
					float averageTemperature = Float.parseFloat(Measurement[1]);
					Temperature temperature = Temperature.createFromCelsius(averageTemperature);
					
					float uncertaintyTemp = Float.parseFloat(Measurement[2]);
					Temperature uncertainty = Temperature.createFromCelsius(uncertaintyTemp);
					
					String city = Measurement[3];
					
					String country = Measurement[4];
					
					float latitude = Float.parseFloat(Measurement[5].substring(0, Measurement[5].length()-1));
					float longitude = Float.parseFloat(Measurement[6].substring(0, Measurement[6].length()-1));
					if(Measurement[5].charAt(Measurement[5].length()-1) == 'S'){
						latitude = -latitude;
					}
					if(Measurement[6].charAt(Measurement[6].length()-1) == 'W'){
						longitude = -longitude;
					}
					Coordinates coordinates = new Coordinates(latitude, longitude);
					
					this.Data.add(new TemperatureMeasurement(temperature, uncertainty, date, city, country, coordinates)); 
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this.Data;
	}
	
	public ArrayList<TemperatureMeasurement> getData(){
		return this.Data;
	}
}