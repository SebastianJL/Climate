package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.i18n.client.DateTimeFormat;

public class Climate implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable filterFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private SuggestBox newSuggestBoxCity = new SuggestBox();
	private DateBox newDateBoxStartDate = new DateBox();
	private DateBox newDateBoxEndDate = new DateBox();
	private Button addFilterButton = new Button("Add");
	private ArrayList<String> cities = new ArrayList<String>();
	private ArrayList<Date> sdates = new ArrayList<Date>(); 
	private ArrayList<Date> edates = new ArrayList<Date>(); 
	
	@Override
	public void onModuleLoad() {
		// Create table for filters.
		filterFlexTable.setText(0, 0, "City");
		filterFlexTable.setText(0, 1, "Start Date");
		filterFlexTable.setText(0, 2, "End Date");
		filterFlexTable.setText(0, 3, "Remove");
		
		// Add styles to elements in the filter table.
		filterFlexTable.setCellPadding(6);
		
		// Add styles to elements in the filter list table.
		filterFlexTable.getRowFormatter().addStyleName(0, "watchFilterHeader");
		filterFlexTable.addStyleName("watchFilter");
		filterFlexTable.getCellFormatter().addStyleName(0, 0, "watchFilterColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 1, "watchFilterColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 2, "watchFilterColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 3, "watchFilterColumn");
		
		// Assemble Add filter panel.
	    addPanel.add(newSuggestBoxCity);
	    addPanel.add(newDateBoxStartDate);
	    addPanel.add(newDateBoxEndDate);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");

	    // Assemble Main panel.
	    mainPanel.add(filterFlexTable);
	    mainPanel.add(addPanel);
		
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
	    
	    // Listen for keyboard events in the suggest box for cities.
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
	      final String city = newSuggestBoxCity.getText().toUpperCase().trim();
	      final Date sdate = newDateBoxStartDate.getValue();
	      final Date edate = newDateBoxEndDate.getValue();
	      newSuggestBoxCity.setFocus(true);

	      // Test wheater filter inputs are correct!
	      /**
	       * 
	       * NEEDS TO BE IMPLEMENTED!
	       * 
	       * if () {
	       * 	Window.alert("The set filter configurations are not valid.");
	       *	return;
	       * }
	       */
	        

	      newSuggestBoxCity.setText("");
	      newDateBoxStartDate.setValue(null);
	      newDateBoxEndDate.setValue(null);

	      // Don't add the filter if it's already in the table.
	      /**
	       * 
	       * NEEDS TO BE IMPLEMENTED!
	       * 
	       */
	      
	      // Add the filter to the table.
	      int row = filterFlexTable.getRowCount();
	      cities.add(city);
	      sdates.add(sdate);
	      edates.add(edate);
	      filterFlexTable.setText(row, 0, city);
	      filterFlexTable.setText(row, 1,DateTimeFormat.getFormat("YYYY/MM/DD").format(sdate));
	      filterFlexTable.setText(row, 2,DateTimeFormat.getFormat("YYYY/MM/DD").format(edate));
	      filterFlexTable.setWidget(row, 3, new Label());
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
	      
	      filterFlexTable.setWidget(row, 3, removeStockButton);	      
	  }
	  
}
