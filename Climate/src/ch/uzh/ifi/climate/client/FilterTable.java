package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FilterTable {
	private FlexTable filterFlexTable = new FlexTable();
	private ArrayList<FilterRow> filterRows = new ArrayList<FilterRow>();
	
	public void setUpFilterTable(){
		// Create table for filters.
		filterFlexTable.setText(0, 0, "City");
		filterFlexTable.setText(0, 1, "Start Date");
		filterFlexTable.setText(0, 2, "End Date");
		filterFlexTable.setText(0, 3, "Remove");
		filterFlexTable.setText(0, 4, "Go");
		
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
	public void addFilterToTable(String city, Date sdate, Date edate){
	      int row = filterFlexTable.getRowCount();
	      FilterRow currentFilterRow = new FilterRow(city, sdate, edate);
	      
	      filterRows.add(currentFilterRow);
	      
	      filterFlexTable.setText(row, 0, city);
	      filterFlexTable.setText(row, 1,DateTimeFormat.getFormat("dd/MM/yyyy").format(sdate));
	      filterFlexTable.setText(row, 2,DateTimeFormat.getFormat("dd/MM/yyyy").format(edate));
	      filterFlexTable.setWidget(row, 3, new Label());
	      filterFlexTable.setWidget(row, 3, currentFilterRow.getRemoveButton());	     
	      filterFlexTable.setWidget(row, 4, currentFilterRow.getGetDataButton());

	      filterFlexTable.getCellFormatter().addStyleName(row, 0, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 1, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 2, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 3, "filterTableColumn");
	      currentFilterRow.getRemoveButton().addStyleDependentName("remove");
		  currentFilterRow.getGetDataButton().addStyleDependentName("launch search");	      
	}
	
	public FilterRow getCurrentRow(int index){
		return this.filterRows.get(index);
	}
}
