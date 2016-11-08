package ch.uzh.ifi.climate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Climate implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();

	@Override
	public void onModuleLoad() {
		// Associate the Main panel with the HTML host page.
	    RootPanel.get("stockList").add(mainPanel);

	}


}
