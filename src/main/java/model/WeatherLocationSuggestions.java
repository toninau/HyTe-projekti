package model;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import view.HyteGUI;

/**
 * This class provides locations to the use of the weather component.
 * @author IdaKi
 *
 */
public class WeatherLocationSuggestions {
	
	private static final Logger LOGGER = Logger.getLogger(WeatherLocationSuggestions.class.getName());

	
	/**
	 * Location suggestions from csv files according to the current locale. (FI, GB supported)
	 * @return List of locations.
	 */
	public List<String> getLocationSuggestions() {
		InputStream csvFile = null;
		String cvsSplitBy = "";
		int col1 = 0;
		int col2 = 0;
		switch (HyteGUI.getLocale().getCountry()) {
		case "FI":
			csvFile = getClass().getResourceAsStream("/cityLists/kuntaluettelo.csv");
			cvsSplitBy = ";";
			col1 = 1;
			col2 = 15;
			break;
		case "GB":
			csvFile = getClass().getResourceAsStream("/cityLists/Towns_List.csv");
			cvsSplitBy = ",";
			col1 = 0;
			col2 = 1;
			break;
		default:
			break;
		}
		return readLocations(col1, col2, cvsSplitBy, csvFile);
	}
	
	public List<String> readLocations(int col1, int col2, String cvsSplitBy, InputStream csvFile) {
		List<String> locations = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] city = line.split(cvsSplitBy);
				locations.add(city[col1] + ", " + city[col2]);
			}
		} catch (IOException e) {
			LOGGER.warning("IOException");
		}
		return locations;
	}
	
}
