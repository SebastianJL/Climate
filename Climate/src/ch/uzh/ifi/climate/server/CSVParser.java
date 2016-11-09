package ch.uzh.ifi.climate.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

public class CSVParser{

	public static ArrayList<TemperatureMeasurement> parseCSV(String csvFileName){
		
		ArrayList<TemperatureMeasurement> dataStorage = new ArrayList<TemperatureMeasurement>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
			br = new BufferedReader(new FileReader(csvFileName));
			while ((line = br.readLine()) != null) {
				
                // use comma as separator
				String[] Measurement = line.split(cvsSplitBy);
                
				if(Measurement[0].equals("dt") != true){
					/*float test = Float.parseFloat(Measurement[1]);
					System.out.println(test);
					String x = Measurement[5].substring(0, Measurement[5].length()-1);
					float test2 = Float.parseFloat(x);
					System.out.println(test2);*/
				}
				/*System.out.println(	"Date = " + Measurement[0] + 
									", AverageTemperature = " + Measurement[1] + 
									", Uncertainty = " + Measurement[2] + 
									", City = " + Measurement[3] + 
									", Country = " + Measurement[4] +
									", Latitude = " + Measurement[5] + 
									", Longitude = " + Measurement[6]);*/
				//System.out.print(Measurement[5].charAt(Measurement[5].length()-1) + "  ");
				//System.out.println(Measurement[6].charAt(Measurement[6].length()-1));
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
		return dataStorage;
	}
}