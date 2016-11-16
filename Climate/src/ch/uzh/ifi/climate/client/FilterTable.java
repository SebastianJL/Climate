package ch.uzh.ifi.climate.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class FilterTable {
	private FlexTable filterFlexTable = new FlexTable();
	
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
}
