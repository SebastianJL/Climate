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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import ch.uzh.ifi.climate.server.QueryServiceImpl;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

public class Climate implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable filterFlexTable = new FlexTable();
	private FlexTable measurementFlexTable = new FlexTable();
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
	
	
	@Override
	public void onModuleLoad() {
		

		// Create table for filters.
		filterFlexTable.setText(0, 0, "City");
		filterFlexTable.setText(0, 1, "Start Date");
		filterFlexTable.setText(0, 2, "End Date");
		filterFlexTable.setText(0, 3, "Remove");
		
		// Add styles to elements in the filter table.
		filterFlexTable.setCellPadding(6);
		
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
		
		// Add styles to elements in the filter list table.
		filterFlexTable.addStyleName("filterTable");
		filterFlexTable.getRowFormatter().addStyleName(0, "filterTableHeader");
		filterFlexTable.getCellFormatter().addStyleName(0, 0, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 1, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 2, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 3, "filterTableColumn");
		
		
		// Assemble Add filter panel.
	    addPanel.add(newSuggestBoxCity);
	    addPanel.add(integerBoxStartYear);
	    addPanel.add(startMonth);
	    addPanel.add(integerBoxEndYear);
	    addPanel.add(endMonth);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");
	    
	    // Create table for mesurement data.
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
 		
	    // Assemble Main panel.
	    mainPanel.add(filterFlexTable);
	    mainPanel.add(addPanel);
	    mainPanel.add(measurementFlexTable);
		
	    
		// Associate the Main panel with the HTML host page.
	    RootPanel.get("filterList").add(mainPanel);
	    
	    // Move cursor focus to the city filter box.
	    newSuggestBoxCity.setFocus(true);
	    
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
	      
	      
	      /*	This solution stops the add button from working, reason unknown
	      String d = "10/10/2011"; 
	      DateTimeFormat fmt = DateTimeFormat.getFormat("dd,MM,yyyy"); 
	      final Date sdate = fmt.parse(d);
	      */

	      
	      newSuggestBoxCity.setFocus(true);

	      // Don't add the filter if it's already in the table.
	      for (String s : cities) 
	    	  if (s.toUpperCase().equals(city.toUpperCase()))
	      {
		        Window.alert("This city is already selected.");
		        return; 
	      }
	      
	      // Test whether filter inputs are incorrect
	       if (eyear < syear || (eyear == syear && startMonth.getSelectedIndex() > endMonth.getSelectedIndex())) {
	        	Window.alert("The start time has to be earlier than the end time.");
	        	return;
	        }
	       if (eyear > 2016 || syear > 2016) {	//TODO change this so it automatically gets the current year
	        	Window.alert("The dates must not be in the future.");
	        	return;
	        }

	      newSuggestBoxCity.setText("");
	      integerBoxStartYear.setValue(null);
	      integerBoxEndYear.setValue(null);
	      startMonth.setSelectedIndex(0);
	      endMonth.setSelectedIndex(0);
	      
	      
	      // Add the filter to the table.
	      int row = filterFlexTable.getRowCount();
	      cities.add(city);
	      sdates.add(sdate);
	      edates.add(edate);
	      filterFlexTable.setText(row, 0, city);
	      filterFlexTable.setText(row, 1,DateTimeFormat.getFormat("dd/MM/yyyy").format(sdate));
	      filterFlexTable.setText(row, 2,DateTimeFormat.getFormat("dd/MM/yyyy").format(edate));
	      filterFlexTable.setWidget(row, 3, new Label());
	      

	      filterFlexTable.getCellFormatter().addStyleName(row, 0, "watchFilterColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 1, "watchFilterColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 2, "watchFilterColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 3, "watchFilterColumn");
	      
	      // Add a button to remove this filter from the table.
	      Button removeStockButton = new Button("x");
	      removeStockButton.addStyleDependentName("remove");
	      removeStockButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	          int removedIndex = cities.indexOf(city);
	          cities.remove(removedIndex);
	          sdates.remove(removedIndex);
	          edates.remove(removedIndex);
	          filterFlexTable.removeRow(removedIndex + 1);
	        }
	      });
	      
       // Add a button to get data for this filter setup
	   Button getDataButton = new Button("Go");
	   getDataButton.addStyleDependentName("launch search");
	   getDataButton.addClickHandler(new ClickHandler() {
		   public void onClick(ClickEvent event) {
//			   refreshTable("Zürich");
	       }
	   });	        
	        
      filterFlexTable.setWidget(row, 3, removeStockButton);	     
      filterFlexTable.setWidget(row, 4, getDataButton);
	  }
	
	
	/**
	 * Refreshes the flex Table containing the measurements
	 * @pre -
	 * @post -
	 * @param -
	 * @return float Temperature in units of Kelvin
	 */
	protected void refreshTable(String city, Date sdate, Date edate) {
		// Initialize the service proxy.
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
				updateTable(result);
				
			}
		};
		querySvc.temperatureMeasurements(city, sdate, edate, callback);
	}

	protected void updateTable(ArrayList<TemperatureMeasurement> result) {
		TemperatureMeasurement tempmesh = result.get(0);	
		measurementFlexTable.setText(1, 0, "bla");
//		for (TemperatureMeasurement temp : result) {
//			updateTable(temp);
//		}
		
	}

//	private void updateTable(TemperatureMeasurement temp) {
//		measurementFlexTable.
//		
//	}
	
}
