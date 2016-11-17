package ch.uzh.ifi.climate.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import ch.uzh.ifi.climate.server.QueryServiceImpl;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

public class Climate implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FilterTable filterTable = new FilterTable();
	private MeasurementTable measurementTable = new MeasurementTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private SuggestBox newSuggestBoxCity = new SuggestBox();
	private IntegerBox integerBoxStartYear = new IntegerBox();
	private IntegerBox integerBoxEndYear = new IntegerBox();
	private ListBox startMonth = new ListBox();
	private ListBox endMonth = new ListBox();
	private Button addFilterButton = new Button("Add");
	private ArrayList<String> cities = new ArrayList<String>();
	private ArrayList<Date> sdates = new ArrayList<Date>(); 
	private ArrayList<Date> edates = new ArrayList<Date>();
	private QueryServiceAsync querySvc = GWT.create(QueryService.class);
	MultiWordSuggestOracle cityNames = new MultiWordSuggestOracle();

	@Override
	public void onModuleLoad() {
		
		filterTable.setUpFilterTable();
		measurementTable.setUpMeasurementTable();
		
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
		
		// Add months to Month selection dropdown menu
		startMonth.setVisibleItemCount(1);
		startMonth.addItem("January");
		startMonth.addItem("February");	
		startMonth.addItem("March");
		startMonth.addItem("April");
		startMonth.addItem("May");	
		startMonth.addItem("June");
		startMonth.addItem("July");
		startMonth.addItem("August");	
		startMonth.addItem("September");
		startMonth.addItem("October");
		startMonth.addItem("November");	
		startMonth.addItem("December");
		
		endMonth.setVisibleItemCount(1);
		endMonth.addItem("January");
		endMonth.addItem("February");	
		endMonth.addItem("March");
		endMonth.addItem("April");
		endMonth.addItem("May");	
		endMonth.addItem("June");
		endMonth.addItem("July");
		endMonth.addItem("August");	
		endMonth.addItem("September");
		endMonth.addItem("October");
		endMonth.addItem("November");	
		endMonth.addItem("December");		
		
		// Assemble Add filter panel.
	    addPanel.add(newSuggestBoxCity);
	    addPanel.add(integerBoxStartYear);
	    addPanel.add(startMonth);
	    addPanel.add(integerBoxEndYear);
	    addPanel.add(endMonth);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");
 		
	    // Assemble Main panel.
	    mainPanel.add(filterTable.getFilterTable());
	    mainPanel.add(addPanel);
		mainPanel.add(measurementTable.getMeasurementTable());
		
	    
		// Associate the Main panel with the HTML host page.
	    RootPanel.get("filterList").add(mainPanel);
	    
	    // Move cursor focus to the city filter box.
	    newSuggestBoxCity.setFocus(true);
	    
	    // Listen for keyboard events on cityBox and accept only letters
	   	newSuggestBoxCity.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
	   		public void onKeyPress(KeyPressEvent event) {
	   			if (Character.isDigit(event.getCharCode())){
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
	   			if (!Character.isDigit(event.getCharCode()) && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE) {
	   				((IntegerBox) event.getSource()).cancelKey();
	   			}
			}
	    });
	   	integerBoxEndYear.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode()) && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE) {
					((IntegerBox) event.getSource()).cancelKey();
				}
			}
	    });
	    	
	   	
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
	    newSuggestBoxCity.addKeyDownHandler(new KeyDownHandler() {
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
		final String city = newSuggestBoxCity.getText().trim().substring(0, 1).toUpperCase() + newSuggestBoxCity.getText().trim().substring(1);;	//this includes automatic capitalization
		final int syear = integerBoxStartYear.getValue();
		final int eyear = integerBoxEndYear.getValue();
	      
		// Determine Start Date
		String sD = startMonth.getSelectedIndex()+1 + "/1/" + syear;
		final Date sdate = new Date(sD);
		// Determine End Date
		String eD = endMonth.getSelectedIndex()+1 + "/1/" + eyear;
		final Date edate = new Date(eD);
	      
		newSuggestBoxCity.setFocus(true);

		// Don't add the filter if it's already in the table.
		for(String s : cities){
			if (s.toUpperCase().equals(city.toUpperCase())){
				Window.alert("This city is already selected.");
				return; 
			}
		}
	      
		// Test whether filter inputs are incorrect
		if (eyear < syear || (eyear == syear && startMonth.getSelectedIndex() > endMonth.getSelectedIndex())){
			Window.alert("Start date needs to be before end date");
			return;
		}

		newSuggestBoxCity.setText("");
		integerBoxStartYear.setValue(null);
		integerBoxEndYear.setValue(null);
		startMonth.setSelectedIndex(0);
		endMonth.setSelectedIndex(0);
	      
		if(!cities.contains(city)){
			cities.add(city);
			sdates.add(sdate);
			edates.add(edate);
		}
	      
		filterTable.addFilterToTable(city, sdate, edate);
	      
		// Add a button to remove this filter from the table.
		filterTable.getCurrentRow(cities.indexOf(city)).getRemoveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
	    		int removedIndex = cities.indexOf(city);
	    		measurementTable.clearMeasurementTable();
	    		removeFromMeasurementTable(cities.get(removedIndex));
	    		filterTable.removeFilterFromTable(cities.get(removedIndex));
	    		cities.remove(removedIndex);
	    		sdates.remove(removedIndex);
	    		edates.remove(removedIndex);
	    		filterTable.getFilterTable().removeRow(removedIndex + 1);
			}
		});
	      
	    // Add a button to get data for this filter setup
	    filterTable.getCurrentRow(cities.indexOf(city)).getGetDataButton().addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		int getIndex = cities.indexOf(city);
	    		measurementTable.clearMeasurementTable();
	    		String cityGet = cities.get(getIndex);
	    		Date sdateGet = sdates.get(getIndex);
	    		Date edateGet = edates.get(getIndex);
	    		refreshMeasurementTable(cityGet, sdateGet, edateGet);
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
	protected void refreshMeasurementTable(String city, Date sdate, Date edate) {
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
		querySvc.temperatureMeasurements(city, sdate, edate, callback);
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
}