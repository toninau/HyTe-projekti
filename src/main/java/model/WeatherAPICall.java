package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Jeremiaza
 * Luokka sään kutsumiselle.
 * Käyttää GSON-kirjastoa JSON:in parsetukseen
 */
public class WeatherAPICall {
	private double celsius;
	private String state;
	
	public WeatherAPICall(String location) throws IOException{
		
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+ location +"&APPID=aec9142965fb3ca48cf10cd6300423fc";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JsonElement jelement = new JsonParser().parse(response.toString());
		JsonObject jobject = jelement.getAsJsonObject();
		JsonArray statemain = jobject.getAsJsonArray("weather");
		JsonObject celsiusmain = jobject.get("main").getAsJsonObject();
		state = statemain.get(0).getAsJsonObject().get("main").toString().replace("\"", "");
		String kelvin = celsiusmain.get("temp").toString();
		celsius = Double.parseDouble(kelvin) - 273.15;
	}
	
	public String getCelsius() {
		return Double.toString(Math.round(celsius));
	}

	public String getState() {
		return state;
	}
}