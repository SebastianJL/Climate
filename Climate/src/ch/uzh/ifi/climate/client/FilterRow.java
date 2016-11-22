package ch.uzh.ifi.climate.client;

import java.util.Date;

import com.google.gwt.user.client.ui.Button;

public class FilterRow {
	private String country;
	private String city;
	private Date sdate;
	private Date edate;
    private Button removeFilterButton;
	private Button getDataButton;
	
	public FilterRow(String country, String city, Date sdate, Date edate){
		this.country = country;
		this.city = city;
		this.sdate = sdate;
		this.edate = edate;
		this.removeFilterButton = new Button("x");
		this.getDataButton = new Button("Go");
	}
	
	public FilterRow(String city){
		this.city = city;
		this.removeFilterButton = new Button("x");
		this.getDataButton = new Button("Go");
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public Date getStartDate(){
		return this.sdate;
	}
	
	public Date getEndDate(){
		return this.edate;
	}
	
	public Button getRemoveButton(){
		return this.removeFilterButton;
	}
	
	public Button getGetDataButton(){
		return this.getDataButton;
	}
}
