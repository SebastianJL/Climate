package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import ch.uzh.ifi.climate.shared.TemperatureMeasurement;




/**
 * This class manages the user interface for the table view.
 * @author		Johannes Lade
 * @history 	2016-25-11 JL First version
 * @version 	2016-25-11 JL 0.1.0
 * @responsibilities
 * 				Keeps track of all panels, widgets and of the functionality of the table view.
 */
public class TablePanel extends VerticalPanel{
	private FilterTable filterTable = new FilterTable();
	private MeasurementTable measurementTable = new MeasurementTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private SuggestBox newSuggestBoxCity = new SuggestBox();
	private SuggestBox newSuggestBoxCountry = new SuggestBox();
	private IntegerBox integerBoxStartYear = new IntegerBox();
	private IntegerBox integerBoxEndYear = new IntegerBox();
	private ListBox startMonth = new ListBox();
	private ListBox endMonth = new ListBox();
	private Button addFilterButton = new Button("Add");
	private QueryServiceAsync querySvc = GWT.create(QueryService.class);
	MultiWordSuggestOracle cityNames = new MultiWordSuggestOracle();
	MultiWordSuggestOracle countryNames = new MultiWordSuggestOracle();
	private final String[] MONTHS = {"January","February","March","April","May","June",
	                           "July","August","September","October","November","December"};

	
	public TablePanel() {
		initialize();
	}
	

	
	public void initialize() {
		filterTable.setUpFilterTable();
		measurementTable.setUpMeasurementTable();
		
		//Clear previous filtered data
		if(querySvc == null){
			querySvc = GWT.create(QueryService.class);
		}
		AsyncCallback<ArrayList<TemperatureMeasurement>> clearCallback = new AsyncCallback<ArrayList<TemperatureMeasurement>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<TemperatureMeasurement> result) {
				// TODO Auto-generated method stub
				updateMeasurementTable(result);
			}
		};
		querySvc.clearMeasurements(clearCallback);
		
		// Add city names to the suggestBox
		if(querySvc == null)
		{
			querySvc = GWT.create(QueryService.class);
		}
		
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				addCityNames(result);
			}	
		};
		querySvc.getCities(callback);
		newSuggestBoxCity = new SuggestBox(cityNames);
		
		AsyncCallback<ArrayList<String>> callbackCountry = new AsyncCallback<ArrayList<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				addCountryNames(result);
			}
			
		};
		querySvc.getCountries(callbackCountry);
		newSuggestBoxCountry = new SuggestBox(countryNames);
		
		// Add months to Month selection dropDown menu
		startMonth.setVisibleItemCount(1);
		endMonth.setVisibleItemCount(1);
		for(int i = 0; i<MONTHS.length; i++){
			startMonth.addItem(MONTHS[i]);
			endMonth.addItem(MONTHS[i]);
		}
		
		// Assemble Add filter panel.
		addPanel.add(newSuggestBoxCountry);
	    addPanel.add(newSuggestBoxCity);
	    addPanel.add(integerBoxStartYear);
	    addPanel.add(startMonth);
	    addPanel.add(integerBoxEndYear);
	    addPanel.add(endMonth);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");
 		
	    // Assemble Main panel.
	    add(filterTable.getFilterTable());
	    add(addPanel);
		add(measurementTable.getMeasurementTable());
		
		
	    // Move cursor focus to the city filter box.
	    newSuggestBoxCountry.setFocus(true);

	    // Listen for keyboard events on cityBox and countryBox and accept only letters
	   	newSuggestBoxCity.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
	   		public void onKeyPress(KeyPressEvent event) {
	   			if (Character.isDigit(event.getCharCode())){
	   				event.getNativeEvent().preventDefault();
	   			}
	   		}
	    });
	   	newSuggestBoxCountry.addKeyPressHandler(new KeyPressHandler(){
	   		@Override
	   		public void onKeyPress(KeyPressEvent event){
		   		if(Character.isDigit(event.getCharCode())){
		   			event.getNativeEvent().preventDefault();
		   		}
	   		}
	   	});
	       
	    
	    // Listen for mouse events on the Add button.
	    addFilterButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		addFilter();
	    	}
	    });
	    
	    
	    // Listen for keyboard events on startYear and endYear boxes, and accept only numbers/backspace
	   	integerBoxStartYear.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
	   		public void onKeyPress(KeyPressEvent event) {
	   			if (!Character.isDigit(event.getCharCode()) && 
	   				event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && 
	   				event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE){
	   					((IntegerBox) event.getSource()).cancelKey();
	   			}
			}
	    });
	   	integerBoxEndYear.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode()) && 
					event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && 
					event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE) {
						((IntegerBox) event.getSource()).cancelKey();
				}
			}
	    });
	   	
	   	// Add placeHolders to the text boxes	
	   	newSuggestBoxCountry.getElement().setAttribute("placeHolder", "Enter Country");
	    newSuggestBoxCity.getElement().setAttribute("placeHolder","Enter City");
	    integerBoxStartYear.getElement().setAttribute("placeHolder", "Enter StartYear");
	    integerBoxEndYear.getElement().setAttribute("placeholder", "Enter EndYear");
	   	
	    // Listen for keyboard events in the suggest box for cities.
	    integerBoxEndYear.addKeyDownHandler(new KeyDownHandler() {
	    	public void onKeyDown(KeyDownEvent event) {
	    		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    			addFilter();
	    		}
	    	}
	    });	   
	    integerBoxStartYear.addKeyDownHandler(new KeyDownHandler() {
	    	public void onKeyDown(KeyDownEvent event) {
	    		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    			addFilter();
		        }
	    	}
		});
	}
	
	/**
	   * Add filter to FlexTable. Executed when the user clicks the addFilterButton or
	   * presses enter in one of the suggestBoxes.
	   */
	private void addFilter() {
		//Get values from boxes and do capitalization for Strings
		final String country = newSuggestBoxCountry.getText().trim().substring(0, 1).toUpperCase() + newSuggestBoxCountry.getText().trim().substring(1);
		final String city = newSuggestBoxCity.getText().trim().substring(0, 1).toUpperCase() + newSuggestBoxCity.getText().trim().substring(1);
	    final Integer syear = integerBoxStartYear.getValue();
	    final Integer eyear = integerBoxEndYear.getValue();
	    final Date sdate;
	    final Date edate;
		
		// Determine Start Date
	    if(syear != null){
			String sD = startMonth.getSelectedIndex()+1 + "/1/" + syear;
			sdate = new Date(sD);
	    }else{
	    	sdate = null;
	    }
		// Determine End Date
	    if(eyear != null){
			String eD = endMonth.getSelectedIndex()+1 + "/1/" + eyear;
			edate = new Date(eD);
	    }else{
	    	edate = null;
	    }
	    
		newSuggestBoxCountry.setFocus(true);

		// Don't add the filter if it's already in the table.
		for(String s : filterTable.getCurrentCities()){
			if (s.toUpperCase().equals(city.toUpperCase())){
				Window.alert("This city is already selected.");
				return; 
			}
		}
	    
		// Test whether filter inputs are incorrect
		if(sdate != null && edate != null){
			if (eyear.intValue() < syear.intValue() || 
				(eyear.equals(syear) && startMonth.getSelectedIndex() > endMonth.getSelectedIndex())){
					Window.alert("Start date needs to be before end date");
					return;
			}
		}
		
		if(sdate != null && edate != null || sdate == null && edate == null){
				filterTable.addFilterToTable(country, city, sdate, edate);
				newSuggestBoxCountry.setText(null);
				newSuggestBoxCity.setText(null);
				integerBoxStartYear.setValue(null);
				integerBoxEndYear.setValue(null);
				startMonth.setSelectedIndex(0);
				endMonth.setSelectedIndex(0);
		}
 
		// Add a button to remove this filter from the table.
		filterTable.getCurrentRow(city).getRemoveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(city != ""){
					removeData(filterTable.getCurrentRow(city).getCity());
				}
			}
		});
		filterTable.getCurrentRowCountry(country).getRemoveButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(country != "" && city == ""){
					removeDataCountry(filterTable.getCurrentRowCountry(country).getCountry());
				}
			}
		});
	      
	    // Add a button to get data for this filter setup
	    filterTable.getCurrentRow(city).getGetDataButton().addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		addData(city, sdate, edate);
	    	}
	    });	        
	}
	
	
	/**
	 * Refreshed the flex Table containing the measurements
	 * @pre -
	 * @post -
	 * @param -
	 * @return -
	 */
	protected void refreshMeasurementTable(String country, String city, Date sdate, Date edate) {
		if (querySvc == null) {
	    	querySvc = GWT.create(QueryService.class);
	    }
		AsyncCallback<ArrayList<TemperatureMeasurement>> callback = new AsyncCallback<ArrayList<TemperatureMeasurement>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onSuccess(ArrayList<TemperatureMeasurement> result) {
				updateMeasurementTable(result);	
			}
		};
		if(sdate != null && edate != null){
			if(country == "" && city != ""){
				querySvc.temperatureMeasurements(city, sdate, edate, callback);
			}
			if(country != "" && city == ""){
				querySvc.temperatureMeasurementsCountry(country, sdate, edate, callback);
			}
			if(country != "" && city != ""){
				querySvc.temperatureMeasurementsCityCountry(country, city, sdate, edate, callback);
			}
		}
		if(sdate == null && edate == null){
			if(country == "" && city != ""){
				querySvc.temperatureMeasurements(city, callback);
			}
			if(country != "" && city == ""){
				querySvc.temperatureMeasurementsCountry(country, callback);
			}
			if(country != "" && city != ""){
				querySvc.temperatureMeasurementsCityCountry(country, city, callback);
			}
		}
	}
	
	
	protected void updateMeasurementTable(ArrayList<TemperatureMeasurement> temperatureMeasurements) {
		for (TemperatureMeasurement temperatureMeasurement : temperatureMeasurements) {
			updateMeasurementTable(temperatureMeasurement);
		}
	}

	private void updateMeasurementTable(TemperatureMeasurement temperatureMeasurement) {
		measurementTable.fillTable(temperatureMeasurement);
	}
	
	protected void removeFromMeasurementTable(String city){
		if (querySvc == null) {
	    	querySvc = GWT.create(QueryService.class);
	    }
		AsyncCallback<ArrayList<TemperatureMeasurement>> callback = new AsyncCallback<ArrayList<TemperatureMeasurement>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ArrayList<TemperatureMeasurement> result) {
				updateMeasurementTable(result);
			}
		};
		querySvc.removeCity(city, callback);
	}
	
	protected void removeCountryFromMeasurementTable(String country){
		if(querySvc == null){
			querySvc = GWT.create(QueryService.class);
		}
		AsyncCallback<ArrayList<TemperatureMeasurement>> callback = new AsyncCallback<ArrayList<TemperatureMeasurement>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void onSuccess(ArrayList<TemperatureMeasurement> result) {
				updateMeasurementTable(result);
			}
		};
		querySvc.removeCountry(country, callback);
	}

	/**
	 * Adds all city names in the given ArrayList to the suggestion box
	 * @pre -
	 * @post -
	 * @param ArrayList<String> names: the list of names to add
	 * @return -
	 */
	protected void addCityNames(ArrayList<String> names){
		cityNames.addAll(names);
	}	
	
	protected void addCountryNames(ArrayList<String> names){
		countryNames.addAll(names);
	}
	
	public void addData(String city, Date sdate, Date edate){
		FilterRow currentRow = filterTable.getCurrentRow(city);
		refreshMeasurementTable(currentRow.getCountry(),currentRow.getCity(),currentRow.getStartDate(),currentRow.getEndDate());
		measurementTable.clearMeasurementTable();
	}
	
	public void removeData(String city){
		removeFromMeasurementTable(city);
		filterTable.removeFilterFromTable(city);
		measurementTable.clearMeasurementTable();
	}
	
	public void removeDataCountry(String country){
		removeCountryFromMeasurementTable(country);
		filterTable.removeCountryFilterFromTable(country);
		measurementTable.clearMeasurementTable();
	}
}
