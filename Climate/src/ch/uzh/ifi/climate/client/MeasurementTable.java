package ch.uzh.ifi.climate.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

/**
 * This class manages the measurement table in which the measurements chosen in the filter table are showed.
 * @author		Johannes Lade
 * @history 	2016-14-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				This class manages the measurement table in which the measurements are showed.
 */
public class MeasurementTable {
    FlexTable measurementFlexTable = new FlexTable();
    
    /**
     * Creats an empty MeasurementTable
     * 
     * @pre -
     * @post -
     * @return -
     * 
     */
    public void setUpMeasurementTable(){
	    // Create table for measurement data.
 		measurementFlexTable.setText(0, 0, "Date");
 		measurementFlexTable.setText(0, 1, "Average Temperature");
 		measurementFlexTable.setText(0, 2, "Average Temperature Uncertainty");
 		measurementFlexTable.setText(0, 3, "City");
 		measurementFlexTable.setText(0, 4, "Country");
 		measurementFlexTable.setText(0, 5, "Latitude");
 		measurementFlexTable.setText(0, 6, "Longitude");
 		
 		// Add styles to elements in the measurement table.
 		measurementFlexTable.addStyleName("filterTable");
 		measurementFlexTable.getRowFormatter().addStyleName(0, "filterTableHeader");
 		for (int i=0; i<7; i++) {
 			measurementFlexTable.getCellFormatter().addStyleName(0, i, "filterTableColumn");
 		}
    }
    
    /**
     * Clears the MeasurementTable
     * 
     * @pre -
     * @post -
     * @return -
     * 
     */
    public void clearMeasurementTable(){
	      int measurementRowCount = measurementFlexTable.getRowCount()-1;
	 	  for(int i = 1; i < measurementRowCount; ){
	 		  measurementFlexTable.removeRow(i);
	      }
    }
    
    public FlexTable getMeasurementTable(){
    	return this.measurementFlexTable;
    }
    
    /**
     * Adds the temperatureMeasurement from the input to the MeasurementTable.
     * 
     * @pre -
     * @post -
     * @param temperatureMeasurement TemperatureMeasurement
     * @return -
     * 
     */
    public void fillTable(TemperatureMeasurement temperatureMeasurement){
		final int measurementNumberOfColumns = 7;
		int row = measurementFlexTable.getRowCount();
		
		Float avgTemperature = new Float(Math.round(temperatureMeasurement.getTemperature().celsius()*100)/100f);
		Float uncertainty = new Float(Math.round(temperatureMeasurement.getUncertainty().celsius()*100)/100f);
		Float latitude = new Float(Math.round(temperatureMeasurement.getCoordinates().getLatitude()*100)/100f);
		Float longitude = new Float(Math.round(temperatureMeasurement.getCoordinates().getLongitude()*100)/100f);
		
		measurementFlexTable.setText(row, 0, DateTimeFormat.getFormat("dd/MM/yyyy").format(temperatureMeasurement.getDate()));
	    measurementFlexTable.setText(row, 1, avgTemperature.toString());
	    measurementFlexTable.setText(row, 2, uncertainty.toString());
	    measurementFlexTable.setText(row, 3, temperatureMeasurement.getCity());
	    measurementFlexTable.setText(row, 4, temperatureMeasurement.getCountry());
		measurementFlexTable.setText(row, 5, latitude.toString());
		measurementFlexTable.setText(row, 6, longitude.toString());
		
		for(int i = 0; i < measurementNumberOfColumns; i++){
			measurementFlexTable.getCellFormatter().addStyleName(row, i, "filterTableColumn");
		}
    }
}
