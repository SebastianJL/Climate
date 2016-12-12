package ch.uzh.ifi.climate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;


/**
 * This class manages the entry point of the webpage.
 * @author		Johannes Lade
 * @history 	2016-08-11 JL First version
 * @history 	2016-25-11 JL Version 0.1.1
 * @version 	2016-08-11 JL 0.1.1
 * @responsibilities
 * 				This class manages the entry point of the webpage.
 */
public class Climate implements EntryPoint {

	private final String TABLE_PAGE_TITLE = "Table";
	private final String MAP_PAGE_TITLE = "Map";

	private TabPanel tabPanel;
	private TablePanel tablePanel;
	private MapPanel mapPanel;

	/**
	 * See super class documentation
	 */
	@Override
	public void onModuleLoad() {

		// Create tab panel and panels for table and map view.
		tabPanel = new TabPanel(); // 1.5, Unit.EM);
		tablePanel = new TablePanel();
		tablePanel.addStyleName("tablePanel");
		mapPanel = new MapPanel();
		mapPanel.addStyleName("mapPanel");
		mapPanel.setSize("70em", "30em");

		// Add panels to tabPanel
		tabPanel.add(tablePanel, TABLE_PAGE_TITLE);
		tabPanel.add(mapPanel, MAP_PAGE_TITLE);

		// Select the first tab by default
		tabPanel.selectTab(1);

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