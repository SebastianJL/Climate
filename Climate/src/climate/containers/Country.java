package climate.containers;

import java.util.ArrayList;

public class Country {
	private String name;
	private ArrayList<City> cities = new ArrayList<City>();
	
	
	public Country(String name){
		this.name = name;
	}
}
