package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;

public class FilterTable {
	private FlexTable filterFlexTable = new FlexTable();
	private ArrayList<FilterRow> filterRows = new ArrayList<FilterRow>();
	
	public void setUpFilterTable(){
		// Create table for filters.
		filterFlexTable.setText(0, 0, "Country");
		filterFlexTable.setText(0, 1, "City");
		filterFlexTable.setText(0, 2, "Start Date");
		filterFlexTable.setText(0, 3, "End Date");
		filterFlexTable.setText(0, 4, "Remove");
		filterFlexTable.setText(0, 5, "Go");
		
		// Add styles to elements in the filter table.
		filterFlexTable.setCellPadding(6);
		
		// Add styles to elements in the filter list table.
		filterFlexTable.addStyleName("filterTable");
		filterFlexTable.getRowFormatter().addStyleName(0, "filterTableHeader");
		filterFlexTable.getCellFormatter().addStyleName(0, 0, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 1, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 2, "filterTableColumn");
		filterFlexTable.getCellFormatter().addStyleName(0, 3, "filterTableColumn");
	}

	public FlexTable getFilterTable(){
		return this.filterFlexTable;
	}
	
    // Add the filter to the table.
	public void addFilterToTable(String country, String city, Date sdate, Date edate){
	      int row = filterFlexTable.getRowCount();
	      
	      FilterRow currentFilterRow = new FilterRow(country, city, sdate, edate);
	      
	      filterRows.add(currentFilterRow);
	      
	      if(country != ""){
	    	  filterFlexTable.setText(row, 0, country);
	      }else{
	    	  filterFlexTable.setText(row, 0, "all");
	      }
	      if(city != ""){
		      filterFlexTable.setText(row, 1, city); 
	      }else{
	    	  filterFlexTable.setText(row, 1, "all");
	      }
	      if(sdate != null && edate != null){
	    	  filterFlexTable.setText(row, 2, DateTimeFormat.getFormat("dd/MM/yyyy").format(sdate));
	    	  filterFlexTable.setText(row, 3, DateTimeFormat.getFormat("dd/MM/yyyy").format(edate));
	      }
	      if(sdate == null && edate == null){
	    	  filterFlexTable.setText(row, 2, "earliest");
	    	  filterFlexTable.setText(row, 3, "latest");
	      }
	      filterFlexTable.setWidget(row, 4, currentFilterRow.getRemoveButton());	     
	      filterFlexTable.setWidget(row, 5, currentFilterRow.getGetDataButton());

	      filterFlexTable.getCellFormatter().addStyleName(row, 0, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 1, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 2, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 3, "filterTableColumn");
	      currentFilterRow.getRemoveButton().addStyleDependentName("remove");
		  currentFilterRow.getGetDataButton().addStyleDependentName("launch search");	 
	}
	
	public void removeFilterFromTable(String city){
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCity().equals(city)){
				filterFlexTable.removeRow(filterRows.indexOf(filterRow)+1);
				filterRows.remove(filterRow);
			}
		}
	}
	
	public void removeCountryFilterFromTable(String country){
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCountry().equals(country)){
				filterFlexTable.removeRow(filterRows.indexOf(filterRow)+1);
				filterRows.remove(filterRow);
			}
		}
	}
	
	public FilterRow getCurrentRow(String city){
		FilterRow currentFilterRow = null;
		for(FilterRow filterRow:filterRows){
			if(filterRow.getCity().equals(city)){
				currentFilterRow = filterRow;
			}
		}
		return currentFilterRow;
	}
	
	public FilterRow getCurrentRowCountry(String country){
		FilterRow currentFilterRow = null;
		for(FilterRow filterRow:filterRows){
			if(filterRow.getCountry().equals(country)){
				currentFilterRow = filterRow;
			}
		}
		return currentFilterRow;
	}
	
	public ArrayList<String> getCurrentCities(){
		ArrayList<String> cities = new ArrayList<String>();
		for(FilterRow filterRow : filterRows){
			cities.add(filterRow.getCity());
		}
		return cities;
	}
	
	public ArrayList<String> getCurrentCountries(){
		ArrayList<String> countries = new ArrayList<String>();
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCity() == ""){
				countries.add(filterRow.getCountry());
			}
		}
		return countries;
	}
}
