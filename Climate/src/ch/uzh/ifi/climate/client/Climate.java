package ch.uzh.ifi.climate.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

import ch.uzh.ifi.climate.server.QueryServiceImpl;
import ch.uzh.ifi.climate.shared.TemperatureMeasurement;
import sun.nio.cs.ext.MacArabic;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

public class Climate implements EntryPoint {

	private final String TABLE_PAGE_TITLE = "Table";
	private final String MAP_PAGE_TITLE = "Map";

	private TabPanel tabPanel;
	private TablePanel tablePanel;
	private MapPanel mapPanel;

	@Override
	public void onModuleLoad() {

		// Create tab panel and panels for table and map view.
		tabPanel = new TabPanel(); // 1.5, Unit.EM);
		tablePanel = new TablePanel();
		mapPanel = new MapPanel();

		// Add panels to tabPanel
		tabPanel.add(tablePanel, TABLE_PAGE_TITLE);
		tabPanel.add(mapPanel, MAP_PAGE_TITLE);

		// Select the first tab by default
		tabPanel.selectTab(0);

		// Add controls to RootPanel
		RootPanel.get("tabPanel").add(tabPanel);

		// Add tab selection handler
		tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				/*
				 * add a token to history containing pageIndex History class
				 * will change the URL of application by appending the token to
				 * it.
				 */
				History.newItem("pageIndex" + event.getSelectedItem());
			}
		});

		/*
		 * Add value change handler to History this method will be called, when
		 * browser's Back button or Forward button are clicked and URL of
		 * application changes.
		 */
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				/* parse the history token */
				try {
					if (historyToken.substring(0, 9).equals("pageIndex")) {
						String tabIndexToken = historyToken.substring(9, 10);
						int tabIndex = Integer.parseInt(tabIndexToken);
						/* select the specified tab panel */
						tabPanel.selectTab(tabIndex);
					} else {
						tabPanel.selectTab(0);
					}
				} catch (IndexOutOfBoundsException e) {
					tabPanel.selectTab(0);
				}
			}
		});

	}
}