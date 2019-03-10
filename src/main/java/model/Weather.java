package model;

public class Weather {
	private String temperature;
	private String description;
	private String weather; // cloudy, windy etc.
	public Weather() {
		// TODO Auto-generated constructor stub
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", description=" + description + ", weather=" + weather + "]";
	}
	
	
}
