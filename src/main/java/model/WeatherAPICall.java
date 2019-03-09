package model;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;

public class WeatherAPICall {

	public static void main(String[] args) throws Exception {

		WeatherAPICall http = new WeatherAPICall();

		System.out.println("Testing GET");
		http.sendGet();

	}
	
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

		System.out.println(response.toString());

	}
}