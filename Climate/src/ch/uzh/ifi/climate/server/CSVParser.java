package ch.uzh.ifi.climate.server;

import java.io.BufferedReader;
import java.io.File;
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

/**
 * This class handles the parsing of the CSV file.
 * @author		Pascal Siemon
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				Read the CSV file and create objects for every measurement which have values
 * 				that can be properly used afterwards.
 */

public class CSVParser{
	
	private ArrayList<TemperatureMeasurement> Data = new ArrayList<TemperatureMeasurement>();
	
	/**
	 * Reads the csv file and creates all necessary objects which are afterwards stored in the ArrayList Data
	 * @pre		csvFileName != null && Data != null
	 * @param 	csvFileName is the name of the file that should be read
	 * @post	data from the csv file is stored in the returned ArrayList
	 * @return	ArrayList that contains all the measurements from the csv file
	 * @throws	FileNotFoundException if the file with name csvFileName is not found
	 * @throws	IOException If an IO error occurs
	 * @throws	ParseException If a parse error occurs (for the parsed Date and float types)
	 */
	public ArrayList<TemperatureMeasurement> parseCSV(String csvFileName){
		
		//initialization
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
			br = new BufferedReader(new FileReader(csvFileName));
			while ((line = br.readLine()) != null) {
				
                //use comma as separator
				String[] Measurement = line.split(cvsSplitBy);
                
				//creates temperatureMeasurement objects beginning at the second line of the file
				if(Measurement[0].equals("dt") != true){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	Date date =  df.parse(Measurement[0]);
					float averageTemperature = Float.parseFloat(Measurement[1]);
					float uncertaintyTemp = Float.parseFloat(Measurement[2]);
					String city = Measurement[3];
					String country = Measurement[4];
					Temperature temperature = Temperature.createFromCelsius(averageTemperature);
					Temperature uncertainty = Temperature.createFromKelvin(uncertaintyTemp);
					
					//unifies coordinates (south = -north and west = -east)
					float latitude = Float.parseFloat(Measurement[5].substring(0, Measurement[5].length()-1));
					float longitude = Float.parseFloat(Measurement[6].substring(0, Measurement[6].length()-1));
					if(Measurement[5].charAt(Measurement[5].length()-1) == 'S') latitude = -latitude;
					if(Measurement[6].charAt(Measurement[6].length()-1) == 'W') longitude = -longitude;
					
					Coordinates coordinates = new Coordinates(latitude, longitude);
					
					//creates measurements and adds them to the ArrayList which will be returned at the end
					this.Data.add(new TemperatureMeasurement(temperature, uncertainty, date, city, country, coordinates)); 
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		return Data;
	}
}