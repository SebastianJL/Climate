package ch.uzh.ifi.climate.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

import ch.uzh.ifi.climate.client.slider.Slider;
import ch.uzh.ifi.climate.client.slider.SliderEvent;
import ch.uzh.ifi.climate.client.slider.SliderListener;
import ch.uzh.ifi.climate.shared.Temperature;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;

/**
 * This class manages the user interface for the map view.
 * 
 * @author Johannes Lade
 * @history 2016-25-11 JL First version
 * @version 2016-25-11 JL 0.1.0
 * @responsibilities Keeps track of all panels, widgets and of the functionality
 *                   of the map view.
 */
public class MapPanel extends SimplePanel implements SliderListener {
	private final Date INITIAL_DATE = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/01/2000");

	private GeoChart geoChart;
	private ArrayList<CountryMean> data;
	private QueryServiceAsync querySvc = GWT.create(QueryService.class);
	private Date observedDate = INITIAL_DATE;
	private Slider slider;
    private Label sliderLabel;

	public MapPanel() {
		initialize();
	}

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				add(geoChart);
				updateGeoChart(INITIAL_DATE);
			}
		});
		
		// Creat and attach the slider
		Label sliderLabel = new Label("Value:");
        sliderLabel = new Label("0");
        sliderLabel.addStyleName("slider-values");
        slider = new Slider("slider",1700,2020,1700);
        this.add(sliderLabel);
        this.add(sliderLabel);
        this.add(slider);
        slider.addListener(this);
	}

	/**
	 * Draws the geoChart
	 * 
	 * @pre geoChart != null && data != null
	 * @post pre
	 * @param -
	 * @return -
	 */
	private void draw() {

		// Prepare the datatable
		DataTable dataTable = prepareData();

		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors("green", "yellow", "red");
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("gray");

		// Draw the chart
		geoChart.draw(dataTable, options);

	}

	/**
	 * Creates and fills DataTable for the geoChart.
	 * 
	 * @pre data != null && !data.isEmpty()
	 * @post -
	 * @param result
	 *            ArrayList<TemperatureMeasurement>
	 * @return DataTable Contains the data for the qeochart.
	 */
	private DataTable prepareData() {
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Mean Temperature");
		dataTable.addColumn(ColumnType.NUMBER, "Uncertainty");
		dataTable.addRows(data.size());
		for (int i = 0; i < data.size(); i++) {
			dataTable.setValue(i, 0, data.get(i).getCountry());
			dataTable.setValue(i, 1, data.get(i).getTemperatureMean().celsius());
			dataTable.setValue(i, 2, data.get(i).getUncertaintyMean().celsius());
		}
		return dataTable;
	}

	/**
	 * Updates the data for a specific date and redraws the geochart.
	 * 
	 * @pre data != null && date != null
	 * @post pre
	 * @param result
	 *            ArrayList<TemperatureMeasurement>
	 * @return -
	 */
	private void updateGeoChart(Date date) {
		AsyncCallback<ArrayList<TemperatureMeasurement>> callback = new AsyncCallback<ArrayList<TemperatureMeasurement>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<TemperatureMeasurement> result) {
				data = generateCountryMeans(result);
				draw();
			}
		};

		if (querySvc == null) {
			querySvc = GWT.create(QueryService.class);
		}
		querySvc.temperatureMeasurementsOfAllCitiesAtDate(date, callback);

	}

	/**
	 * Generates CountryMeans for a list of TemperatureMeasurements
	 * 
	 * @pre tempMeasur
	 * @post -
	 * @param tempMeasur
	 *            ArrayList<TemperatureMeasurement>
	 * @return countryMeans ArrayList with CountryMeans
	 */
	private ArrayList<CountryMean> generateCountryMeans(ArrayList<TemperatureMeasurement> tempMeasurs) {
		ArrayList<CountryMean> countryMeans = new ArrayList<CountryMean>();
		NavigableMap<String, List<TemperatureMeasurement>> tempMeasursByCountry = new TreeMap<String, List<TemperatureMeasurement>>();

		// Sort TemperatureMeasurements by country in tempMeasursByCountry
		for (TemperatureMeasurement tempMeasur : tempMeasurs) {
			List<TemperatureMeasurement> countryList = tempMeasursByCountry.get(tempMeasur.getCountry());
			if (countryList == null) {
				tempMeasursByCountry.put(tempMeasur.getCountry(),
						countryList = new ArrayList<TemperatureMeasurement>());
			}
			countryList.add(tempMeasur);
		}

		// Generate CountryMeans
		for (Map.Entry<String, List<TemperatureMeasurement>> entry : tempMeasursByCountry.entrySet()) {
			countryMeans.add(new CountryMean(entry.getKey(), entry.getValue()));
		}
		return countryMeans;

	}

	/**
	 * This class manages a country with its mean temperature value and
	 * uncertainty.
	 * 
	 * @author Johannes Lade
	 * @history 2016-26-11 JL First version
	 * @version 2016-26-11 JL 0.1.0
	 * @responsibilities Manages a country with its mean temperature value and
	 *                   uncertainty.
	 */
	class CountryMean {
		private String country;
		private Temperature tempMean;
		private Temperature uncertaintyMean;

		public CountryMean(String name, List<TemperatureMeasurement> tempMeasur) {
			this.country = name;
			setMeans(tempMeasur);
		}

		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * @return the tempMean
		 */
		public Temperature getTemperatureMean() {
			return tempMean;
		}

		/**
		 * @return the uncertaintyMean
		 */
		public Temperature getUncertaintyMean() {
			return uncertaintyMean;
		}

		/**
		 * Calculates and sets the means of the Temperatures and the
		 * Uncertainties of the list of TemperatureMeasurements. (Scientificly
		 * incorrect method.)
		 * 
		 * @pre tempMeasur != null && tempMeasur.size() != 0
		 * @post -
		 * @param tempMeasurs
		 *            ArrayList of TemperatureMeasurements
		 * @return -
		 */
		private void setMeans(List<TemperatureMeasurement> tempMeasurs) {
			ArrayList<Temperature> temps = new ArrayList<Temperature>();
			ArrayList<Temperature> uncerts = new ArrayList<Temperature>();

			for (TemperatureMeasurement tm : tempMeasurs) {
				temps.add(tm.getTemperature());
				uncerts.add(tm.getUncertainty());
			}

			this.tempMean = Temperature.sum(temps);
			this.tempMean = Temperature.divide(this.tempMean, temps.size());
			this.uncertaintyMean = Temperature.sum(uncerts);
			this.uncertaintyMean = Temperature.divide(this.uncertaintyMean, uncerts.size());

		}

	}
	
    @Override
    public boolean onSlide(SliderEvent e){
        sliderLabel.setText("" + e.getValues()[0]);
        return true;
    }

    @Override
    public void onStart(SliderEvent e){
        // We are not going to do anything onStart
    }

    @Override
    public void onStop(SliderEvent e){
        // We are not going to do anything onStop        
    }

    @Override
    public void onChange(SliderEvent e){
        //We don't need to do anything, because everything is done in onSlide in this example
    }

}
