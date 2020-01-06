package lsda;

import java.util.ArrayList;
import java.util.List;

public class MainClass 
{
	public static void main(String Args[]){
		//Creates a series of MeasurementQ1 object, creates a list and populate the list
		Measurement m1 = new Measurement(1, 2.0);
		Measurement m2 = new Measurement(13, 8.1);
		Measurement m3 = new Measurement(12, 12.5);
		List<Measurement> mesearements = new ArrayList<>();
		mesearements.add(m1);
		mesearements.add(m2);
		mesearements.add(m3);
		Measurement m4 = new Measurement(3, 10.6);
		Measurement m5 = new Measurement(22, 11.8);
		Measurement m6 = new Measurement(18, 14.5);
		List<Measurement> measure = new ArrayList<>();
		measure.add(m4);
		measure.add(m5);
		measure.add(m6);

		//Creates the WeatherStationQ1 object
		WeatherStation WS1 = new WeatherStation("Galway", mesearements);
		WeatherStation WS2 = new WeatherStation("Leixlip", measure);
		List<KeyValue> output;
		WeatherStation.stations.add(WS1);
		WeatherStation.stations.add(WS2);
		System.out.println("----------------------Question 1-------------------------");
		WS1.maxTemperature(1, 13);// Applying the maxTemperature method
		WS2.maxTemperature(15, 24);

		System.out.println("----------------------Question 2-------------------------");
		output = WeatherStation.countTemperature(9.0, 10.8, 2.1);
		System.out.println(output);
	}
}
