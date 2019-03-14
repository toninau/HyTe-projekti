package model;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	public static void main(String[] args) throws Exception {

		WeatherAPICall http = new WeatherAPICall();
		System.out.println("Testing GET");
		http.sendGet();

	}
	/**
	 * OpenWeatherMap-API kutsuu halutun kaupungin sään
	 * Tulostaa säätilan
	 * @throws Exception virhe tapahtuu
	 */
	private void sendGet() throws Exception {

		String url = "http://api.openweathermap.org/data/2.5/weather?q=Helsinki&APPID=aec9142965fb3ca48cf10cd6300423fc";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		
		Gson gson = new GsonBuilder().create();

		JsonElement jelement = new JsonParser().parse(response.toString());
		JsonObject jobject = jelement.getAsJsonObject();
		JsonArray main = jobject.getAsJsonArray("weather");
		System.out.println(main.get(0).getAsJsonObject().get("main"));

	}
}